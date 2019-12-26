package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "PRESCRIZIONE_ESAME")
public class PrescrizioneEsame {
    
    public PrescrizioneEsame(){}
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField(columnName="idPaziente", foreign = true)
    private Paziente paziente;
    
    @DatabaseField(columnName="idMedico", foreign = true, foreignAutoRefresh=true)
    private Medico medico;
    
    @DatabaseField(columnName="idEsame", foreign = true, foreignAutoRefresh=true)
    private EsamePrescrivibile esame;
    
    @DatabaseField(readOnly = true, canBeNull = false)
    private Boolean letta;
      
    @DatabaseField(readOnly = true, canBeNull = false)
    private Date data;
    
    private Boolean completed;

    public void setLetta(Boolean letta) {
        this.letta = letta;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setEsame(EsamePrescrivibile esame) {
        this.esame = esame;
    }

    public int getId() {
        return id;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public Medico getMedico() {
        return medico;
    }

    public EsamePrescrivibile getEsame() {
        return esame;
    }

    public Boolean getLetta() {
        return letta;
    }

    public Date getData() {
        return data;
    }
}
