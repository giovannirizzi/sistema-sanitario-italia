package sistemasanitario.listeners;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.MedicoSpecialista;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.Ssp;
import sistemasanitario.entities.User;
import sistemasanitario.servlets.PasswordTest;

public class AuthenticatedUserListener{
    
    private Dao<Paziente, Integer> pazienteDao;
    private Dao<Medico, Integer> medicoDao;
    private Dao<MedicoSpecialista, Integer> medicoSpecialistaDao;
    private Dao<Medicina, Integer> medicinaDao;
    private Dao<Ssp, Integer> sspDao;
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    public AuthenticatedUserListener(ServletContext context){
        
        pazienteDao = (Dao<Paziente, Integer>)context.getAttribute("pazienteDao");
        medicoDao = (Dao<Medico, Integer>)context.getAttribute("medicoDao");
        sspDao = (Dao<Ssp, Integer>)context.getAttribute("sspDao");
        medicoSpecialistaDao = (Dao<MedicoSpecialista, Integer>)context.getAttribute("medicoSpecialistaDao");
        medicinaDao = (Dao<Medicina, Integer>)context.getAttribute("medicinaDao");
    }
    
    public void onNewUserAuthenticated(HttpSession session, User user) throws SQLException{
        
        String headerUserName = null;
        
        switch(user.getType()){
            
            case PAZIENTE:
            {
                Paziente paziente = pazienteDao.queryForId(user.getId());
                if(paziente != null){
                    session.setAttribute("paziente", paziente);
                    headerUserName = paziente.getNome() + " " + paziente.getCognome();
                }
                break;
            }
                
            case SS_PROVINCIALE:
            {
                Ssp ssp = sspDao.queryForId(user.getId());
                if(ssp != null){
                    session.setAttribute("ssp", ssp);
                    headerUserName = ssp.getNome();
                }
                break;
            }
            
            case MEDICO_BASE:
            {
                Medico medico = medicoDao.queryForId(user.getId());
                if(medico != null){
                    session.setAttribute("medico", medico);
                    headerUserName = medico.getNome() + " " + medico.getCognome();
                }
                break; 
            }
            
            case MEDICO_SPECIALISTA:
            {
                MedicoSpecialista medico = medicoSpecialistaDao.queryForId(user.getId());
                if(medico != null){
                    session.setAttribute("medicoSpecialista", medico);
                    headerUserName = medico.getNome() + " " + medico.getCognome();
                }
                break; 
            }  
        }
        if(headerUserName != null)
            session.setAttribute("headerUserName", headerUserName.toUpperCase());     
    }
}
