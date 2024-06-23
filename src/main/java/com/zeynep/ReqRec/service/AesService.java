package com.zeynep.ReqRec.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AesService {

    String sId = "Password1";
    String SALT2 = "saltyyy";

    // Get the Key
    byte[] key = (SALT2 + sId).getBytes();

    SecretKeySpec secKey = new SecretKeySpec(key, "AES");

    public String encryptText(String plainText) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(byteCipherText);
    }

    public String decryptText(String cipherText) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secKey);
        byte[] bytePlainText = aesCipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(bytePlainText, StandardCharsets.UTF_8);
    }

}
