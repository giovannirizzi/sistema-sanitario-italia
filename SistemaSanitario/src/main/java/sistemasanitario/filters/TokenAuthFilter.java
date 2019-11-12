package sistemasanitario.filters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import sistemasanitario.listeners.AuthenticatedUserListener;
import sistemasanitario.servlets.PasswordTest;
import static sistemasanitario.utils.GeneralUtil.getUserSession;
import sistemasanitario.utils.TokenUtil;

public class TokenAuthFilter implements Filter{

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    private static final int MAX_DAYS_REMEMBER_ME = 7;
    public static final String REMEMBER_COOKIE_NAME = "rememberme";
    
    private Dao<AuthToken, Integer> authTokensDao;
    private Dao<User, Integer> usersDao;
    private AuthenticatedUserListener authUserListener;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        authTokensDao = (Dao<AuthToken, Integer>)filterConfig.getServletContext().getAttribute("AuthTokensDao");
        usersDao = (Dao<User, Integer>)filterConfig.getServletContext().getAttribute("UsersDao");
        authUserListener = new AuthenticatedUserListener(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if(request instanceof HttpServletRequest){
            
            HttpSession session = getUserSession((HttpServletRequest)request);

            if(session == null)
                authUserWithTokenIfPossible((HttpServletRequest)request,(HttpServletResponse)response);  
        }
        
        chain.doFilter(request, response);
    }
    
    private void authUserWithTokenIfPossible(HttpServletRequest request, HttpServletResponse response){
       
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        String rememberMeToken = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(REMEMBER_COOKIE_NAME)){
                    rememberMeToken = cookie.getValue();
                    break;
                } 
            }  
        }
        if(rememberMeToken != null && rememberMeToken.length() > 0){

            String selector = rememberMeToken.substring(0, 36);
            String validator = rememberMeToken.substring(36);

            QueryBuilder<AuthToken, Integer> queryBuilder = authTokensDao.queryBuilder();
            PreparedQuery<AuthToken> getTokenBySelectorQuery;

            try {
                getTokenBySelectorQuery = queryBuilder.where().eq("selector", selector).prepare();
                List<AuthToken> tokens = authTokensDao.query(getTokenBySelectorQuery);

                boolean authenticated = false;

                if(tokens.size() > 0){ 

                    Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
                  
                    long elapsedDaysSinceCreation = TimeUnit.DAYS.convert(now.getTime() - 
                            tokens.get(0).getCreatedTime().getTime()
                            , TimeUnit.MILLISECONDS);
                    
                    LOGGER.log(Level.INFO, "ELAPSED DAYS SINCE CREATION OF AUTH TOKEN: {0}", elapsedDaysSinceCreation);
                    
                    if(elapsedDaysSinceCreation < MAX_DAYS_REMEMBER_ME
                            && TokenUtil.verify(tokens.get(0).getValidator(), validator)){

                        LOGGER.log(Level.INFO, "User authenticated with token");
                        usersDao.refresh(tokens.get(0).user);
                        
                        HttpSession session = ((HttpServletRequest)request).getSession();
                        session.setAttribute("user", tokens.get(0).user);    
                        
                        authUserListener.onNewUserAuthenticated(session, tokens.get(0).user);
                    }
                    else{
                        authTokensDao.delete(tokens.get(0));
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
