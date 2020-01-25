package sistemasanitario.config;

public class AuthConfig extends Config{
    
    private static final String FILE_NAME = "auth.properties";
    private static final String CATEGORY = "auth";
    private static AuthConfig instance;
    
    private AuthConfig() throws Exception{
        super(FILE_NAME, CATEGORY);
    }
    
    public static AuthConfig getInstance() throws Exception{
        
        if(instance == null)
            instance = new AuthConfig();
            
        return instance;
    }
    
    public int getPasswordMinLength(){
        return this.getInt("password.minLength");
    }
    
    public int getPasswordMaxLength(){
        return this.getInt("password.maxLength");
    }
    
    public int getMinUpperCaseCharacter(){
        return this.getInt("password.minUpperCaseCharacter");
    }
    
    public int getMinLowerCaseCharacter(){
        return this.getInt("password.minLowerCaseCharacter");
    }
    
    public int getMinDigitCharacter(){
        return this.getInt("password.minDigitCharacter");
    }
    
    public int getMinSpecialCharacter(){
        return this.getInt("password.minSpecialCharacter");
    }
    
    public String getRememberMeCookieName(){
        return this.getString("cookie.remember.name");
    }
    
    public int getRememberMeMaxAge(){
        return this.getInt("cookie.remember.maxAge");
    }
}
