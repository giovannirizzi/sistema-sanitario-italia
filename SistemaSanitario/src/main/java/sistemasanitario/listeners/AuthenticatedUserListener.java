package sistemasanitario.listeners;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.MedicoSpecialista;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.Ssp;
import sistemasanitario.entities.User;

public class AuthenticatedUserListener{
    
    private final Dao<Paziente, Integer> pazienteDao;
    private final Dao<Medico, Integer> medicoDao;
    private final Dao<MedicoSpecialista, Integer> medicoSpecialistaDao;
    private final Dao<Medicina, Integer> medicinaDao;
    private final Dao<Ssp, Integer> sspDao;
    private static final Logger LOGGER = Logger.getLogger(AuthenticatedUserListener.class.getName());
    
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
                    session.setAttribute("pazienti", pazienteDao.queryForAll());
                    headerUserName = ssp.getNome();
                }
                break;
            }
            
            case MEDICO_BASE:
            {
                Medico medico = medicoDao.queryForId(user.getId());
                if(medico != null){
                    session.setAttribute("medico", medico);
                    QueryBuilder<Paziente, Integer> queryBuilder = pazienteDao.queryBuilder();
          
                    session.setAttribute("pazienti", queryBuilder.
                            where().
                            eq("idMedico", medico.getId())
                            .query());
                    headerUserName = medico.getNome() + " " + medico.getCognome();
                }
                break; 
            }
            
            case MEDICO_SPECIALISTA:
            {
                MedicoSpecialista medico = medicoSpecialistaDao.queryForId(user.getId());
                if(medico != null){
                    session.setAttribute("medicoSpecialista", medico);
                    session.setAttribute("pazienti", pazienteDao.queryForAll());
                    headerUserName = medico.getNome() + " " + medico.getCognome();
                }
                break; 
            }  
        }
        if(headerUserName != null)
            session.setAttribute("headerUserName", headerUserName.toUpperCase());     
    }
}
