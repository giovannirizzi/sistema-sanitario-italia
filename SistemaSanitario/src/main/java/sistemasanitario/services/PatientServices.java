package sistemasanitario.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.MedicoSpecialista;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.Report;
import sistemasanitario.entities.Ssp;
import sistemasanitario.servlets.PasswordTest;
import static sistemasanitario.utils.GeneralUtil.getUserSession;
import sistemasanitario.utils.pdf.PrescriptionMedicinePDFUtil;

@Path("patient")
public class PatientServices {

    @Context
    private HttpServletRequest request;
    
    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    private static Dao<Medico, Integer> medicoDao;
    private static Dao<EsamePrescrivibile, Integer> esameDao;
    private static Dao<Paziente, Integer> pazienteDao;
    private static Dao<Report, Integer> reportDao;
    private static Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    private static Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    private static Dao<MedicoSpecialista, Integer> medicoSpecialistaDao;
    private static Dao<Ssp, Integer> sspDao;
    
    public PatientServices() {
    }
    
    @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
            medicoDao =  (Dao<Medico, Integer>) servletContext.getAttribute("medicoDao");
            pazienteDao = (Dao<Paziente, Integer>) servletContext.getAttribute("pazienteDao");
            esameDao = (Dao<EsamePrescrivibile, Integer>) servletContext.getAttribute("esamePrescrivibileDao");
            reportDao = (Dao<Report, Integer>) servletContext.getAttribute("reportDao");
            prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)servletContext.getAttribute("prescrizioneMedicinaDao");
            prescrizioneEsameDao = (Dao<PrescrizioneEsame, Integer>)servletContext.getAttribute("prescrizioneEsameDao");
            medicoSpecialistaDao = (Dao<MedicoSpecialista, Integer>)servletContext.getAttribute("medicoSpecialistaDao");
            sspDao = (Dao<Ssp, Integer>)servletContext.getAttribute("sspDao");  
        }
    }
    
    //CHANGE DOCTOR
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
    
    //GET EXAM INFO
    @GET
    @Path("/examInfo/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response examInfo(@PathParam("id") Integer esameId) {
        
        Response.ResponseBuilder response;
        if (esameId == null) {
            // EsameId is missing
            response = Response.status(Response.Status.BAD_REQUEST);
        } else {
            try {
                QueryBuilder<EsamePrescrivibile, Integer> queryBuilder = esameDao.queryBuilder();
                List <EsamePrescrivibile> esame = queryBuilder.where().idEq(esameId).query();
                String descrizione = "";
                if(esame.size() > 0){
                    descrizione = esame.get(0).getDescrizione();
                    descrizione = descrizione.replace("\n", "").replace("\r", "");
                } 
                response = Response.ok(descrizione, MediaType.TEXT_PLAIN);
                
            } catch (SQLException ex) {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return response.build();
    }
    
    //GET REPORT
    @GET
    @Path("/report/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportById(@PathParam("id") Integer reportId) {
        
        Response.ResponseBuilder response;
                    
        if (reportId == null) {
            // Report Id is missing
            response = Response.status(Response.Status.BAD_REQUEST);
        } else {
            try {
                Report report = reportDao.queryForId(reportId);
                prescrizioneEsameDao.refresh(report.getPrescrizioneEsame());
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                String data = dateFormat.format(report.getData());
                report.setDataString(data);
                
                if(report.getPrescrizioneEsame().getMedicoSpe() != null){
                    MedicoSpecialista spe = report.getPrescrizioneEsame().getMedicoSpe();
                    medicoSpecialistaDao.refresh(spe);
                    report.setAutore(spe.getNome() + " " + spe.getCognome());    
                }
                else if(report.getPrescrizioneEsame().getSsp() != null){
                    Ssp ssp = report.getPrescrizioneEsame().getSsp();
                    sspDao.refresh(ssp);
                    report.setAutore(ssp.getNome());    
                }
                report.setEsame(report.getPrescrizioneEsame().getEsame().getNome());
                
                report.setData(null);
                report.setPrescrizioneEsame(null);
                response = Response.ok(report);
            } catch (SQLException ex) {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return response.build();
    }
    
    //GET PRESCRIPTION MEDICINE PDF
    @GET
    @Path("/prescriptionmedicine/{id}")
    @Produces("application/pdf")
    public javax.ws.rs.core.Response getPrescriptionMedicine(@PathParam("id") Integer idPrescription) {
        
        Response.ResponseBuilder response;
        PrescrizioneMedicina prescrizioneMedicina = null;
        
        if (idPrescription == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
       
        Paziente paziente = (Paziente)getUserSession(request).getAttribute("paziente");
        boolean exists = false;        
        QueryBuilder queryBuilder = prescrizioneMedicinaDao.queryBuilder();
        List<PrescrizioneMedicina> prescrizioniPaziente;

        try {
            prescrizioniPaziente = queryBuilder.where().eq("idPaziente", paziente.getId()).query();

            for(PrescrizioneMedicina prescrizione : prescrizioniPaziente){

                if(prescrizione.getId() == idPrescription){
                    exists = true;
                    prescrizioneMedicina = prescrizione;
                }
            }

             if(exists && prescrizioneMedicina != null){
                pazienteDao.refresh(prescrizioneMedicina.getPaziente());
                response = Response.ok(PrescriptionMedicinePDFUtil.generatePDF(prescrizioneMedicina).toByteArray());
                response.type("application/pdf");
                response.header("Content-Disposition",  "filename = Prescription_Medicine_" + prescrizioneMedicina.getId() + ".pdf"); 

            }
            else
                response = Response.status(Response.Status.UNAUTHORIZED);

        } catch (SQLException ex) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
      
        return response.build();
    }
}
