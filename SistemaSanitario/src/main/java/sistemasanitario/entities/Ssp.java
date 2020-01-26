package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "SSP")
public class Ssp {

    public Ssp() {
    }
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField
    private String nome;

    @DatabaseField
    private String citta;
    
    @DatabaseField
    private String provincia;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }
}
