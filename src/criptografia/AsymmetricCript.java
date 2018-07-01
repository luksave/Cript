package criptografia;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.swing.JOptionPane;
import java.nio.charset.StandardCharsets;

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
            TwoKeysGenerator parTemp = new TwoKeysGenerator(1024);
            parUsuario = new TwoKeysStore(parTemp.getPrivateKey(), parTemp.getPublicKey());
        
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

    public String decryptHash(String msg, PublicKey pKey)
                    throws InvalidKeyException,       UnsupportedEncodingException, 
                           IllegalBlockSizeException, BadPaddingException {
        
        cipher.init(Cipher.DECRYPT_MODE, pKey);
        
        byte[] byteArr = msg.getBytes(StandardCharsets.UTF_8);
        
        return new String(cipher.doFinal(byteArr), "UTF-8");
          
        
    }

        
}
    
    

