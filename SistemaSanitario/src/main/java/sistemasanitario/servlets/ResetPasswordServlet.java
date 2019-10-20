package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.passay.RuleResult;
import sistemasanitario.entities.ResetPasswordToken;
import sistemasanitario.entities.User;
import sistemasanitario.utils.PasswordUtil;
import sistemasanitario.utils.TokenUtil;

@WebServlet("/resetpassword")
public class ResetPasswordServlet extends HttpServlet{

    private Dao<ResetPasswordToken, Integer> resetTokenDao;
    private Dao<User, Integer> usersDao;
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());

    @Override
    public void init() throws ServletException {
        
        resetTokenDao = (Dao<ResetPasswordToken, Integer>)getServletContext().getAttribute("resetTokenDao");
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String token = req.getParameter("token");
        String password = req.getParameter("newPassword");
        
        if(token == null || password == null ) return;
        
        RuleResult passwordValResult = PasswordUtil.validatePassword(password);
        
        if(!TokenUtil.checkSyntaxResetToken(token)) return;

        if(!passwordValResult.isValid()){
            
            LOGGER.log(Level.INFO, passwordValResult.getDetails().toString());
            req.setAttribute("invalidPassword", true);
            forwardToJSPPage(token, req, resp); 
            return;
        }
        
        ResetPasswordToken tokenEntry = getTokenEntryFromDB(token);
        if(tokenEntry != null){
            
            try{
                String passwordHash = PasswordUtil.hash(password.toCharArray());
                usersDao.refresh(tokenEntry.getUser());

                UpdateBuilder<User, Integer> updateBuilder = usersDao.updateBuilder();
                updateBuilder.where().idEq(tokenEntry.getUser().getId());
                updateBuilder.updateColumnValue("password", passwordHash);
                updateBuilder.update();

                //Rimuovo il token utilizzato
                resetTokenDao.delete(tokenEntry);

                //Stampa password modificata correttamente.
                req.setAttribute("success", true);
                forwardToJSPPage(token, req, resp);
            }
            catch (SQLException ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        else{
            
            req.setAttribute("invalidToken", true);
            forwardToJSPPage(token, req, resp);   
        }
    }
    
    private void forwardToJSPPage(String token, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        RequestDispatcher dispatcher = getServletContext().
        getRequestDispatcher("/WEB-INF/resetPassword.jsp");
        req.setAttribute("token", token);
        dispatcher.forward(req, resp);  
    }
    
    private ResetPasswordToken getTokenEntryFromDB(String token){
        
        QueryBuilder<ResetPasswordToken, Integer> queryBuilder = resetTokenDao.queryBuilder();
        PreparedQuery<ResetPasswordToken> getTokenQuery;
        
        try{
            String hashToken = TokenUtil.getHashSHA256(token);
            getTokenQuery = queryBuilder.where().eq("token", hashToken).prepare();
            List<ResetPasswordToken> tokens = resetTokenDao.query(getTokenQuery);           
            
            //Il token è nel db
            if(tokens.size() > 0){ 
                
                Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));

                long elapsedDaysSinceCreation = TimeUnit.DAYS.convert(now.getTime() - 
                        tokens.get(0).getCreatedTime().getTime()
                        , TimeUnit.MILLISECONDS);

                //Se il token è stato generato meno di un giorno fa
                if(elapsedDaysSinceCreation < 1)
                    return tokens.get(0);
                else
                    return null;           
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        RequestDispatcher dispatcher = getServletContext().
        getRequestDispatcher("/404.jsp");
        dispatcher.forward(req, resp);  
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String token = req.getParameter("token");
        ResetPasswordToken tokenEntry = null;
        
        if(TokenUtil.checkSyntaxResetToken(token) && getTokenEntryFromDB(token) != null){
            
            forwardToJSPPage(token, req, resp);    
        }
        else{
             //Redirect to 404 page
             //forwardToErrorPage();
            resp.sendError(404);
        } 
    }
}
       