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
import sistemasanitario.config.DatabaseConfig;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.MedicoSpecialista;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.Report;
import sistemasanitario.entities.ResetPasswordToken;
import sistemasanitario.entities.Ssp;
import sistemasanitario.entities.User;
import sistemasanitario.utils.pdf.PrescriptionMedicinePDFUtil;

public class WebAppContextListener  implements ServletContextListener{

    private static final Logger LOGGER = Logger.getLogger(WebAppContextListener.class.getName());
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            /*JdbcConnectionSource con = new JdbcConnectionSource(
                    sce.getServletContext().getInitParameter("DBURL"),
                     "webapp", "bufalo123");*/
            
            DatabaseConfig dbConfig = DatabaseConfig.getInstance();
            JdbcConnectionSource con = new JdbcConnectionSource(dbConfig.getUrl(),
                    dbConfig.getUsername(), dbConfig.getPassword());
     
            
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
            
            Dao<MedicoSpecialista, Integer> medicoSpecialistaDao = DaoManager.createDao(con, MedicoSpecialista.class);
            sce.getServletContext().setAttribute("medicoSpecialistaDao", medicoSpecialistaDao);
            
            Dao<Ssp, Integer> sspDao = DaoManager.createDao(con, Ssp.class);
            sce.getServletContext().setAttribute("sspDao", sspDao);
            
            Dao<EsamePrescrivibile, Integer> esamePrescrivibileDao = DaoManager.createDao(con, EsamePrescrivibile.class);
            sce.getServletContext().setAttribute("esamePrescrivibileDao", esamePrescrivibileDao);
            
            Dao<Medicina, Integer> medicinaDao = DaoManager.createDao(con, Medicina.class);
            sce.getServletContext().setAttribute("medicinaDao", medicinaDao);
            
            Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao = DaoManager.createDao(con, PrescrizioneEsame.class);
            sce.getServletContext().setAttribute("prescrizioneEsameDao", prescrizioneEsameDao);
            
            Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao = DaoManager.createDao(con, PrescrizioneMedicina.class);
            sce.getServletContext().setAttribute("prescrizioneMedicinaDao", prescrizioneMedicinaDao);
            
            Dao<Report, Integer> reportDao = DaoManager.createDao(con, Report.class); 
            sce.getServletContext().setAttribute("reportDao", reportDao);
            
            sce.getServletContext().setAttribute("medicine", medicinaDao.queryForAll());
            sce.getServletContext().setAttribute("esami", esamePrescrivibileDao.queryForAll());
            
            PrescriptionMedicinePDFUtil.init(sce.getServletContext());
               
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error initialize DAO", ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error get database url configuration", ex);
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