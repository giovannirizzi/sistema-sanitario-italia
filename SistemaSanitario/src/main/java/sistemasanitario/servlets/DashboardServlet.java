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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;

/**
 *
 * @author marco
 */

@WebServlet("/myservices/medico/pazienti")
public class DashboardServlet extends HttpServlet {

    private Dao<Paziente, Integer> pazienteDao;
    private Dao<Medico, Integer> medicoDao;
    private Gson gson = new Gson();
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        pazienteDao = (Dao<Paziente, Integer>)getServletContext().getAttribute("pazienteDao");
        medicoDao = (Dao<Medico, Integer>)getServletContext().getAttribute("medicoDao");
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
