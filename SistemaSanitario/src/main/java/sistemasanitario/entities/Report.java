package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "REPORT")
public class Report {
    
    public Report(){}
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField(columnName="idPrescrizione", foreign = true)
    private PrescrizioneEsame prescrizioneEsame;
    
    @DatabaseField
    private String descrizione;
    
    @DatabaseField
    private Date data; 
}
