package sistemasanitario.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

public final class PasswordUtil {
    
    private static Argon2 argon2Hasher = null;
    private static PasswordValidator passwordValidator; 
    private static final int ITERATIONS = 10;
    
    /**
    * Compute a hash of a password.
    * Password provided is wiped from the memory at the end of this method
    *
    * @param password Password to hash
    * @return the hash in format "$argon2i$v=19$m=128000,t=3,
    *       p=4$sfSe5MewORVlg8cDtxOTbg$uqWx4mZvLI092oJ8ZwAjAWU0rrBSDQkOezxAuvrE5dM"
    */
    public static String hash(char[] password) {
        
        String hash;
        try {
    
            argon2Hasher = createInstance();
            hash = argon2Hasher.hash(ITERATIONS, 65536, 1, password, StandardCharsets.UTF_16);
        
        } finally {
            //Clean the password from the memory
            if (argon2Hasher != null) {
                argon2Hasher.wipeArray(password);
            }
        }
        return hash;
    }

    /**
    * Verifies a password against a hash
    * Password provided is wiped from the memory at the end of this method
    *
    * @param hash     Hash to verify
    * @param password Password to which hash must be verified against
    * @return True if the password matches the hash, false otherwise.
    */
    public static boolean verify(String hash, char[] password) {
        Argon2 argon2Hasher = null;
        boolean isMatching;
        try {
            // Create instance
            argon2Hasher = createInstance();
            //Apply the verification (hash computation options are included
            //in the hash itself)
            isMatching = argon2Hasher.verify(hash, password, StandardCharsets.UTF_16);
        } finally {
            //Clean the password from the memory
            if (argon2Hasher != null) {
                argon2Hasher.wipeArray(password);
            }
        }
        return isMatching;
    }

    /**
    * Create and configure an Argon2 instance
    *
    * @return The Argon2 instance
    */
    private static Argon2 createInstance() {

        if(argon2Hasher == null)
            return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        else
            return argon2Hasher;
    }
    
    public static RuleResult validatePassword(String password){
         
        if(passwordValidator == null){
            passwordValidator = new PasswordValidator(
                Arrays.asList(
                    new LengthRule(8, 255),
                    new CharacterRule(EnglishCharacterData.LowerCase, 1),
                    new CharacterRule(EnglishCharacterData.UpperCase, 1),
                    new CharacterRule(EnglishCharacterData.Digit, 1),
                    new CharacterRule(EnglishCharacterData.Special, 1))
                );
        }
        return passwordValidator.validate(new PasswordData(password));
    }
}
