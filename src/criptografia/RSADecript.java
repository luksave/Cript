package criptografia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;

public class RSADecript {
    
    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException, Exception{
        int dialogButton = JOptionPane.YES_NO_OPTION;
        

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
                linha = decrypt(linha, AsymmetricCript.parUsuario.getPublicKey());
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
