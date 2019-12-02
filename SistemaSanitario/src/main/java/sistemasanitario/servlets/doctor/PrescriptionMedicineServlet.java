
package sistemasanitario.servlets.doctor;

import com.j256.ormlite.dao.Dao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.entities.Medicina;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneMedicina;

@WebServlet(name = "PrescriptionMedicineServlet", urlPatterns = {"/services/doctor/prescription_medicine"})
public class PrescriptionMedicineServlet extends HttpServlet {
    
    Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)getServletContext().getAttribute("prescrizioneMedicinaDao");
    }

    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
        Medico medico = (Medico)getServletContext().getAttribute("medico");
      
        Integer idPaziente=0, idMedicina=0, quantita=0;
        try{
            idPaziente =  Integer.valueOf(request.getParameter("idPaziente"));
            idMedicina = Integer.valueOf(request.getParameter("idMedicina"));
            quantita = Integer.valueOf(request.getParameter("quantita"));
        }
        catch(Exception ex){
            response.sendError(400);
        }
        
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
           response.sendError(500);
        }
    }                    
}
