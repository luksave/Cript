package criptografia;

import java.io.*;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import servidor.KeyServer;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Lucas
 */
public class SymmetricDecript {
    
    public static void main(String args[]) 
            throws NoSuchAlgorithmException, FileNotFoundException, 
                   IOException,              GeneralSecurityException{
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        
        BASE64Decoder decoder = new BASE64Decoder();
        
        //Em vez de new SymmetricKeyGenerator(); e SymmetricKeyGenerator.getKey(),
        //obter a chave simétrica descriptografando-a com Asymmetric. A chave está 
        //no arquivo key.arq.
        
        //Recuperando a chave pública
        PrivateKey cPri = AsymmetricCript.parUsuario.getPrivateKey();

        //Armazenando todo o conteúdo do arquivo dentro de uma string
        File keyFile = new File("key.arq");
        FileReader fReader = new FileReader(keyFile);
        String conteudo;
        
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            conteudo = "";
            
            while(bReader.ready())
                conteudo += bReader.readLine();
        
        }
        
        //Fazendo a chamada para descriptografar o arquivo key.arq e recuperar a chave
        AsymmetricCript ac    = new AsymmetricCript();
        String chaveSimetrica = ac.decryptText(conteudo, cPri);
        
        //Chave Simétrica recuperada
        byte[] chaveCodificada = decoder.decodeBuffer(chaveSimetrica);
        SecretKey simKey = new SecretKeySpec(chaveCodificada,0, chaveCodificada.length, "DES");  
        
        int novaDescriptografia = JOptionPane.showConfirmDialog (
                        null, 
                        "Descriptografar novo arquivo?",
                        "Warning", dialogButton);

        
        while(novaDescriptografia != JOptionPane.NO_OPTION){

            String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo");
            
            //TODO - Descriptografar arquivo nomeArquivo
            //Onde nomeArquivo deve ser um caminho completo até o arquivo caso 
            //este não esteja na mesma pasta dos fontes.            
            File file = new File(nomeArquivo);
            String linha;
            
            BufferedReader leitor = new BufferedReader(new FileReader(file));

            //Lê linha por linha de nomeArquivo, criptografa e escreve em msg.arq
            //A mensagem descriptografada não deve ser salva em um arquivo
            while ((linha = leitor.readLine()) != null){
                linha = decrypt(linha, simKey);
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
