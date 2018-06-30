package servidor;

import criptografia.ConvertStringToKey;
import java.security.PublicKey;

/**
 *
 * @author Bruno e Lucas
 */
public class IDChave {
    
    private String ID;
    private String Chave;
    
    public String getID() {
        return ID;
        
    }

    public void setID(String ID) {
        this.ID = ID;
        
    }

    public PublicKey getChave() {
        return ConvertStringToKey.stringToPublicKey(Chave);
        
    }

    public void setChave(String Chave) {
        this.Chave = Chave;
        
    }
   
}