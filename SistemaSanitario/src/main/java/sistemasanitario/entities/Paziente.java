package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.Date;

@DatabaseTable(tableName = "PAZIENTE")
public class Paziente implements Serializable{
    
    public Paziente() {
    }
    
    @DatabaseField(columnName="id", id=true)
    public Integer id;
    
    @DatabaseField(columnName="idMedico", foreign = true,  foreignAutoRefresh=true)
    public Medico medicoBase;
    
    @DatabaseField
    private String nome;
    
    @DatabaseField
    private String cognome;
    
    @DatabaseField
    private String luogoNascita;
    
    @DatabaseField
    private Date dataNascita;
    
    @DatabaseField
    private String provincia;
    
    @DatabaseField
    private String cf;
    
    @DatabaseField
    private Character sesso;
    
    @DatabaseField
    private String foto;

    public void setMedicoBase(Medico medicoBase) {
        this.medicoBase = medicoBase;
    }

    public void setPhoto(String foto) {
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public Medico getMedicoBase() {
        return medicoBase;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCf() {
        return cf;
    }

    public Character getSesso() {
        return sesso;
    }

    public String getFoto() {
        return foto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    } 
    
}

