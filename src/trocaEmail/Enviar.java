package trocaEmail;

import cliente.MessageClient;
import criptografia.AsymmetricCript;
import criptografia.ConvertStringToKey;
import criptografia.SymmetricCript;
import criptografia.SymmetricKeyGenerator;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.crypto.SecretKey;

/**
 *
 * @author lucas
 * Essa classe realiza as etapas do envio do e-mail
 */
public class Enviar {
    
    public Enviar() throws GeneralSecurityException, IOException{
        /** 
        *1 - Se o usuário corrente (remetente da mensagem) não tiver um par de 
        *chaves pública-privada (criptografia assimétrica), o software deverá 
        *gerar essas chaves e disponibilizar a chave pública para que os 
        *destinatários dos e-mails possam baixa-la;
        */
        AsymmetricCript ac = new AsymmetricCript();
        String chavePublica = 
                ConvertStringToKey.publicKeyToString(AsymmetricCript.parUsuario.getPublicKey());
        MessageClient.main("1");
        
        /**
         *2 - As mensagens enviadas poderão ter, além dos textos das mensagens, 
         * arquivos anexados, sem restrição ao tipo e tamanho desses arquivos; 
         */
        
        //Isso foi tratado em cada criptografia e descriptografia
        
        /**
         *3 - Uma vez escrito o texto e escolhido os arquivos anexados (caso 
         * existam anexos), o software deverá calcular um hashing da mensagem e 
         * arquivos de forma a garantir que o destinatário valide a integridade 
         * da mensagem caso o e-mail seja alterado durante a transmissão;
         */
        
        //TODO - PARTE-PAIVA
        
        /**
         *4 -  Usando a chave privada do usuário corrente, o software deverá 
         * criptografar o hashing calculado na etapa anterior e armazenar o 
         * hashing criptografado em um arquivo (hash.arq);
         */
        
        //TODO - PARTE-PAIVA
        
        /**
         *5 - O software deverá gerar uma chave de criptografia simétrica 
         *(cada e-mail enviado terá uma chave diferente), com tamanho mínimo 
         * suficiente para tornar a criptografia segura (à prova de quebra por 
         * força bruta)
         * Essa chave será usada para criptografar o texto e arquivos anexos da
         * mensagem que será enviada;
         */
        SymmetricKeyGenerator sc = new SymmetricKeyGenerator();
        
        /**
         *6 - Criptografar o texto e anexos da mensagem usando o algoritmo AES 
         *(criptografia simétrica) com a chave gerada na etapa 5, e armazenar 
         * esse conteúdo criptografado em um arquivo (msg.arq)
         */
        SymmetricCript.main(sc);
        
        /**
         *7 - Utilizando a chave pública do destinatário, criptografar a chave 
         * gerada na etapa 5 e armazenar essa chave criptografada em um arquivo 
         * (key.arq)
         * O software deverá fazer o download dessa chave a partir de um local 
         * seguro, conforme definido na etapa 1 [Obs.: caso o usuário envie 
         * essa mensagem para diversos destinatários, cada destinatário 
         * receberá um arquivo criptografado com sua chave pública específica, 
         * ou seja, serão gerados arquivos key.arq distintos para cada 
         * destinatário]; 
         */
        MessageClient.main("2");
        
        SecretKey chave = sc.getKey();
        byte[]    input = chave.getEncoded();
        
        ac.encrypt(input, MessageClient.getpKey());
        
        /**
         *8 - Usando o serviço de envio de mensagens (por exemplo, o Gmail), 
         * criar um novo e-mail para o destinatário e anexar os arquivos gerados
         * anteriormente (hash.arq, msg.arq e key.arq);
         */
       
        /**
         *9 - Enviar o e-mail 
         */
        
    }
    
    
    
    
    
}
