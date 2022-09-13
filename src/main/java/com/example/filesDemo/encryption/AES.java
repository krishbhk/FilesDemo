package com.example.filesDemo.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES {
    private static final String ALGO = "AES";

    public static byte[] encrypt(String fileKey,byte[] Data) throws Exception {

        Key key = new SecretKeySpec(fileKey.getBytes(), ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        return c.doFinal(Data);
    }

    public static byte[] decrypt(String fileKey, byte[] encryptedData) throws Exception {

        Key key = new SecretKeySpec(fileKey.getBytes(), ALGO);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);

        return c.doFinal(encryptedData);
    }

}
