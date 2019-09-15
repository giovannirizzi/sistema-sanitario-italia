package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.User;
import sistemasanitario.utils.AuthTokenUtil;
import sistemasanitario.utils.PasswordUtil;

public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private static final int REMEMBERME_COOKIE_AGE = 60;
    
    private Dao<AuthToken, Integer> authTokensDao;
    private Dao<User, Integer> usersDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        authTokensDao = (Dao<AuthToken, Integer>)getServletContext().getAttribute("AuthTokensDao");
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
    }
    
    private boolean isAlreadyLogged(HttpServletRequest request){
        
        ServletContext servletContext = ((HttpServletRequest) request).getServletContext();
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        return session != null && 
                session.getAttribute("user") != null;
    }
 
    private void redirectToServices(HttpServletResponse response) throws IOException{
     
        String contextPath = getServletContext().getContextPath(); 
        if (!contextPath.endsWith("/")) contextPath += "/";
        
        response.sendRedirect(response.encodeRedirectURL(contextPath + "myservices/"));
        LOGGER.log(Level.INFO, "Redirect to My Services"); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isAlreadyLogged(request)){
           
            RequestDispatcher dispatcher = getServletContext().
            getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
            
            LOGGER.log(Level.INFO, "Forward to {0}", "login.jsp");
        }
        else{
            
            redirectToServices(response); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(isAlreadyLogged(request)){
            redirectToServices(response); 
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("rememberme") != null;

        User user = null;
        
        //QUERY PER VERIFICARE LE CREDENZIALI
        
        QueryBuilder<User, Integer> queryBuilder = usersDao.queryBuilder();
        
        PreparedQuery<User> getUserByUsernameQuery;
        try {
            
            getUserByUsernameQuery = queryBuilder.where().eq("username", username).prepare();
            List<User> users = usersDao.query(getUserByUsernameQuery);
            
            if(users.size() > 0){

                User tmpUser = users.get(0);
                
                //Verifica password
                if(PasswordUtil.verify(tmpUser.getPassword(), password.toCharArray()))
                   user = tmpUser;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (user != null) { //credenziali verificate

            if(rememberMe){

                AuthToken token = AuthTokenUtil.getRandomToken();
                
                Cookie userCookie = new Cookie("rememberme", 
                        token.selector+token.validator);
                userCookie.setMaxAge(REMEMBERME_COOKIE_AGE);
                response.addCookie(userCookie);
                
                saveAuthToken(user, token);     
            }
            request.getSession().setAttribute("user", user);
            redirectToServices(response);
            
        } else { //login failed
            
            request.setAttribute("errorMessage", "Invalid user or password");

            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/WEB-INF/login.jsp");
            
               
            dispatcher.forward(request, response);
        }
    }
    
    private void saveAuthToken(User user, AuthToken token){
        
        token.validator = AuthTokenUtil.getHashSHA256(token.validator); 
        try { 
            token.user = user;
            authTokensDao.create(token);
        } 
        catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
