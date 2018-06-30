/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author lucas
 */
public class ConvertStringToKey {
    
    public static PublicKey stringToPublicKey(String s) {

        BASE64Decoder decoder = new BASE64Decoder();

        byte[] c = null;
        KeyFactory keyFact = null;
        PublicKey returnKey = null;

        try {
            c = decoder.decodeBuffer(s);
            keyFact = KeyFactory.getInstance("DSA", "SUN");
        } catch (Exception e) {
            System.out.println("Error in Keygen");
            e.printStackTrace();
        }


        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(c);
        try {
            returnKey = keyFact.generatePublic(x509KeySpec);
        } catch (Exception e) {

            System.out.println("Error in Keygen2");
            e.printStackTrace();

        }

        return returnKey; 

    }
    public static PrivateKey stringToPrivateKey(String s) {

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] c = null;
        KeyFactory keyFact = null;
        PrivateKey returnKey = null;

        try {

                        c = decoder.decodeBuffer(s);
            keyFact = KeyFactory.getInstance("DSA", "SUN");
        } catch (Exception e) {

            System.out.println("Error in first try catch of stringToPrivateKey");
            e.printStackTrace();
        }


        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(c);
        try {   //the next line causes the crash
            returnKey = keyFact.generatePrivate(x509KeySpec);
        } catch (Exception e) {

            System.out.println("Error in stringToPrivateKey");
            e.printStackTrace();
        }

        return returnKey; 

    }

    public static String publicKeyToString(PublicKey p) {

        byte[] publicKeyBytes = p.getEncoded();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(publicKeyBytes);

    }
    public static String privateKeyToString(PrivateKey p) {

        byte[] privateKeyBytes = p.getEncoded();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(privateKeyBytes);
    }
    
}
