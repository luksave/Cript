/*
Essa classe implementa o algoritmo Hash MD5, onde ele recebe uma mensagem e calcula o hashing da mesma
 */
package criptografia;

import static criptografia.RSADecript.decrypt;
import static criptografia.SymmetricCript.encrypt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

public class MD5 {
    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException, Exception{
        int dialogButton = JOptionPane.YES_NO_OPTION;
        
        //Abre o arquivo msg.arq para escrita
        BufferedWriter escritor = new BufferedWriter(new FileWriter("hash.arq", true));

        int novaHash = JOptionPane.showConfirmDialog (
                        null, 
                        "Hash novo arquivo?",
                        "Warning", dialogButton);

        
        while(novaHash != JOptionPane.NO_OPTION){

            String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo");
            
            //TODO - Calcular o hash arquivo nomeArquivo
            //Onde nomeArquivo deve ser um caminho completo até o arquivo caso 
            //este não esteja na mesma pasta dos fontes.            
            File file = new File(nomeArquivo);
            String linha;
            
             BufferedReader leitor   = new BufferedReader(new FileReader(file));

            //Lê linha por linha de nomeArquivo, criptografa e escreve em msg.arq
            while ((linha = leitor.readLine()) != null){
                linha = encrypt(linha, (SecretKey) AsymmetricCript.parUsuario.getPrivateKey());
                escritor.append(linha);
                escritor.close();
                
            }
            
            leitor.close();
            
            //Pergunta se o usuário deseja calcular hash novamente um arquivo
            novaHash = JOptionPane.showConfirmDialog (
                        null, 
                        "Hash novo arquivo?",
                        "Warning", dialogButton);
        
        }
    
        
    }
    
    // Essa classe gera um hash MD5 de uma string
    public static String calculate(String str) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] hashBytes = digest.digest(str.getBytes());
			return convertToHexString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block

		}
		return null;
	}

    // Essa classe converte um array de Bytes em Hexa
	private static String convertToHexString(byte[] arrayBytes) {
	    StringBuffer stringBuffer = new StringBuffer();
	    for (int i = 0; i < arrayBytes.length; i++) {
	        stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    return stringBuffer.toString();
	}
    
}
