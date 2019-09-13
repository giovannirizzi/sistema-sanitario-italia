package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "USERS")
public class User {
    
    public enum UserType{
        INVALID,
        PAZIENTE,
        MEDICO_BASE,
        MEDICO_SPECIALISTA,
        SS_PROVINCIALE   
    }
    
    public User() {
    }
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private String username;
    
    @DatabaseField
    private String password;
    
    @DatabaseField
    private String email;
    
    @DatabaseField(unknownEnumName = "INVALID")
    private UserType type;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserType getType() {
        return type;
    } 
}
