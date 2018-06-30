/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;

public class RSAEncrypt {
    
    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException, Exception{
        int dialogButton = JOptionPane.YES_NO_OPTION;

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
            
            //TODO - Criptografar arquivo nomeArquivo
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
    
    // Essa função criptografa uma string
    public static String encrypt(String plainText, Key key) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }
    
}
