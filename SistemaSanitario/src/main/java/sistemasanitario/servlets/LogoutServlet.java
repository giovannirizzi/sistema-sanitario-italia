package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.User;
import sistemasanitario.filters.TokenAuthFilter;

public class LogoutServlet extends HttpServlet{
    
    private Dao<AuthToken, Integer> authTokensDao;
    private Dao<User, Integer> usersDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        authTokensDao = (Dao<AuthToken, Integer>)getServletContext().getAttribute("AuthTokensDao");
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
    }
    
    private void clearRememberMeCookie(HttpServletRequest req, HttpServletResponse response){
        
        Cookie rememberCookie = new Cookie(TokenAuthFilter.REMEMBER_COOKIE_NAME, "");
        rememberCookie.setPath("/");
        rememberCookie.setMaxAge(0);
        response.addCookie(rememberCookie);
        
        Cookie[] cookies = req.getCookies();
        
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(TokenAuthFilter.REMEMBER_COOKIE_NAME)){
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    break;
                }
            }
    }
    
    private void clearAuthTokens(HttpServletRequest req){
        
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            
            User user = (User)session.getAttribute("user");
            DeleteBuilder authdeleteBuilder = authTokensDao.deleteBuilder();
            try {
                
                authdeleteBuilder.where().eq("userId", user);
                authTokensDao.delete(authdeleteBuilder.prepare());  
            } catch (SQLException ex) {
                Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
    }
    
    private void logout(HttpServletRequest req, HttpServletResponse resp){
        
        HttpSession session = req.getSession(false);
        //Se l'utente è autenticato
        if(session != null && session.getAttribute("user") != null){
        
            resp.setContentType("text/html"); 

            clearRememberMeCookie(req, resp);
            clearAuthTokens(req);
            session.invalidate();
            
             //Redirect to home
            try {
                String contextPath = req.getContextPath();
                if (!contextPath.endsWith("/")) contextPath += "/";
                resp.sendRedirect(contextPath);
            } catch (IOException ex) {
                Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }  
    }
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logout(req, resp);   
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logout(req, resp);
    }
    
}
