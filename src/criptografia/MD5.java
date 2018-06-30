/*
Essa classe implementa o algoritmo Hash MD5, onde ele recebe uma mensagem e calcula o hashing da mesma
 */
package criptografia;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    
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
