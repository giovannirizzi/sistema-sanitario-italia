/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        
        HttpSession session = getUserSession(request);
        if(session == null){
            response.sendError(401);
            return;
        }

        if(id == null){
            
            User user = (User)session.getAttribute("user");

            if(user.getType() == User.UserType.MEDICO_BASE){
                Medico medico = (Medico)session.getAttribute("medico");
                
                try {
                    
                    JSONObject pazienti = new JSONObject();
                    QueryBuilder queryBuilder = pazienteDao.queryBuilder();
                    List<Paziente> pazientiList = queryBuilder.where().eq("idMedico", medico.getId()).query();

                    JSONArray array = new JSONArray();
                    
                    for(Paziente paziente : pazientiList){
                        
                        JSONObject item = new JSONObject();
                        item.put("id", paziente.getId());
                        item.put("name", paziente.getNome());
                        item.put("surname", paziente.getCognome());
                        item.put("birthDate", paziente.getDataNascita().getTime());
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
                    
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(pazienti);
                    out.flush();
                     
                } catch (SQLException ex) {
                    Logger.getLogger(PatientCardServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }else{
                
                if(user.getType() == User.UserType.MEDICO_SPECIALISTA || user.getType() == User.UserType.SS_PROVINCIALE){
                    
                    try {
                        
                        JSONObject pazienti = new JSONObject();

                        List<Paziente> pazientiList = pazienteDao.queryForAll();

                        JSONArray array = new JSONArray();

                        for(Paziente paziente : pazientiList){
                        
                            JSONObject item = new JSONObject();
                            item.put("id", paziente.getId());
                            item.put("name", paziente.getNome());
                            item.put("surname", paziente.getCognome());
                            item.put("birthDate", paziente.getDataNascita().getTime());
                            item.put("birthPlace", paziente.getLuogoNascita());
                            item.put("province", paziente.getProvincia());
                            item.put("cf", paziente.getCf());
                            item.put("photo", paziente.getFoto());

                            /*pazienteDao.queryR
                            ResultSet rs1 = statement.executeQuery("SELECT max(PRESCRIZIONE_MEDICINA.data) AS dataMedicina,max(PRESCRIZIONE_ESAME.data) AS dataEsame FROM PAZIENTE INNER JOIN PRESCRIZIONE_MEDICINA ON PAZIENTE.id = PRESCRIZIONE_MEDICINA.idPaziente AND PAZIENTE.idMedico = " + medico.getId() + " INNER JOIN PRESCRIZIONE_ESAME ON PAZIENTE.id = PRESCRIZIONE_ESAME.idPaziente WHERE PAZIENTE.ID = " + rs.getString("id") + ";");
                            while (rs1.next()){
                                item.put("lastMedicine", rs1.getString("dataMedicina"));
                                item.put("lastVisit", rs1.getString("dataEsame"));
                            }*/

                            array.add(item); 
                        }

                        pazienti.put("patient", array);

                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        out.print(pazienti);
                        out.flush();
                    
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            
        } else {
            
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
                    item.put("date", new String(prescrizione.getData().toString()));
                    array.add(item);
                }
            
                prescrizioni.put("exams", array);
                
                Paziente paziente = pazienteDao.queryForId(Integer.valueOf(id));

                
                prescrizioni.put("name", paziente.getNome());
                prescrizioni.put("surname", paziente.getCognome());
                prescrizioni.put("birthDate", paziente.getDataNascita().getTime());
                prescrizioni.put("birthPlace", paziente.getLuogoNascita());
                prescrizioni.put("province", paziente.getProvincia());
                prescrizioni.put("cf", paziente.getCf());
                prescrizioni.put("photo", paziente.getFoto());

                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(prescrizioni);
                out.flush();

            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
