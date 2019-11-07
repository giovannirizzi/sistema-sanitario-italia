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
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.User;
import sistemasanitario.filters.TokenAuthFilter;
import static sistemasanitario.utils.GeneralUtil.getUserSession;
import sistemasanitario.utils.TokenUtil;
import sistemasanitario.utils.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private static final int REMEMBERME_COOKIE_AGE = 60*60*24*14; //14 giorni
    
    private Dao<AuthToken, Integer> authTokensDao;
    private Dao<User, Integer> usersDao;
    private Dao<Paziente, Integer> pazienteDao;
    private Dao<Medico, Integer> medicoDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        authTokensDao = (Dao<AuthToken, Integer>)getServletContext().getAttribute("AuthTokensDao");
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
        pazienteDao = (Dao<Paziente, Integer>)getServletContext().getAttribute("pazienteDao");
        medicoDao = (Dao<Medico, Integer>)getServletContext().getAttribute("medicoDao");
    }
    
    public void loadUserData(HttpSession session, User user) throws SQLException {
        
        String headerUserName = null;
        
        switch(user.getType()){
            
            case PAZIENTE:
            {
                List<Paziente> pazienti = pazienteDao.queryForEq("idUtente", user.getId());
              
                if(pazienti != null && pazienti.size()>0){
                    Paziente paziente = pazienti.get(0);
                  
                    session.setAttribute("paziente", paziente);
                    headerUserName = paziente.getNome() + " " + paziente.getCognome();
                }
                else{
                    LOGGER.log(Level.INFO, "no pazienti");
                }
                break;
            }
                
            case SS_PROVINCIALE:
            {
                break;
            }
            
            default:
            {
                List<Medico> medici = medicoDao.queryForEq("idUtente", user.getId());
                if(medici != null && medici.size()>0){
                
                    Medico medico = medici.get(0);
                    session.setAttribute("medico", medico);
                    headerUserName = medico.getNome() + " " + medico.getCognome();
                }
                break; 
            }  
        }
        if(headerUserName != null)
            session.setAttribute("headerUserName", headerUserName.toUpperCase());
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
                
        response.sendRedirect(response.encodeRedirectURL(contextPath + "myservices/dashboard"));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(!isAlreadyLogged(request)){
           
            RequestDispatcher dispatcher = getServletContext().
            getRequestDispatcher("/WEB-INF/login.jsp");
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
                loadUserData(session, (User)session.getAttribute("user"));
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
                loadUserData(request.getSession(), user);
            } catch (SQLException ex) {
                response.sendError(500, "SQLException: loadUserData");
                return;
            }
            redirectToServices(response);
            
        } else { //login failed
            
            request.setAttribute("error", true);

            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/WEB-INF/login.jsp");
            
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
