package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "AUTH_TOKENS")
public class AuthToken {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName="userId", foreign = true)
    public User user;

    @DatabaseField
    public String validator;
    @DatabaseField
    public String selector;
    
    @DatabaseField
    private Date created;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getValidator() {
        return validator;
    }

    public String getSelector() {
        return selector;
    }
    
    public Date getCreatedTime(){
        return created;
    }
}
