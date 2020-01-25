package sistemasanitario.beans;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneMedicina;

@ViewScoped
@ManagedBean(name = "prescrizioniMedicine")
public class PrescriptionMedicineBean implements Serializable{
    
    @ManagedProperty("#{sessionScope.paziente}")
    private Paziente paziente;
    
    @ManagedProperty("#{notifications}")
    private NotificationsBean notifications;
    
    private DataModel<PrescrizioneMedicina> prescrizioni; 
    private static Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    
    public PrescriptionMedicineBean(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        
        prescrizioneMedicinaDao = (Dao<PrescrizioneMedicina, Integer>)servletContext.getAttribute("prescrizioneMedicinaDao");
    }
    
    @PostConstruct
    public void init(){
        
        QueryBuilder queryBuilder = prescrizioneMedicinaDao.queryBuilder();
        List<PrescrizioneMedicina> tmp = null;
        try {
            tmp = queryBuilder.where().eq("idPaziente", paziente.getId()).query();
        } catch (SQLException ex) {
           
        }
        this.prescrizioni = new ListDataModel<>(tmp);
        
        if(notifications.getPatientNewMedicinePrescriptions()!= 0)
            notifications.patientOnGetMedicinePrescription();
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public DataModel<PrescrizioneMedicina> getPrescrizioni() {
        return prescrizioni;
    }

    public void setNotifications(NotificationsBean notifications) {
        this.notifications = notifications;
    }
}
