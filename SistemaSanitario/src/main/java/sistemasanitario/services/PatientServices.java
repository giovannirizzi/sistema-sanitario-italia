package sistemasanitario.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.servlets.PasswordTest;
import static sistemasanitario.utils.GeneralUtil.getUserSession;

@Path("patient")
public class PatientServices {

    @Context
    private HttpServletRequest request;
    
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private Dao<Medico, Integer> medicoDao;
    private Dao<Paziente, Integer> pazienteDao;
    
    public PatientServices() {
    }
    
    @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
            medicoDao =  (Dao<Medico, Integer>) servletContext.getAttribute("medicoDao");
            pazienteDao = (Dao<Paziente, Integer>) servletContext.getAttribute("pazienteDao");
        }
    }
    
    //CHANGE DOCTOR
    
    @XmlRootElement
    public class DoctorId {
        @XmlElement Integer id;
    }

    @POST
    @Path("/changedoctor")
    public Response changeDoctor(@FormParam("idMedico") Integer idMedico) {
        
        HttpSession session = getUserSession(request);
        Paziente paziente = (Paziente)session.getAttribute("paziente");
        
        if(paziente == null)
            return Response.serverError().build();
        
        QueryBuilder queryBuilder = medicoDao.queryBuilder();
       
        try {
            List<Medico> newMedico = queryBuilder.where()
                    .eq("provincia", paziente.getProvincia())
                    .and()
                    .idEq(idMedico)
                    .query();
            
            if(newMedico.size()>0){
                
                UpdateBuilder<Paziente, Integer> updateBuilder = pazienteDao.updateBuilder();
                updateBuilder.where().idEq(paziente.getId());
                updateBuilder.updateColumnValue("idMedico", newMedico.get(0).getId());
                updateBuilder.update();
                paziente.setMedicoBase(newMedico.get(0));
                
                session.setAttribute("paziente", paziente);
            }  
            
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
      
        return Response.ok().build();
    }
}
