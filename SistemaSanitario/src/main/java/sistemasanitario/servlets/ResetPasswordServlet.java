package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.passay.CharacterCharacteristicsRule;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import sistemasanitario.entities.ResetPasswordToken;
import sistemasanitario.entities.User;
import sistemasanitario.utils.PasswordUtil;

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
        
        QueryBuilder<ResetPasswordToken, Integer> queryBuilder = resetTokenDao.queryBuilder();
        PreparedQuery<ResetPasswordToken> getTokenQuery;
        
        RuleResult passwordValResult = PasswordUtil.validatePassword(password);

        if(!passwordValResult.isValid()){
            resp.sendError(404);
            LOGGER.log(Level.INFO, passwordValResult.getDetails().toString());
            req.setAttribute("error", passwordValResult.getDetails().toString());
            forwardToJSPPage(req, resp); 
            return;
        }

        try{
            getTokenQuery = queryBuilder.where().eq("token", token).prepare();
            List<ResetPasswordToken> tokens = resetTokenDao.query(getTokenQuery);           
            
            //Il token è nel db
            if(tokens.size() > 0){ 

                Timestamp now = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));

                long elapsedDaysSinceCreation = TimeUnit.DAYS.convert(now.getTime() - 
                        tokens.get(0).getCreatedTime().getTime()
                        , TimeUnit.MILLISECONDS);

                //Se il token è stato generato meno di un giorno fa
                if(elapsedDaysSinceCreation < 1){

                    String passwordHash = PasswordUtil.hash(password.toCharArray());
                    usersDao.refresh(tokens.get(0).getUser());
                    
                    UpdateBuilder<User, Integer> updateBuilder = usersDao.updateBuilder();
                    updateBuilder.where().idEq(tokens.get(0).getUser().getId());
                    updateBuilder.updateColumnValue("password", passwordHash);
                    updateBuilder.update();
                    
                    //Rimuovo il token utilizzato
                    resetTokenDao.delete(tokens.get(0));
                    
                    //Stampa password modificata correttamente.
                    req.setAttribute("success", true);
                    forwardToJSPPage(req, resp);
                }
                else{
                    resp.sendError(404);
                    req.setAttribute("error", "errore bobobo");
                    forwardToJSPPage(req, resp);
                }
            }     
        }
        catch (SQLException ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void forwardToJSPPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        RequestDispatcher dispatcher = getServletContext().
        getRequestDispatcher("/WEB-INF/resetPassword.jsp");
        dispatcher.forward(req, resp);  
    }
    
    private boolean isValidToken(String token){
        
        if(token == null || token.length() != 36)
            return false;
        
        try{ 
            UUID tokenUUID = UUID.fromString(token);
        }
        catch(IllegalArgumentException ex){
            return false;
        }
        return true;
    }
    
    private void forwardToErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
        RequestDispatcher dispatcher = getServletContext().
        getRequestDispatcher("/404.jsp");
        dispatcher.forward(req, resp);  
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String token = req.getParameter("token");
        
        if(isValidToken(token)){
            
            req.setAttribute("token", token);
            forwardToJSPPage(req, resp);    
        }
        else{
             //Redirect to 404 page
             //forwardToErrorPage();
            resp.sendError(404);
        } 
    }
}
       