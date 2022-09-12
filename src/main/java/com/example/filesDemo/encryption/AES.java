package com.example.filesDemo.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES {
    private static final String ALGO = "AES";
    private static final byte[] keyValue = "Ad0#2s!3oGyRq!5F".getBytes();

    private static Key generateKey() throws Exception {
        Key key = null;
        key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static byte[] encrypt(byte[] Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(Data);
    }


    public static byte[] decrypt(byte[] encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);

        return c.doFinal(encryptedData);
    }
}
