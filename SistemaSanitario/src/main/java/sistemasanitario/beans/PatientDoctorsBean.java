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
import javax.servlet.ServletContext;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.Paziente;


@ManagedBean(name = "doctors", eager = true)
@ViewScoped
public class PatientDoctorsBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty("#{sessionScope.paziente}")
    private Paziente paziente;

    private List<Medico> availableDoctors = null;
    
    public PatientDoctorsBean(){}

    @PostConstruct
    public void init(){
        
        if(paziente != null){
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            Dao<Medico, Integer> medicoDao = (Dao<Medico, Integer>)servletContext.getAttribute("medicoDao");
            
            QueryBuilder queryBuilder = medicoDao.queryBuilder();
            try {
                
                availableDoctors = queryBuilder.where().eq("provincia", paziente.getProvincia()).query();
                
                /*if(paziente.getMedicoBase() != null){
                    availableDoctors.removeIf(new Predicate<Medico>(){
                        @Override
                        public boolean test(Medico medico) {
                            return medico.getId() == paziente.getMedicoBase().getId();
                        }
                    }
                    );
                }*/
            } catch (SQLException ex) {}
        }
    }
    
    public List<Medico> getAvailableDoctors() {
        return availableDoctors;
    }
    
    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }
}
