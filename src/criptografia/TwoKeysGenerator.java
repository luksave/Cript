package criptografia;

/**
 *
 * @author Lucas
 */
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TwoKeysGenerator {

    private final KeyPairGenerator keyGen;
    private KeyPair pair;
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public TwoKeysGenerator(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);

    }

    public void createKeys() {
        this.pair = this.keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
    
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    
    }

    public PublicKey getPublicKey() {
        return publicKey;
    
    }

    public static void main(String[] args){

        TwoKeysGenerator gk;

        try {
            gk = new TwoKeysGenerator(1024);
            gk.createKeys();
           
        }catch(NoSuchAlgorithmException | NoSuchProviderException e){
            System.err.println(e.getMessage());

        }

    }
    
}
