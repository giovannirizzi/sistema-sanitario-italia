package sistemasanitario.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.Report;
import static sistemasanitario.utils.GeneralUtil.getUserSession;


@Path("/doctor")
public class DoctorServices {

    @Context
    private UriInfo context;
    
    @Context
    private HttpServletRequest request;
    private Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    private Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    private Dao<Report, Integer> reportDao;

    public DoctorServices() {
    }
    
       @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
            prescrizioneEsameDao = (Dao<PrescrizioneEsame, Integer>)
                    servletContext.getAttribute("prescrizioneEsameDao");
            prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)
                    servletContext.getAttribute("prescrizioneMedicinaDao");
            reportDao = (Dao<Report, Integer>)
                    servletContext.getAttribute("reportDao");
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
    
    @POST
    @Path("/report/{idPrescrizione}")
    public Response writeReport(@PathParam("idPrescrizione") Integer idPrescrizione) {
        
        HttpSession session = getUserSession(request);
        
        try {
            //Prendo una prescrizione e la inserisco nel report
            QueryBuilder<PrescrizioneEsame, Integer> queryBuilder = prescrizioneEsameDao.queryBuilder();
            List <PrescrizioneEsame> prescrizione = queryBuilder.where().idEq(idPrescrizione).query();
            
            Report report = new Report();

            report.setPrescrizioneEsame(prescrizione.get(0));
            report.setDescrizione("Ciao bello stai per morire!");
            
            reportDao.create(report);
            
            //Prendo idReport e lo metto nella tabella PrescrizioneEsame
            QueryBuilder<Report, Integer> preEsameBuilder = reportDao.queryBuilder();
            List <Report> reports = preEsameBuilder.where().in("idPrescrizione", idPrescrizione).query();
            
            UpdateBuilder<PrescrizioneEsame, Integer> updateBuilder = prescrizioneEsameDao.updateBuilder();
            updateBuilder.where().idEq(idPrescrizione);
            updateBuilder.updateColumnValue("idReport", reports.get(0).getId());
            updateBuilder.update();
                
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
