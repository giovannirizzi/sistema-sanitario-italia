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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;

/**
 *
 * @author marco
 */

@WebServlet(name = "DashboardServlet", urlPatterns = {"/services/dashboard"})
public class DashboardServlet extends HttpServlet {

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
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Medico medico = (Medico)request.getSession().getAttribute("medico");
        String id = request.getParameter("id");
        
        if(id == null){
            QueryBuilder<Paziente, Integer> queryBuilder = pazienteDao.queryBuilder();
            PreparedQuery<Paziente> getPazienteByIdMedicoQuery;

            try {
                getPazienteByIdMedicoQuery = queryBuilder.where().eq("idMedico", medico.getId()).prepare();
                List<Paziente> pazienti = pazienteDao.query(getPazienteByIdMedicoQuery);
                
                String listaPazienti = this.gson.toJson(pazienti);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(listaPazienti);
                out.flush();
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            QueryBuilder<PrescrizioneEsame, Integer> queryBuilder2 = prescrizioneEsameDao.queryBuilder();
            PreparedQuery<PrescrizioneEsame> getPrescrizioneEsameByIdPazienteQuery;
            
            try {
                
                JSONObject prescrizioni = new JSONObject();
                
                Connection connection = DriverManager.getConnection("jdbc:mysql://sistemasanitariodb.c16q8irjv9nd.us-east-2.rds.amazonaws.com/sistemasanitario", "admin", "Cinghiale123$");
                // Creazione oggetto Statement
                Statement statement = connection.createStatement();

                // Esecuzione di un comando sul DB
                ResultSet rs = statement.executeQuery("select PAZIENTE.nome,PAZIENTE.cognome,ESAME_PRESCRIVIBILE.nome as nomeEsame,ESAME_PRESCRIVIBILE.descrizione, PRESCRIZIONE_ESAME.data from PAZIENTE left join PRESCRIZIONE_ESAME on PAZIENTE.id = PRESCRIZIONE_ESAME.idPaziente inner join ESAME_PRESCRIVIBILE on PRESCRIZIONE_ESAME.idEsame = ESAME_PRESCRIVIBILE.id where PAZIENTE.id = " + id + ";");
                
                JSONArray array = new JSONArray();
                JSONObject item = new JSONObject();
                
                // Elaborazione del risultato
                while (rs.next()){
                    item.put("name", rs.getString("nomeEsame"));
                    item.put("description", rs.getString("descrizione"));
                    item.put("date", rs.getString("data"));
                    array.put(item);
                }
                
                prescrizioni.put("exams", array);
                
                rs = statement.executeQuery("select nome,cognome from PAZIENTE where PAZIENTE.id = " + id + ";");
                
                while (rs.next()){
                    prescrizioni.put("name", rs.getString("nome"));
                    prescrizioni.put("surname", rs.getString("cognome"));
                }
                
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
