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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.User;
import sistemasanitario.filters.TokenAuthFilter;
import sistemasanitario.listeners.AuthenticatedUserListener;
import static sistemasanitario.utils.GeneralUtil.getUserSession;
import sistemasanitario.utils.TokenUtil;
import sistemasanitario.utils.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private static final int REMEMBERME_COOKIE_AGE = 60*60*24*14; //14 giorni
    
    private AuthenticatedUserListener authUserListener; 
    private Dao<AuthToken, Integer> authTokensDao;
    private Dao<User, Integer> usersDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        authUserListener = new AuthenticatedUserListener(getServletContext());
        authTokensDao = (Dao<AuthToken, Integer>)getServletContext().getAttribute("AuthTokensDao");
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
    }
 
    private void redirectToServices(HttpServletResponse response) throws IOException{
     
        String contextPath = getServletContext().getContextPath(); 
        if (!contextPath.endsWith("/")) contextPath += "/";
                
        response.sendRedirect(response.encodeRedirectURL(contextPath + "myservices/dashboard"));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession authUserSession = getUserSession(request);
        
        if(authUserSession == null){
           
            RequestDispatcher dispatcher = getServletContext().
            getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
        else{
            
            redirectToServices(response); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = getUserSession(request);
        
        if(session != null){
            
            try {
                authUserListener.onNewUserAuthenticated(session, (User)session.getAttribute("user"));
            } catch (SQLException ex) {
                response.sendError(500, "SQLException: loadUserData");
            }
            
            redirectToServices(response); 
            return;
        }
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("rememberme") != null;

        User user = null;
        
        if(username != null && username.length()>0 && password != null && password.length() > 0){
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
        }

        if (user != null) { //credenziali verificate

            if(rememberMe){

                AuthToken token = TokenUtil.getRandomAuthToken();
                
                Cookie rememberCookie = new Cookie(TokenAuthFilter.REMEMBER_COOKIE_NAME, 
                        token.selector+token.validator);
                rememberCookie.setMaxAge(REMEMBERME_COOKIE_AGE);
                rememberCookie.setHttpOnly(true);
                rememberCookie.setPath("/");
                response.addCookie(rememberCookie);
                
                saveAuthToken(user, token);     
            }
            
            request.getSession().setAttribute("user", user);
            try {
                authUserListener.onNewUserAuthenticated(request.getSession(false), user);
            } catch (SQLException ex) {
                response.sendError(500, "SQLException: loadUserData");
                return;
            }
            redirectToServices(response);
            
        } else { //login failed
            
            request.setAttribute("error", true);

            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            
            dispatcher.forward(request, response);
        }
    }
    
    private void saveAuthToken(User user, AuthToken token){
        
        token.validator = TokenUtil.getHashSHA256(token.validator); 
        try { 
            token.user = user;
            authTokensDao.create(token);
        } 
        catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
