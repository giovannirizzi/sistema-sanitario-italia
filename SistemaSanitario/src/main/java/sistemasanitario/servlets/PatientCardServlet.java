package sistemasanitario.servlets;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.User;
import static sistemasanitario.entities.User.UserType.MEDICO_BASE;
import static sistemasanitario.entities.User.UserType.MEDICO_SPECIALISTA;
import static sistemasanitario.entities.User.UserType.SS_PROVINCIALE;
import static sistemasanitario.utils.GeneralUtil.getUserSession;

@WebServlet(name = "PatientCardServlet", urlPatterns = {"/services/patientcard"})
public class PatientCardServlet extends HttpServlet {

    private Dao<Paziente, Integer> pazienteDao;
    private Dao<Medico, Integer> medicoDao;
    private Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        pazienteDao = (Dao<Paziente, Integer>)getServletContext().getAttribute("pazienteDao");
        medicoDao = (Dao<Medico, Integer>)getServletContext().getAttribute("medicoDao");
        prescrizioneEsameDao = (Dao<PrescrizioneEsame, Integer>)getServletContext().getAttribute("prescrizioneEsameDao");
    }
    
    private void getPatientInfo(String patientId, HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        HttpSession session = getUserSession(request);
        if(session == null || ((User)session.getAttribute("user")).getType() == User.UserType.PAZIENTE){
            response.sendError(401);
            return;
        }

        int id;
        try{
            id = Integer.valueOf(patientId);
        }
        catch(NumberFormatException ex){
            response.sendError(400);
            return;
        }
        
        try {
               
            GenericRawResults<String[]> rawResults = prescrizioneEsameDao.queryRaw("select ESAME_PRESCRIVIBILE.nome,REPORT.descrizione, PRESCRIZIONE_ESAME.data from PAZIENTE left join PRESCRIZIONE_ESAME on PAZIENTE.id = PRESCRIZIONE_ESAME.idPaziente inner join ESAME_PRESCRIVIBILE on PRESCRIZIONE_ESAME.idEsame = ESAME_PRESCRIVIBILE.id LEFT JOIN REPORT on PRESCRIZIONE_ESAME.idReport = REPORT.id where PAZIENTE.id = " + id + ";");
            List<String[]> results = rawResults.getResults();
            
            JSONArray array = new JSONArray();
            for(String[] resultArray : results){
                
                JSONObject item = new JSONObject();
                item.put("name", resultArray[0]);
                item.put("description", resultArray[1]);
                item.put("date", resultArray[2]);
                array.add(item);
            }
            JSONObject prescrizioni = new JSONObject(); 
            prescrizioni.put("exams", array);

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(prescrizioni);
            out.flush();
             
        } catch (SQLException ex) {
            getLogger(LoginServlet.class.getName()).log(SEVERE, null, ex);
            response.sendError(500);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        
        HttpSession session = getUserSession(request);
        if(session == null){
            response.sendError(401);
            return;
        }
        
        if(id != null){
            getPatientInfo(id, request, response);
            return;
        }
        
        //Get patient list
        User user = (User)session.getAttribute("user");
        List<Paziente> pazientiList;
        JSONObject pazienti = new JSONObject();
        QueryBuilder queryBuilder = pazienteDao.queryBuilder();

        try{
 
            if(user.getType() == MEDICO_BASE){
                Medico medico = (Medico)session.getAttribute("medico");
                pazientiList = queryBuilder.where().eq("idMedico", medico.getId()).query();
            }
            else if(user.getType() == MEDICO_SPECIALISTA || user.getType() == SS_PROVINCIALE){
                pazientiList = pazienteDao.queryForAll();
            }
            else{
                response.sendError(401);
                return;
            }
            
            JSONArray array = new JSONArray();

            for(Paziente paziente : pazientiList){

                JSONObject item = new JSONObject();
                item.put("id", paziente.getId());
                item.put("name", paziente.getNome());
                item.put("surname", paziente.getCognome());
                SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dataNascita = mdyFormat.format(paziente.getDataNascita());
                item.put("birthDate", dataNascita);
                item.put("birthPlace", paziente.getLuogoNascita());
                item.put("province", paziente.getProvincia());
                item.put("cf", paziente.getCf());
                item.put("photo", paziente.getFoto());

                GenericRawResults<String[]> rawResults = prescrizioneEsameDao.queryRaw("SELECT max(PRESCRIZIONE_MEDICINA.data),max(PRESCRIZIONE_ESAME.data) FROM PAZIENTE INNER JOIN PRESCRIZIONE_MEDICINA ON PAZIENTE.id = PRESCRIZIONE_MEDICINA.idPaziente INNER JOIN PRESCRIZIONE_ESAME ON PAZIENTE.id = PRESCRIZIONE_ESAME.idPaziente WHERE PAZIENTE.ID = " + paziente.getId() + ";");
                List<String[]> results = rawResults.getResults();
                for(String[] resultArray : results){
                    item.put("lastMedicine", resultArray[0]);
                    item.put("lastVisit", resultArray[1]);   
                }

                array.add(item); 
            }
            pazienti.put("patient", array);

        } catch(SQLException ex){
            getLogger(PatientCardServlet.class.getName()).log(SEVERE, "SQL Error get patients", ex);
            response.sendError(500);
            return;
        }
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(pazienti);
        out.flush();
    }
}
