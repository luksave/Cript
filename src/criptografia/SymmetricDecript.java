package criptografia;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Lucas
 */
public class SymmetricDecript {
    
    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        int dialogButton = JOptionPane.YES_NO_OPTION;
        
        //Em vez de new SymmetricKeyGenerator(); e SymmetricKeyGenerator.getKey(),
        //obter a chave simétrice descriptografando-a com Asymmetric. A chave está 
        //no arquivo key.arq.

        int novaDescriptografia = JOptionPane.showConfirmDialog (
                        null, 
                        "Descriptografar novo arquivo?",
                        "Warning", dialogButton);

        
        while(novaDescriptografia != JOptionPane.NO_OPTION){

            String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo");
            
            //TODO - Criptografar arquivo nomeArquivo
            //Onde nomeArquivo deve ser um caminho completo até o arquivo caso 
            //este não esteja na mesma pasta dos fontes.            
            File file = new File(nomeArquivo);
            String linha;
            
            BufferedReader leitor = new BufferedReader(new FileReader(file));

            //Lê linha por linha de nomeArquivo, criptografa e escreve em msg.arq
            while ((linha = leitor.readLine()) != null){
                linha = decrypt(linha, SymmetricKeyGenerator.getKey());
                System.out.print(linha);
                
            }
            
            //Pergunta se o usuário deseja descriptografar novamente um arquivo
            novaDescriptografia = JOptionPane.showConfirmDialog (
                        null, 
                        "Descriptografar novo arquivo?",
                        "Warning", dialogButton);
        
        }
    
        
    }
    
    public static String decrypt(final String textoCriptografado, final SecretKey secKey) {
        String textoDescriptografado = null;

        try {
            final Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, secKey);
            final byte[] decorVal = new BASE64Decoder().decodeBuffer(textoCriptografado);
            final byte[] decValue = c.doFinal(decorVal);
            textoDescriptografado = new String(decValue);
            
        } catch(Exception ex) {
            System.out.println("Exceção = " + ex);
            
        }

        return textoDescriptografado;

    }
    
}
