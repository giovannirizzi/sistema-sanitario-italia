package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "REPORT")
public class Report {
    
    public Report(){}
    
    @DatabaseField(id = true)
    private Integer id;
    
    @DatabaseField(columnName="idPrescrizione", foreign = true)
    private PrescrizioneEsame prescrizioneEsame;
    
    @DatabaseField
    private String descrizione;
    
    @DatabaseField
    private Date data; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PrescrizioneEsame getPrescrizioneEsame() {
        return prescrizioneEsame;
    }

    public void setPrescrizioneEsame(PrescrizioneEsame prescrizioneEsame) {
        this.prescrizioneEsame = prescrizioneEsame;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    
}
