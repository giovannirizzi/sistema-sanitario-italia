package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.entities.ResetPasswordToken;
import sistemasanitario.entities.User;
import sistemasanitario.utils.PasswordUtil;
import sistemasanitario.utils.TokenUtil;

public class ForgotPasswordServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private Dao<ResetPasswordToken, Integer> resetTokenDao;
    private Dao<User, Integer> usersDao;
    
    @Override
    public void init() throws ServletException {
        
        resetTokenDao = (Dao<ResetPasswordToken, Integer>)getServletContext().getAttribute("resetTokenDao");
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        if(email == null) return;
        
        User user = getUserByEmail(email);
        
        if(user == null) return;
        
        ResetPasswordToken token = TokenUtil.getRandomResetPasswordToken();
        token.user = user;
        
        try {
            
            //TODO INVIA TOKEN PER EMAIL
              
            LOGGER.log(Level.INFO, "Reset password token: {0}", token.token );
            
            token.token = TokenUtil.getHashSHA256(token.token);
            
            resetTokenDao.create(token);           
            
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         
    }
    
    private User getUserByEmail(String email){
        
        QueryBuilder<User, Integer> queryBuilder = usersDao.queryBuilder();

        PreparedQuery<User> getUserByEmailQuery;
     
        try {
            getUserByEmailQuery = queryBuilder.where().eq("email", email).prepare();
       
        List<User> users = usersDao.query(getUserByEmailQuery);

            if(users.size() > 0)
                return users.get(0);
            else
                return null;
            
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
