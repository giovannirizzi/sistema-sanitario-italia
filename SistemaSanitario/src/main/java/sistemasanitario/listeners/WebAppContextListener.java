package sistemasanitario.listeners;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebAppContextListener  implements ServletContextListener{

    private static final Logger LOGGER = Logger.getLogger(WebAppContextListener.class.getName());
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            JdbcConnectionSource connectionSource = new JdbcConnectionSource(
                    sce.getServletContext().getInitParameter("DBURL"),
                     "webapp", "bufalo123");  
            
            sce.getServletContext().setAttribute("connectionSource", connectionSource);
            
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