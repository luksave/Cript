package criptografia;


import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */

public class AsymmetricCript {
    
    public static TwoKeysGenerator par;
    public static TwoKeysStore parUsuario;
    
    private static final StringBuffer chavePublica = new StringBuffer();
    private static final StringBuffer chavePrivada = new StringBuffer();
    
    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException{
        int dialogButton = JOptionPane.YES_NO_OPTION;
      
        int possuiChave 
              = JOptionPane.showConfirmDialog (
                    null, 
                    "O usuário já possui um par de chaves?",
                    "Warning", dialogButton);
      
        if(possuiChave == JOptionPane.YES_OPTION){
            String cPublica = JOptionPane.showInputDialog("Digite a chave publica");
            String cPrivada = JOptionPane.showInputDialog("Digite a chave privada");
            
            parUsuario = new TwoKeysStore(ConvertStringToKey.stringToPrivateKey(cPrivada),
                                          ConvertStringToKey.stringToPublicKey (cPublica));          
            
        }else{
            TwoKeysGenerator par = new TwoKeysGenerator(1024);
            parUsuario = new TwoKeysStore(par.getPrivateKey(), par.getPublicKey());
        
        }  
        
    }
        
}
