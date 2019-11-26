package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ESAME_PRESCRIVIBILE")
public class EsamePrescrivibile {
    
    public EsamePrescrivibile(){}
    
    @DatabaseField(id = true)
    private int id;
    
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
    
}
