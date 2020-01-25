package sistemasanitario.config;


public class EmailConfig extends Config{
    
    private static final String FILE_NAME = "email.properties";
    private static final String CATEGORY = "email";
    private static EmailConfig instance;
    
    public static EmailConfig getInstance() throws Exception{
        
        if(instance == null)
            instance = new EmailConfig();
            
        return instance;
    }

    private EmailConfig() throws Exception {
         super(FILE_NAME, CATEGORY);
    }
    
    public String getUsername() {
        return this.getString("username");
    }

    public String getPassword() {
        return this.getString("password");
    }

    public boolean getDebug() {
        return this.getBoolean("debug");
    }

    public String getSmtpHost() {
        return this.getString("smtp.host");
    }

    public int getSmtpPort() {
        return this.getInt("smtp.port");
    }

    public boolean getSmtpAuth() {
        return this.getBoolean("smtp.auth");
    }

    public boolean getSmtpTls() {
        return this.getBoolean("smtp.tls");
    }
}
