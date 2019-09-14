package sistemasanitario.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "RESET_PASSWORD_TOKENS")
public class ResetPasswordToken {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName="userId", foreign = true)
    private User user;

    @DatabaseField
    private String token;
    
    @DatabaseField
    private Date created;
    
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedTime(){
        return created;
    }
}
