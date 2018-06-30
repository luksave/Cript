package criptografia;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author lucas
 */
public class TwoKeysStore {
    
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    public TwoKeysStore(PrivateKey priKey, PublicKey pubKey){
        this.privateKey = priKey;
        this.publicKey  = pubKey;
          
    }
    
    public PrivateKey getPrivateKey() {
        return this.privateKey;
        
    }

    public PublicKey getPublicKey() {
            return this.publicKey;
    
    }
    
    
}
