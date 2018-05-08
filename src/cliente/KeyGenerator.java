package cliente;

import java.security.*;
/**
 *
 * @author Bruno e Lucas
 */
public class KeyGenerator {
    
    private final KeyPairGenerator geraChave;
    
    private final StringBuffer chavePublica = new StringBuffer();
    private final StringBuffer chavePrivada = new StringBuffer();
    
    //Costrutor das chaves
    public KeyGenerator() throws NoSuchAlgorithmException{
        this.geraChave = KeyPairGenerator.getInstance("RSA");
        this.geraChave.initialize(512);//Esse parâmetro refere-se ao tamanho da chave

        byte[] chaveMais = geraChave.genKeyPair().getPublic().getEncoded();
        byte[] chaveMenos = geraChave.genKeyPair().getPrivate().getEncoded();
        
        //Armazenando a chave Pública
        for (int i = 0; i < chaveMais.length; ++i)
            chavePublica.append(chaveMais[i]);
        
        //Armazenando a chave Privada
        for (int i = 0; i < chaveMenos.length; ++i)
            chavePrivada.append(chaveMenos[i]);
          
    }

    public String getChavePublica() {
        return chavePublica.toString();
    }

    public String getChavePrivada() {
        return chavePrivada.toString();
    }

    
}
