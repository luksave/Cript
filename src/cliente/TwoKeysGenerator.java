package cliente;

/**
 *
 * @author Bruno e Lucas
 */
import java.security.*;

public class TwoKeysGenerator {
    
    private final KeyPairGenerator geraChave;
    
    private final StringBuffer chavePublica = new StringBuffer();
    private final StringBuffer chavePrivada = new StringBuffer();
    
    //Costrutor das chaves
    public TwoKeysGenerator() throws NoSuchAlgorithmException{
        this.geraChave = KeyPairGenerator.getInstance("RSA");
        this.geraChave.initialize(512);//Esse parâmetro refere-se ao tamanho da chave 
                                       //Para RSA o tamanho da chave é de pelo menos 512 bits
        
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
