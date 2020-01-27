package sistemasanitario.beans;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medico;
import sistemasanitario.entities.MedicoSpecialista;
import sistemasanitario.entities.Paziente;
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
    
    private static Dao<PrescrizioneMedicina, Integer> prescrizioneEsameDao;
    private static Dao<Paziente, Integer> pazienteDao;
    private static Dao<EsamePrescrivibile, Integer> esameDao;
    private static Dao<Medico, Integer> medicoDao;

    private DataModel<PrescrizioneEsame> todoExams; 
    
    public ToDoExamsBean(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        prescrizioneEsameDao = (Dao<PrescrizioneMedicina, Integer>)servletContext.getAttribute("prescrizioneEsameDao");
        pazienteDao = (Dao<Paziente, Integer>) servletContext.getAttribute("pazienteDao");
        esameDao = (Dao<EsamePrescrivibile, Integer>) servletContext.getAttribute("esamePrescrivibileDao");
        medicoDao = (Dao<Medico, Integer>) servletContext.getAttribute("medicoDao");
    }
    
    @PostConstruct
    public void init(){

        QueryBuilder queryBuilder = prescrizioneEsameDao.queryBuilder();
        
        List<PrescrizioneEsame> tmp = null;
        try {
            tmp = new ArrayList<>();
            GenericRawResults<String[]> rawResults = prescrizioneEsameDao.queryRaw("SELECT PRESCRIZIONE_ESAME.id, PAZIENTE.nome, PAZIENTE.cognome, MEDICO.nome, MEDICO.cognome, ESAME_PRESCRIVIBILE.nome FROM PRESCRIZIONE_ESAME INNER JOIN PAZIENTE ON PRESCRIZIONE_ESAME.idPaziente = PAZIENTE.id INNER JOIN ESAME_PRESCRIVIBILE ON PRESCRIZIONE_ESAME.idEsame = ESAME_PRESCRIVIBILE.id INNER JOIN MEDICO ON PRESCRIZIONE_ESAME.idMedico = MEDICO.id WHERE PRESCRIZIONE_ESAME.idReport IS NULL");
            List<String[]> results = rawResults.getResults();
            for(String[] resultArray : results){
  
                PrescrizioneEsame preTmp = new PrescrizioneEsame();
                preTmp.setId(Integer.valueOf(resultArray[0]));
                
                Paziente pazienteTmp = new Paziente();
                pazienteTmp.setNome(resultArray[1]);
                pazienteTmp.setCognome(resultArray[2]);
                
                Medico medicoTmp = new Medico();
                medicoTmp.setNome(resultArray[3]);
                medicoTmp.setCognome(resultArray[4]);
                
                EsamePrescrivibile esameTmp = new EsamePrescrivibile();
                esameTmp.setNome(resultArray[5]);

                preTmp.setPaziente(pazienteTmp);
                preTmp.setMedico(medicoTmp);
                preTmp.setEsame(esameTmp);
                tmp.add(preTmp);
            }
    
        } catch (SQLException ex) {
           
        }
        this.todoExams = new ListDataModel<>(tmp);
    }
    private static final Logger LOG = Logger.getLogger(ToDoExamsBean.class.getName());

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
