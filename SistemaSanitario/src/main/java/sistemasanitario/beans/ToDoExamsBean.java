package sistemasanitario.beans;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
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
import sistemasanitario.entities.MedicoSpecialista;
import sistemasanitario.entities.PrescrizioneEsame;
import sistemasanitario.entities.PrescrizioneMedicina;
import sistemasanitario.entities.Ssp;

    
@ManagedBean(name = "todoExams")
@ViewScoped
public class ToDoExamsBean {

    @ManagedProperty("#{sessionScope.medicoSpecialista}")
    private MedicoSpecialista medicoSpe;

    @ManagedProperty("#{sessionScope.ssp}")
    private Ssp ssp;

    private DataModel<PrescrizioneEsame> todoExams; 
    
    @PostConstruct
    public void init(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        Dao<PrescrizioneMedicina, Integer> prescrizioneEsameDao = 
                (Dao<PrescrizioneMedicina, Integer>)servletContext.getAttribute("prescrizioneEsameDao");

        QueryBuilder queryBuilder = prescrizioneEsameDao.queryBuilder();
        
        List<PrescrizioneEsame> tmp = null;
        try {
            
            if(ssp == null){
                
               tmp = queryBuilder.where().isNull("idReport").query(); 
            }
            else{
                
               tmp = queryBuilder.where().isNull("idReport").query();   
            }        
        } catch (SQLException ex) {
           
        }
        this.todoExams = new ListDataModel<>(tmp);
    }

    public ToDoExamsBean(){}

    public MedicoSpecialista getMedicoSpe() {
        return medicoSpe;
    }

    public void setMedicoSpe(MedicoSpecialista medicoSpe) {
        this.medicoSpe = medicoSpe;
    }

    public Ssp getSsp() {
        return ssp;
    }

    public void setSsp(Ssp ssp) {
        this.ssp = ssp;
    }

    public DataModel<PrescrizioneEsame> getTodoExams() {
        return todoExams;
    }

    public void setTodoExams(DataModel<PrescrizioneEsame> todoExams) {
        this.todoExams = todoExams;
    }
     
}
