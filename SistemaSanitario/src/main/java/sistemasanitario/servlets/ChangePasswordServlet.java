/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.passay.RuleResult;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.User;
import static sistemasanitario.utils.GeneralUtil.getUserSession;
import sistemasanitario.utils.PasswordUtil;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {
    
    
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private Dao<User, Integer> usersDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        usersDao = (Dao<User, Integer>)getServletContext().getAttribute("UsersDao");
    }
    

    //FORMATO RICHIESTA: .../changepassword?oldpassword=...&newpassword=...
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = getUserSession(request);
        
        if(session == null) return;
        
        String oldPassword = (String) request.getAttribute("oldpassword");
        String newPassword = (String) request.getAttribute("newpassword");
        if(oldPassword == null || newPassword == null) return;
        
        //validate parameters
        
        //new password rispetta le policy?        
        RuleResult passwordValResult = PasswordUtil.validatePassword(newPassword);
        
        //Send error message
        if(!passwordValResult.isValid()){
            
            return;
        }
        
        //Verifica la vecchia password
        User user = (User)session.getAttribute("user");
        if(!PasswordUtil.verify(user.getPassword(), oldPassword.toCharArray())){
            
            //Send error message
            return;
        }
        
        //Aggiorno la nuova password le database
        String passwordHash = PasswordUtil.hash(newPassword.toCharArray());
        UpdateBuilder<User, Integer> updateBuilder = usersDao.updateBuilder();
        try {
            
            updateBuilder.where().idEq(user.getId());
            updateBuilder.updateColumnValue("password", passwordHash);
            updateBuilder.update();
            
        } catch (SQLException ex) {
            
        }
    }
    
}
