
package sistemasanitario.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemasanitario.entities.AuthToken;

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
        token.validator = Base64.getEncoder().encodeToString(vBytes);
        
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
            byte[] hash = digest.digest(Base64.getDecoder().decode(base64Data));
            return Base64.getEncoder().encodeToString(hash);
            
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