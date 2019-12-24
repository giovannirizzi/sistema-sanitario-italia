package sistemasanitario.services;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import static sistemasanitario.utils.GeneralUtil.getUserSession;


@Path("/doctor")
public class DoctorServices {

    @Context
    private UriInfo context;
    
    @Context
    private HttpServletRequest request;
    private Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    private Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;

    public DoctorServices() {
    }
    
       @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
            prescrizioneEsameDao = (Dao<PrescrizioneEsame, Integer>)
                    servletContext.getAttribute("prescrizioneEsameDao");
            prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)
                    servletContext.getAttribute("prescrizioneMedicinaDao");
        }
    }

    
    @POST
    @Path("/exam_prescription")
    public Response prescribeExam(@FormParam("idPaziente") Integer idPaziente,
           @FormParam("idEsame") Integer idEsame) {
        
        HttpSession session = getUserSession(request);
        Medico medico = (Medico)session.getAttribute("medico");
        
        if(medico == null)
            return Response.serverError().build();
        
        PrescrizioneEsame prescrizione = new PrescrizioneEsame();
        
        Paziente paziente = new Paziente();
        paziente.id = idPaziente;
        
        EsamePrescrivibile esame = new EsamePrescrivibile();
        esame.setId(idEsame);
        
        prescrizione.setEsame(esame);
        prescrizione.setMedico(medico);
        prescrizione.setPaziente(paziente);
        
        try {
            prescrizioneEsameDao.create(prescrizione);
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
    
    @POST
    @Path("/medicine_prescription")
    public Response prescribeMedicine(@FormParam("idPaziente") Integer idPaziente,
           @FormParam("idMedicina") Integer idMedicina,
           @FormParam("quantita") Integer quantita) {
        
        HttpSession session = getUserSession(request);
        Medico medico = (Medico)session.getAttribute("medico");
        
        if(medico == null)
            return Response.serverError().build();
        
         PrescrizioneMedicina prescrizione = new PrescrizioneMedicina();
        
        prescrizione.setQuantita(quantita);
        
        Medicina medicina = new Medicina();
        medicina.setId(idMedicina);
        
        Paziente paziente = new Paziente();
        paziente.id = idPaziente;
        
        prescrizione.setMedicina(medicina);
        prescrizione.setMedico(medico);
        prescrizione.setPaziente(paziente);
        
        try {
            prescrizioneMedicinaDao.create(prescrizione);
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
