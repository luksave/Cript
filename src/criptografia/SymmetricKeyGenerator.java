package criptografia;

/**
 *
 * @author Lucas
 */

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SymmetricKeyGenerator {
    
    private static SecretKey key;
    
    public SymmetricKeyGenerator() throws NoSuchAlgorithmException{
        // Gerando uma chave AES de 256 bits
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);        
        key = keyGen.generateKey();
        
    }

    public static SecretKey getKey() {
        return key;
    }

    public static void setKey(SecretKey key) {
        SymmetricKeyGenerator.key = key;
        
    }
  
}
