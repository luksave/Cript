package criptografia;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author lucas
 */
public class TwoKeysStore {
   
    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    
    public TwoKeysStore(PrivateKey priKey, PublicKey pubKey){
        privateKey = priKey;
        publicKey  = pubKey;
          
    }
    
    public PrivateKey getPrivateKey() {
        return privateKey;
        
    }

    public PublicKey getPublicKey() {
        return publicKey;
    
    }
    
    
}
