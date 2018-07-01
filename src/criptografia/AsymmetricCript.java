package criptografia;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class AsymmetricCript {
    
    public static TwoKeysGenerator par;
    public static TwoKeysStore parUsuario;
    
    private static Cipher cipher;
    
    
    public AsymmetricCript() throws NoSuchAlgorithmException, GeneralSecurityException{
        
        cipher = Cipher.getInstance("RSA");
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
      
        //Pergunta se o usuário já possui um par de chaves
        int possuiChave 
              = JOptionPane.showConfirmDialog (
                    null, 
                    "O usuário já possui um par de chaves?",
                    "Warning", dialogButton);
      
        //Se o usuário possui um par de chaves, armazene-as
        if(possuiChave == JOptionPane.YES_OPTION){
            String cPublica = JOptionPane.showInputDialog("Digite a chave publica");
            String cPrivada = JOptionPane.showInputDialog("Digite a chave privada");
            
            parUsuario = new TwoKeysStore(ConvertStringToKey.stringToPrivateKey(cPrivada),
                                          ConvertStringToKey.stringToPublicKey (cPublica));          
            
        //Senão, gere um par de chave e armazene-as
        }else{
            TwoKeysGenerator par = new TwoKeysGenerator(1024);
            parUsuario = new TwoKeysStore(par.getPrivateKey(), par.getPublicKey());
        
        } 
    
    }
    
    //Criptografia da chave simétrica usando a chave publica do destinatário
    // 1 - Criptografar a chave simétrica usada no AES
    // 2 - Escrever a chave criptografada em um arquivo 
    public void encrypt(byte[] input, PublicKey key) 
            throws IOException, GeneralSecurityException {
        
        File output = new File("key.arq");
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        writeToFile(output, cipher.doFinal(input));
    
    }
    
    public String decryptText(String msg, PrivateKey key)
                    throws InvalidKeyException,       UnsupportedEncodingException, 
                           IllegalBlockSizeException, BadPaddingException {
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        byte[] byteArr = msg.getBytes(StandardCharsets.UTF_8);
        
        return new String(cipher.doFinal(byteArr), "UTF-8");
    
    }

    
    private void writeToFile(File output, byte[] toWrite) 
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        
        try (FileOutputStream fos = new FileOutputStream(output)) {
            fos.write(toWrite);
            fos.flush();
        }

    }

        
}
    
    

