package sistemasanitario.config;


public class DatabaseConfig extends Config{
    
    private static final String FILE_NAME = "database.properties";
    private static final String CATEGORY = "db";
    private static DatabaseConfig instance;
    
    public static DatabaseConfig getInstance() throws Exception{
        
        if(instance == null)
            instance = new DatabaseConfig();
            
        return instance;
    }

    private DatabaseConfig() throws Exception {
         super(FILE_NAME, CATEGORY);
    }
    
    public String getUrl() {
        return this.getString("url");
    }
    
    public String getUsername() {
        return this.getString("username");
    }
    
    public String getPassword() {
        return this.getString("password");
    }
}
