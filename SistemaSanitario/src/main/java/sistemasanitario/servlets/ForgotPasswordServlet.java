package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.entities.ResetPasswordToken;
import sistemasanitario.entities.User;
import sistemasanitario.utils.PasswordUtil;
import sistemasanitario.utils.TokenUtil;

//import to send email
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/forgotpassword")
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        RequestDispatcher dispatcher = getServletContext().
        getRequestDispatcher("/WEB-INF/forgotPassword.jsp");
        dispatcher.forward(req, resp);
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        if(email == null) return;
        
        User user = getUserByEmail(email);
        
        if(user != null){
             
            ResetPasswordToken token = TokenUtil.getRandomResetPasswordToken();
            token.user = user;

            try {

                //TODO INVIA TOKEN PER EMAIL
                final String host = "smtp.gmail.com";
                final String port = "465";
                final String username = "omarbattan@gmail.com";
                final String passwordAcc = "dbfcdokqbhuspuop";
                Properties props = System.getProperties();
                props.setProperty("mail.smtp.host", host);
                props.setProperty("mail.smtp.port", port);
                props.setProperty("mail.smtp.socketFactory.port", port);
                props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.setProperty("mail.smtp.auth", "true");
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.debug", "true");
                
                Session session = Session.getInstance(props, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, passwordAcc);
                }
                });
                Message msg = new MimeMessage(session);
                try {
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("omarbattan@gmail.com", false));
                    msg.setSubject("Reset Password");
                    msg.setText("Clicca sul link per il reset della password: \n"+"http://localhost:8080/SistemaSanitario/resetpassword?token="+token.token);
                    msg.setSentDate(new Date());
                    Transport.send(msg);
                } catch (MessagingException me) {
                    //TODO: log the exception
                    me.printStackTrace(System.err);
                }
                
                LOGGER.log(Level.INFO, "Reset password token: {0}", token.token );

                token.token = TokenUtil.getHashSHA256(token.token);

                resetTokenDao.create(token);           

            } catch (SQLException ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("email", email);
        RequestDispatcher dispatcher = getServletContext().
        getRequestDispatcher("/WEB-INF/forgotPassword.jsp");
        dispatcher.forward(request, response);     
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
