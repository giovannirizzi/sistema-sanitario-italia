package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "PRESCRIZIONE_MEDICINA")
public class PrescrizioneMedicina {
    
    public PrescrizioneMedicina(){}
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField(columnName="idPaziente", foreign = true)
    private Paziente paziente;
    
    @DatabaseField(columnName="idMedico", foreign = true)
    private Medico medico;
    
    @DatabaseField(columnName="idMedicina", foreign = true, foreignAutoRefresh=true)
    private Medicina medicina;
    
    @DatabaseField
    private Integer quantita;
    
    @DatabaseField
    private Date data;

    public int getId() {
        return id;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Medico getMedico() {
        return medico;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public Date getData() {
        return data;
    } 
}
