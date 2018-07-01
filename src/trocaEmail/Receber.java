package trocaEmail;

import cliente.MessageClient;
import criptografia.AsymmetricCript;
import criptografia.ConvertStringToKey;
import criptografia.MD5;
import criptografia.SymmetricDecript;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Lucas
 */
public class Receber {
    
    public Receber() throws GeneralSecurityException, IOException{   
    
        //Antes disso tudo, o usuário deve fornecer sua chave pública
        
        AsymmetricCript ac = new AsymmetricCript();
        MessageClient.main("1");
        
        /**
         * 1 - Usando a chave pública do remetente da mensagem, decriptografar o 
         * arquivo que contém o hashing (hash.arq) 
         * Como estamos utilizando o par de chaves pública-privada do remetente, 
         * temos a garantia de identidade de quem enviou o e-mail;
         */
        
        //Armazenando todo o conteúdo do arquivo dentro de uma string
        File hashFile = new File("hash.arq");
        FileReader fReader = new FileReader(hashFile);
        String conteudo_hash;
        
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            conteudo_hash = "";
            
            while(bReader.ready())
                conteudo_hash += bReader.readLine();
        
        }

        MessageClient.main("2");
        String hashDecriptografada = ac.decryptHash(conteudo_hash, MessageClient.getpKey());
    
        /**
         * 2 - Usando a chave privada do usuário corrente (destinatário da 
         * mensagem), decriptografar o arquivo que contém a chave simétrica usada 
         * para criptografar o texto e arquivos anexos da mensagem original 
         * (key.arq) 
         * Como estamos utilizando o par de chaves pública-privada do destinatário, 
         * temos a garantia de que o e-mail recebido foi enviado para o usuário 
         * corrente;
         */
        File keyFile = new File("key.arq");
        fReader = new FileReader(keyFile);
        String conteudo_key;
        
        try (BufferedReader bReader = new BufferedReader(fReader)) {
            conteudo_key = "";
            
            while(bReader.ready())
                conteudo_key += bReader.readLine();
        
        }
        String chaveSimetrica = ac.decryptText(conteudo_key, AsymmetricCript.parUsuario.getPrivateKey());
        
        /**
         * 3 - Usando a chave obtida na etapa 2, decriptografar a mensagem original 
         * e seus arquivos anexados (msg.arq) usando o algoritmo de criptografia 
         * AES;
         */

        byte[] decodedKey = Base64.getDecoder().decode(chaveSimetrica);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        SymmetricDecript.decrypt(hashDecriptografada, secretKey);
        
        /**
         * 4 - Calcular o hashing da mensagem e arquivos obtidos na etapa 3 e 
         * comparar com o hashing obtido na etapa 1 
         * Se os hashings forem diferentes, a mensagem recebida foi alterada durante
         * a transmissão;
         */
    
        MD5 hash = new MD5();
    
        
    }
    
}
