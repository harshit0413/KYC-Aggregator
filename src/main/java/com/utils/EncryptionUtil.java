package com.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456";

    public static String encrypt(String data) throws Exception {
        SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String originalData = "1100223234457";
            log.info("Original Data: {}", originalData);

            String encryptedData = encrypt(originalData);
            log.info("Encrypted Data: {}", encryptedData);

            String decryptedData = decrypt(encryptedData);
            log.info("Decrypted Data: {}", decryptedData);
        } catch (Exception e) {
            log.error("An error occurred: ", e);
        }
    }
}
