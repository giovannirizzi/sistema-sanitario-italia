package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PAZIENTE")
public class Paziente {
    
    public Paziente() {
    }
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName="userId", foreign = true)
    public User user;
    
    @DatabaseField
    private String nome;
    
    @DatabaseField
    private String cognome;
  
}

