package sistemasanitario.services;

import com.j256.ormlite.dao.Dao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sistemasanitario.entities.User;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import static sistemasanitario.utils.GeneralUtil.getUserSession;
import sistemasanitario.utils.xls.PrescriptionExamXLSUtil;
import sistemasanitario.utils.xls.PrescriptionMedicineXLSUtil;

@Path("/ssp")
public class SspServices {

    @Context
    private UriInfo context;
    
    @Context
    private HttpServletRequest request;
    private Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    private Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    private static final Logger LOG = Logger.getLogger(SspServices.class.getName());

    public SspServices() {
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

    @GET
    @Path("/report_exams")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getXslReportExams(@QueryParam("date") String date){
        
        Response.ResponseBuilder response;
        HttpSession session = getUserSession(request);
        User user = (User)session.getAttribute("user");
        
        if(date == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        if(user.getType() == User.UserType.SS_PROVINCIALE){
            
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateTmp = parser.parse(date);
                String fileName = "Report_Exam_" + date;
                List<PrescrizioneEsame> exams;

                exams = getExamsInADate(date);

                response = Response
                            .ok()
                            .entity(PrescriptionExamXLSUtil.generate(exams).toByteArray())
                            .header("content-disposition", "attachment; filename = " + fileName + ".xls");

            } catch (ParseException ex) {
                response = Response.status(Response.Status.BAD_REQUEST); 
            }
            catch (SQLException | NullPointerException | IOException ex) {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR); 
            }
        }
        else{
            response = Response.status(Response.Status.UNAUTHORIZED); 
        }
        
        return response.build();
    }
    
    @GET
    @Path("/report_medicine")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getXslReportMedicine(@QueryParam("date") String date){
        
        Response.ResponseBuilder response;
        HttpSession session = getUserSession(request);
        User user = (User)session.getAttribute("user");
        
        if(date == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        if(user.getType() == User.UserType.SS_PROVINCIALE){
            
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateTmp = parser.parse(date);
                String fileName = "Report_Medicine_" + date;
                List<PrescrizioneMedicina> medicine;

                medicine = getMedicineInADate(date);

                response = Response
                            .ok()
                            .entity(PrescriptionMedicineXLSUtil.generate(medicine).toByteArray())
                            .header("content-disposition", "attachment; filename = " + fileName + ".xls");

            } catch (ParseException ex) {
                response = Response.status(Response.Status.BAD_REQUEST); 
            }
            catch (SQLException | NullPointerException | IOException ex) {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR); 
            }
        }
        else{
            response = Response.status(Response.Status.UNAUTHORIZED); 
        }
        
        return response.build();
    }
   
    
    List<PrescrizioneEsame> getExamsInADate(String date) throws SQLException, ParseException{

        Date date1, date2;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd H:mm:s");
        date1 = parser.parse(date + " 0:0:0");
        date2 = parser.parse(date + " 23:59:59");
        
        return prescrizioneEsameDao
                .queryBuilder()
                .where()
                .between("data", date1, date2)
                //.and()
                //.in("idMedico", subQueryBuilder)
                .query();
    }
    
    List<PrescrizioneMedicina> getMedicineInADate(String date) throws SQLException, ParseException{

        Date date1, date2;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd H:mm:s");
        date1 = parser.parse(date + " 0:0:0");
        date2 = parser.parse(date + " 23:59:59");
        
        return prescrizioneMedicinaDao
                .queryBuilder()
                .where()
                .between("data", date1, date2)
                //.and()
                //.in("idMedico", subQueryBuilder)
                .query();
    }
}
