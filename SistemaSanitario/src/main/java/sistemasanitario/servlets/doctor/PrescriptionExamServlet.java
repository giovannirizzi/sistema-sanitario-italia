package sistemasanitario.servlets.doctor;

import com.j256.ormlite.dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import static sistemasanitario.utils.GeneralUtil.getUserSession;

@WebServlet(name = "PrescriptionExamServlet", urlPatterns = {"/services/doctor/prescription_exam"})
public class PrescriptionExamServlet extends HttpServlet {
    
    Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        prescrizioneEsameDao = (Dao<PrescrizioneEsame, Integer>)getServletContext().getAttribute("prescrizioneEsameDao");
    }

    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        
        Medico medico = (Medico)getUserSession(request).getAttribute("medico");
      
        Integer idPaziente=0, idEsame = 0;
        try{
            idPaziente =  Integer.valueOf(request.getParameter("idPaziente"));
            idEsame = Integer.valueOf(request.getParameter("idEsame"));
        }
        catch(Exception ex){
            response.sendError(400);
        }
        
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
           response.sendError(500);
        }
    }                    
}

