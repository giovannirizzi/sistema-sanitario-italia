package sistemasanitario.servlets;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.valueOf;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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

/**
 *
 * @author marco
 */

@WebServlet(name = "DashboardServlet", urlPatterns = {"/services/dashboard"})
public class PatientCardServlet extends HttpServlet {

    private Dao<Paziente, Integer> pazienteDao;
    private Dao<Medico, Integer> medicoDao;
    private Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    private Gson gson = new Gson();
    
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
            JSONObject prescrizioni = new JSONObject();    
            QueryBuilder queryBuilder = prescrizioneEsameDao.queryBuilder();
            List<PrescrizioneEsame> prescrizioniList = queryBuilder.where().eq("idPaziente", id).query();

            //ResultSet rs = statement.executeQuery("select ESAME_PRESCRIVIBILE.nome as nomeEsame,ESAME_PRESCRIVIBILE.descrizione as descrizioneEsame, PRESCRIZIONE_ESAME.data from PAZIENTE left join PRESCRIZIONE_ESAME on PAZIENTE.id = PRESCRIZIONE_ESAME.idPaziente inner join ESAME_PRESCRIVIBILE on PRESCRIZIONE_ESAME.idEsame = ESAME_PRESCRIVIBILE.id where PAZIENTE.id = " + id + ";");
            JSONArray array = new JSONArray();

            for(PrescrizioneEsame prescrizione : prescrizioniList){
                JSONObject item = new JSONObject();
                item.put("name", prescrizione.getEsame().getNome());
                item.put("description", prescrizione.getEsame().getDescrizione());
                SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = mdyFormat.format(prescrizione.getData());
                item.put("date", date);
                array.add(item);
            }
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
                item.put("lastMedicine", "pra lastMedicine");
                item.put("lastVisit", "pra lastVisit");

                /*pazienteDao.queryR
                ResultSet rs1 = statement.executeQuery("SELECT max(PRESCRIZIONE_MEDICINA.data) AS dataMedicina,max(PRESCRIZIONE_ESAME.data) AS dataEsame FROM PAZIENTE INNER JOIN PRESCRIZIONE_MEDICINA ON PAZIENTE.id = PRESCRIZIONE_MEDICINA.idPaziente AND PAZIENTE.idMedico = " + medico.getId() + " INNER JOIN PRESCRIZIONE_ESAME ON PAZIENTE.id = PRESCRIZIONE_ESAME.idPaziente WHERE PAZIENTE.ID = " + rs.getString("id") + ";");
                while (rs1.next()){
                    item.put("lastMedicine", rs1.getString("dataMedicina"));
                    item.put("lastVisit", rs1.getString("dataEsame"));
                }*/

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
