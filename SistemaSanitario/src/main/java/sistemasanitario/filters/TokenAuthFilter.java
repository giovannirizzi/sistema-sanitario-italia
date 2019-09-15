package sistemasanitario.filters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.User;
import sistemasanitario.servlets.PasswordTest;
import sistemasanitario.utils.AuthTokenUtil;

public class TokenAuthFilter implements Filter{

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    private Dao<AuthToken, Integer> authTokensDao;
    private Dao<User, Integer> usersDao;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        authTokensDao = (Dao<AuthToken, Integer>)filterConfig.getServletContext().getAttribute("AuthTokensDao");
        usersDao = (Dao<User, Integer>)filterConfig.getServletContext().getAttribute("UsersDao");
  
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if(request instanceof HttpServletRequest){
            
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            
            if(session == null || session.getAttribute("user") == null)
                authUserWithTokenIfPossible((HttpServletRequest)request,(HttpServletResponse)response);  
        }
        
        chain.doFilter(request, response);
    
    }
    
    private void authUserWithTokenIfPossible(HttpServletRequest request, HttpServletResponse response){
       
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        String rememberMeToken = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("rememberme")){
                    rememberMeToken = cookie.getValue();
                    break;
                } 
            }  
        }
        if(rememberMeToken != null && rememberMeToken.length() > 0){

            String selector = rememberMeToken.substring(0, 16);
            String validator = rememberMeToken.substring(16);

            QueryBuilder<AuthToken, Integer> queryBuilder = authTokensDao.queryBuilder();
            PreparedQuery<AuthToken> getTokenBySelectorQuery;

            try {
                getTokenBySelectorQuery = queryBuilder.where().eq("selector", selector).prepare();
                List<AuthToken> tokens = authTokensDao.query(getTokenBySelectorQuery);

                boolean authenticated = false;

                if(tokens.size() > 0){

                    if(AuthTokenUtil.verify(tokens.get(0).getValidator(), validator)){

                        LOGGER.log(Level.INFO, "User authenticated with token");
                        usersDao.refresh(tokens.get(0).user);
                        ((HttpServletRequest)request).getSession().setAttribute("user", tokens.get(0).user);    
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(TokenAuthFilter.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void destroy() {
        
    }

   }
