/*
Essa classe implementa o algoritmo RSA de criptografia
 */
package criptografia;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;

public class RSA {
	
    // Essa função criptografa uma string
    public static String encrypt(String plainText, Key key) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    
    // Essa função descriptografa uma string
    public static String decrypt(String cipherText, Key key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, key);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }
}