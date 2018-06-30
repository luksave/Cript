/*
Essa classe implementa o algoritmo RSA de criptografia
 */
package criptografia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;
import javax.swing.JOptionPane;

public class RSADecrypt {
	
      public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException, Exception{
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

    
    // Essa função descriptografa uma string
    public static String decrypt(String cipherText, Key key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, key);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }
}