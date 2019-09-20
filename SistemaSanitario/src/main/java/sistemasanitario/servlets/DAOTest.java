/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.servlets;

import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.entities.*;

/**
 *
 * @author Giovanni
 */
public class DAOTest extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PasswordTest.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Test DAO</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>TEST DAO</h1>");

        JdbcConnectionSource con = (JdbcConnectionSource)getServletContext()
                .getAttribute("connectionSource");
        
        Dao<User, Integer> usersDao = DaoManager.createDao(con, User.class);
        try(CloseableWrappedIterable<User> iterable = usersDao.getWrappedIterable()) {
            for(User u : iterable){
               out.println("<h3>"+u.getId()+ " - "+u.getUsername()+"</h3><br>");
            }
        }
        
        Dao<ResetPasswordToken, Integer> tokensDao = DaoManager.createDao(con, ResetPasswordToken.class);
        try(CloseableWrappedIterable<ResetPasswordToken> iterable = tokensDao.getWrappedIterable()) {
            for(ResetPasswordToken r : iterable){
               out.println("<h3>"+r.getUser().getId()+ " - "+r.getCreatedTime()+"</h3><br>");
            }
        }
        
        Dao<AuthToken, Integer> authDao = DaoManager.createDao(con, AuthToken.class);
        try(CloseableWrappedIterable<AuthToken> iterable = authDao.getWrappedIterable()) {
            for(AuthToken a : iterable){
               out.println("<h3>"+a.getSelector()+ " - "+a.getValidator()+"</h3><br>");
            }
        }
  
        out.println("</body>");
        out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTest.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
