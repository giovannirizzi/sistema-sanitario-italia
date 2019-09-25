package sistemasanitario.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemasanitario.entities.AuthToken;
import sistemasanitario.entities.ResetPasswordToken;

public class TokenUtil {

    public static final int SELECTOR_LENGTH = 36; //la lunghezza dell'UUID
    public static final int VALIDATOR_LENGTH = 32;

    private static  SecureRandom srandom = null;
    
    private static SecureRandom getSecureRandomInstance(){
        
        if(srandom == null){
            srandom = new SecureRandom();
        }
          
        return srandom;
    }
    
    public static AuthToken getRandomAuthToken(){
        
        byte[] vBytes = generateRandomByte(VALIDATOR_LENGTH);
        
        AuthToken token = new AuthToken();
        token.selector = UUID.randomUUID().toString();
        token.validator = Base64.getUrlEncoder().encodeToString(vBytes);
        
        return token; 
    }
    
    public static boolean isValidResetToken (String base64token){
        
        if(base64token == null ) return false;
        
        try{ 
            String token = new String(Base64.getUrlDecoder().decode(base64token));
            
            String uuidString = token.substring(0, 36);
        
            UUID tokenUUID = UUID.fromString(uuidString);
        }
        catch(IllegalArgumentException ex){
            return false;
        }
        return true;
    }
    
    public static ResetPasswordToken getRandomResetPasswordToken(){
        
        ResetPasswordToken token = new ResetPasswordToken();
        UUID uuid = UUID.randomUUID();
        
        byte[] vBytes = generateRandomByte(8);
        String randomData = Base64.getUrlEncoder().encodeToString(vBytes);
        String stringToken = Base64.getUrlEncoder().encodeToString(uuid.toString().getBytes());
        
        token.token = stringToken+randomData;
        return token;
    }
    
    public static byte[] generateRandomByte(int length){
        
        SecureRandom sr = getSecureRandomInstance();
        byte randomData[] = new byte[length];
        
        sr.nextBytes(randomData);
        
        return randomData;
    }
    
    public static String getHashSHA256(String base64Data) {
        
        try {
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Base64.getUrlDecoder().decode(base64Data));
            return Base64.getUrlEncoder().encodeToString(hash);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TokenUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static boolean verify(String hash, String validator) {
        
        String hashedToken = getHashSHA256(validator);
        return hash.equals(hashedToken);
    }
}
