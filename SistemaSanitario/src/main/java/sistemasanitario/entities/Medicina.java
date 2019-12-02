package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MEDICINA")
public class Medicina {
    
    public Medicina(){}
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField
    private String nome;
     
    @DatabaseField
    private String descrizione;
    
    @DatabaseField
    private Double prezzo;  

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }
}
