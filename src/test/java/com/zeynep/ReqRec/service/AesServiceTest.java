package com.zeynep.ReqRec.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ExtendWith(MockitoExtension.class)
public class AesServiceTest {

    @InjectMocks
    AesService aesService;

    String originalText = "hello";
    String encryptedText = "Yv+SY6JANdLEZoNrwqKp/Q==";
    byte[] byteCipherText;

    @BeforeEach
    void init() throws Exception {
        String originalText = "hello";
        String sKey = "Password1saltyyy";

        SecretKeySpec secKey = new SecretKeySpec(sKey.getBytes(), "AES");

        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
        byteCipherText = aesCipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void givenPlainText_whenEncryptText_thenReturnEncryptedTextEqualOriginalText() throws Exception {
        String encryptedText = Base64.getEncoder().encodeToString(byteCipherText);
        Assertions.assertNotNull(encryptedText);
        Assertions.assertEquals(aesService.encryptText(originalText), encryptedText);
    }

    @Test
    void givenPlainText_whenDifferentPlainText_thenNotEqualTexts() throws Exception {
        String encryptedText = Base64.getEncoder().encodeToString(byteCipherText);
        Assertions.assertNotNull(encryptedText);
        Assertions.assertNotEquals(aesService.encryptText("changedText"), encryptedText);
    }

    @Test
    void givenCorrectEncryptedText_whenDecryptText_thenReturnDecryptText() throws Exception {

        Assertions.assertNotNull(encryptedText);
        Assertions.assertEquals(aesService.decryptText(encryptedText), originalText);
    }

    @Test
    void givenDifferentEncryptedText_whenDecryptText_thenBadPaddingExceptionReturnException() {

        Exception exception = Assertions.assertThrows(BadPaddingException.class,
                () -> aesService.decryptText("Yz+SY6JANdLEZoNrwqKp/Q=="));
        Assertions.assertNotNull(exception);
    }

    @Test
    void givenNotEncryptedText_whenDecryptText_thenReturnIllegalArgumentExceptionException() {
        Exception exception = Assertions.assertThrows(IllegalBlockSizeException.class,
                () -> aesService.decryptText("invalidText"));
        Assertions.assertNotNull(exception);
    }

}
