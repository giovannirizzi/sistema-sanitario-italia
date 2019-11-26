package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "MEDICO")
public class MedicoSpecialista {
    
    public MedicoSpecialista() {}
    
    @DatabaseField(id = true)
    private int id;
    
    @DatabaseField
    private String nome;
    
    @DatabaseField
    private String cognome;
    
    @DatabaseField
    private String citta;
    
    @DatabaseField
    private String provincia;
    
    @DatabaseField
    private String codice;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCitta() {
        return citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCodice() {
        return codice;
    }
    
}
