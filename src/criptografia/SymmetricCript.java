package criptografia;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;
import java.io.*;

/**
 *
 * @author Lucas
 * Essa classe deve ser capaz de criptografar o texto e os anexos da mensagem usando AES
 * com a chave que é gerada por SymmetricKeyGenerator.
 * O resultado dessa criptografia deve ser armazenado em msg.arq
 */
public class SymmetricCript {
    
    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        int dialogButton = JOptionPane.YES_NO_OPTION;
        new SymmetricKeyGenerator();

        //Abre o arquivo msg.arq para escrita
        BufferedWriter escritor = new BufferedWriter(new FileWriter("msg.arq", true));
        
        //Lê a mensagem do e-mail, criptografa e adiciona ao arquivo msg.arq
        String msg = JOptionPane.showInputDialog("Digite a mensagem"); 
        String msgCriptografada = encrypt(msg, SymmetricKeyGenerator.getKey());
        escritor.append(msgCriptografada);
        
        int novaCriptografia = JOptionPane.showConfirmDialog (
                        null, 
                        "Criptografar novo arquivo?",
                        "Warning", dialogButton);

        
        while(novaCriptografia != JOptionPane.NO_OPTION){

            String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo");
            
            //Criptografar arquivo nomeArquivo
            //Onde nomeArquivo deve ser um caminho completo até o arquivo caso 
            //este não esteja na mesma pasta dos fontes.            
            File file = new File(nomeArquivo);
            String linha;
            
            BufferedReader leitor   = new BufferedReader(new FileReader(file));

            //Lê linha por linha de nomeArquivo, criptografa e escreve em msg.arq
            while ((linha = leitor.readLine()) != null){
                linha = encrypt(linha, SymmetricKeyGenerator.getKey());
                escritor.append(linha);
                escritor.close();
                
            }
            
            //Pergunta se o usuário deseja criptografar novamente um arquivo
            novaCriptografia = JOptionPane.showConfirmDialog (
                        null, 
                        "Criptografar novo arquivo?",
                        "Warning", dialogButton);
        
        }
    
        
    }
    
    public static String encrypt(final String texto, final SecretKey secKey) { 
        String textoCriptografado = null;

        try {
            final Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, secKey);
            
            final byte[] encValue = c.doFinal(texto.getBytes());
            textoCriptografado = new BASE64Encoder().encode(encValue);
            
        } catch(Exception ex) {
            System.out.println("Exceção = " + ex);
            
        }

        return textoCriptografado;

    }    
}
