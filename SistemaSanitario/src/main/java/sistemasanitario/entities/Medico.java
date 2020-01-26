package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;

import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;


@DatabaseTable(tableName = "MEDICO")
public class Medico implements Serializable{
    
    public Medico() {
    }
    
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
    private String codiceMedico;

    public void setId(int id) {
        this.id = id;
    }

    
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

    public String getCodiceMedico() {
        return codiceMedico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    
}
