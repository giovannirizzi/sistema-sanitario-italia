package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ESAME_PRESCRIVIBILE")
public class EsamePrescrivibile {
    
    public EsamePrescrivibile(){}
    
    @DatabaseField(id = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }
    
    @DatabaseField
    private String nome;
     
    @DatabaseField
    private String descrizione;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
