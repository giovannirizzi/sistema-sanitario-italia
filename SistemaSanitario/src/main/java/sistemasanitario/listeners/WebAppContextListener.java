package sistemasanitario.listeners;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.ResetPasswordToken;
import sistemasanitario.entities.User;

public class WebAppContextListener  implements ServletContextListener{

    private static final Logger LOGGER = Logger.getLogger(WebAppContextListener.class.getName());
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String path = WebAppContextListener.class.getClassLoader()
                                  .getResource("logging.properties")
                                  .getFile();
        System.setProperty("java.util.logging.config.file", path);

        try {
            JdbcConnectionSource con = new JdbcConnectionSource(
                    sce.getServletContext().getInitParameter("DBURL"),
                     "webapp", "bufalo123");  
            
            sce.getServletContext().setAttribute("connectionSource", con);
            
            //Init DAO clesses
            Dao<AuthToken, Integer> tokensDao = DaoManager.createDao(con, AuthToken.class);
            sce.getServletContext().setAttribute("AuthTokensDao", tokensDao);
            
            Dao<User, Integer> usersDao = DaoManager.createDao(con, User.class);
            sce.getServletContext().setAttribute("UsersDao", usersDao);
            
            Dao<ResetPasswordToken, Integer> resetTokenDao = DaoManager.createDao(con, ResetPasswordToken.class);
            sce.getServletContext().setAttribute("resetTokenDao", resetTokenDao);
            
            Dao<Paziente, Integer> pazienteDao = DaoManager.createDao(con, Paziente.class);
            sce.getServletContext().setAttribute("pazienteDao", pazienteDao);
            
            Dao<Medico, Integer> medicoDao = DaoManager.createDao(con, Medico.class);
            sce.getServletContext().setAttribute("medicoDao", medicoDao);
            
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }  
  
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
        JdbcConnectionSource con = (JdbcConnectionSource)sce.getServletContext()
                .getAttribute("connectionSource");
        
         if (con != null){
            try {
                con.close();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
}