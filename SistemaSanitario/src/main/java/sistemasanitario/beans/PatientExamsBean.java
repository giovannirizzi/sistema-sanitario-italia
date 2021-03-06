package sistemasanitario.beans;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.Report;

@ViewScoped
@ManagedBean(name = "patientExams")
public class PatientExamsBean implements Serializable{
    
    @ManagedProperty("#{sessionScope.paziente}")
    private Paziente paziente;
    
    @ManagedProperty("#{notifications}")
    private NotificationsBean notifications;

    private DataModel<PrescrizioneEsame> exams; 
    private static final Logger LOG = Logger.getLogger(PatientExamsBean.class.getName());
    
    private static Dao<PrescrizioneMedicina, Integer> prescrizioneEsameDao;
    private static Dao<Report, Integer> reportDao;
    
    public PatientExamsBean(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        prescrizioneEsameDao = (Dao<PrescrizioneMedicina, Integer>)servletContext
                .getAttribute("prescrizioneEsameDao");
        reportDao = (Dao<Report, Integer>)servletContext.getAttribute("reportDao");
    }
    
    @PostConstruct
    public void init(){

        QueryBuilder queryBuilderPreEsami = prescrizioneEsameDao.queryBuilder();
        List<PrescrizioneEsame> tmp = null;

        try {

            tmp = queryBuilderPreEsami.where()
                    .eq("idPaziente", paziente.getId())
                    .query();

        } catch (SQLException ex) {
           
        }
        this.exams = new ListDataModel<>(tmp);
        
        if(notifications.getPatientNewExamPrescriptions() != 0)
            notifications.patientOnGetExamPrescription();
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public void setExams(DataModel<PrescrizioneEsame> exams) {
        this.exams = exams;
    }

    public DataModel<PrescrizioneEsame> getExams() {
        return exams;
    }

    public void setNotifications(NotificationsBean notifications) {
        this.notifications = notifications;
    }
 
}
