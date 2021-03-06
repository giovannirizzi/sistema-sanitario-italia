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
    
    @DatabaseField(readOnly = true, canBeNull = false)
    private Date data;
    
    private String autore;
    private String esame;
    
    public String getEsame() {
        return esame;
    }

    public void setEsame(String esame) {
        this.esame = esame;
    }
    

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }
    
    private String dataString;

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
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
    
    public Date getData() {
        return data;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
