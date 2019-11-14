/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.listeners;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.User;
import sistemasanitario.servlets.PasswordTest;

/**
 *
 * @author giovanni
 */
public class AuthenticatedUserListener{
    
    private Dao<Paziente, Integer> pazienteDao;
    private Dao<Medico, Integer> medicoDao;
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    public AuthenticatedUserListener(ServletContext context){
        
        pazienteDao = (Dao<Paziente, Integer>)context.getAttribute("pazienteDao");
        medicoDao = (Dao<Medico, Integer>)context.getAttribute("medicoDao");
    }
    
    public void onNewUserAuthenticated(HttpSession session, User user) throws SQLException{
        
        String headerUserName = null;
        
        switch(user.getType()){
            
            case PAZIENTE:
            {
                List<Paziente> pazienti = pazienteDao.queryForEq("idUtente", user.getId());
              
                if(pazienti != null && pazienti.size()>0){
                    Paziente paziente = pazienti.get(0);
                  
                    session.setAttribute("paziente", paziente);
                    headerUserName = paziente.getNome() + " " + paziente.getCognome();
                }
                else{
                    LOGGER.log(Level.INFO, "no pazienti");
                }
                break;
            }
                
            case SS_PROVINCIALE:
            {
                break;
            }
            
            default:
            {
                List<Medico> medici = medicoDao.queryForEq("idUtente", user.getId());
                if(medici != null && medici.size()>0){
                
                    Medico medico = medici.get(0);
                    session.setAttribute("medico", medico);
                    headerUserName = medico.getNome() + " " + medico.getCognome();
                }
                break; 
            }  
        }
        if(headerUserName != null)
            session.setAttribute("headerUserName", headerUserName.toUpperCase());     
    }
}
