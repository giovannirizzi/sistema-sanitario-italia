package sistemasanitario.beans;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.Report;

@ManagedBean(name = "patientExams")
@ViewScoped
public class PatientExamsBean implements Serializable{
    
    @ManagedProperty("#{sessionScope.paziente}")
    private Paziente paziente;

    private DataModel<PrescrizioneEsame> exams; 
    
    @PostConstruct
    public void init(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        Dao<PrescrizioneMedicina, Integer> prescrizioneEsameDao = 
                (Dao<PrescrizioneMedicina, Integer>)servletContext.getAttribute("prescrizioneEsameDao");

        QueryBuilder queryBuilder = prescrizioneEsameDao.queryBuilder();
        
        List<PrescrizioneEsame> tmp = null;
        try {
            tmp = queryBuilder.where().eq("idPaziente", paziente.getId()).query();
             
        } catch (SQLException ex) {
           
        }
        this.exams = new ListDataModel<>(tmp);
    }

    public PatientExamsBean(){}

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public void setExams(DataModel<PrescrizioneEsame> exams) {
        this.exams = exams;
    }

    public DataModel<PrescrizioneEsame> getExams() {
        return exams;
    }
}
