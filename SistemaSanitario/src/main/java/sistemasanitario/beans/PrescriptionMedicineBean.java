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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import sistemasanitario.entities.Paziente;
import sistemasanitario.entities.PrescrizioneMedicina;

@ManagedBean(name = "prescrizioniMedicine")
@ViewScoped
public class PrescriptionMedicineBean implements Serializable{
    
    @ManagedProperty("#{prescrizioneMedicinaDao}")
    private Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao;
    
    @ManagedProperty("#{sessionScope.paziente}")
    private Paziente paziente;
    
    private DataModel<PrescrizioneMedicina> prescrizioni; 
     
    
    @PostConstruct
    public void init(){
        
        QueryBuilder queryBuilder = prescrizioneMedicinaDao.queryBuilder();
        List<PrescrizioneMedicina> tmp = null;
        try {
            tmp = queryBuilder.where().eq("idPaziente", paziente.getId()).query();
        } catch (SQLException ex) {
           
        }
        this.prescrizioni = new ListDataModel<>(tmp);
    }
    
    public PrescriptionMedicineBean(){}
    

    public void setPrescrizioneMedicinaDao(Dao<PrescrizioneMedicina, Integer> prescrizioneMedicinaDao) {
        this.prescrizioneMedicinaDao = prescrizioneMedicinaDao;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public DataModel<PrescrizioneMedicina> getPrescrizioni() {
        return prescrizioni;
    }
}
