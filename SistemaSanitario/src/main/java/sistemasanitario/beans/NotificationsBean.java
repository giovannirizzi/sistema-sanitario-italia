package sistemasanitario.beans;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.User;

@ViewScoped
@ManagedBean(name = "notifications")
public class NotificationsBean implements Serializable{
    

    @ManagedProperty("#{sessionScope.user}")
    private User user;

    private Integer patientNewExamPrescriptions; 
    private Integer patientNewMedicinePrescriptions;
    
    private static Dao<PrescrizioneEsame, Integer> prescrizioneEsameDao;     
    private static Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    private static Dao<Paziente, Integer> pazienteDao;
    private static final Logger LOG = Logger.getLogger(NotificationsBean.class.getName());
 
    @PostConstruct
    public void init(){

        patientNewExamPrescriptions = 0;
        patientNewMedicinePrescriptions = 0;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        
        prescrizioneEsameDao = (Dao<PrescrizioneEsame, Integer>)servletContext.
                getAttribute("prescrizioneEsameDao");
        prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)servletContext.
                getAttribute("prescrizioneMedicinaDao");
        pazienteDao = (Dao<Paziente, Integer>)servletContext.getAttribute("pazienteDao");
        
        if(user.getType() == User.UserType.PAZIENTE)
            patientUpdateNotifications();
            
    }
    
    public void patientUpdateNotifications(){
        
          try{
            List<PrescrizioneEsame> examsList = prescrizioneEsameDao
                    .queryBuilder().where()
                    .eq("idPaziente", user.getId())
                    .and().eq("letta", false).query(); 
            
            patientNewExamPrescriptions = examsList.size();
            
            List<PrescrizioneMedicina> medicineList = prescrizioneMedicinaDao
                    .queryBuilder().where()
                    .eq("idPaziente", user.getId())
                    .and().eq("letta", false).query(); 
            
            patientNewMedicinePrescriptions = medicineList.size();
            
        }
        catch(SQLException ex){}
    }
    
    public void patientOnGetExamPrescription(){
        try{
            UpdateBuilder<PrescrizioneEsame, Integer>  updateBuilder = 
                    prescrizioneEsameDao.updateBuilder();
            updateBuilder.updateColumnValue("letta", true);
            updateBuilder.where().eq("idPaziente", user.getId())
                    .and().eq("letta", false);
            
            prescrizioneEsameDao.update(updateBuilder.prepare());
            patientNewExamPrescriptions = 0;
        }
        catch(SQLException ex){  
        }
    }
    
    void patientOnGetMedicinePrescription() {
        try{
            UpdateBuilder<PrescrizioneMedicina, Integer>  updateBuilder = 
                    prescrizioneMedicinaDao.updateBuilder();
            updateBuilder.updateColumnValue("letta", true);
            updateBuilder.where().eq("idPaziente", user.getId())
                    .and().eq("letta", false);
            
            prescrizioneMedicinaDao.update(updateBuilder.prepare());
            patientNewMedicinePrescriptions = 0;
        }
        catch(SQLException ex){  
        }
    }
    
    public Integer getPatientNewExamPrescriptions() {
        return patientNewExamPrescriptions;
    }

    public Integer getPatientNewMedicinePrescriptions() {
        return patientNewMedicinePrescriptions;
    }  

    public void setUser(User user) {
        this.user = user;
    }
}
