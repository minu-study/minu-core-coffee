package minu.coffee.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class EncUtil {

    private static String encryptKey;
    private static String encryptSalt;
    private static StandardPBEStringEncryptor encryptor;

    static {
        encryptor = new StandardPBEStringEncryptor();
        try {
            ResetEncryptor();
        } catch (IllegalStateException e) {
            log.warn("Encryptor initialization failed in static block: {}", e.getMessage());
        }
    }

    public EncUtil(@Value("${common.encrypt.key}") String encryptKey,
                   @Value("${common.encrypt.salt}") String encryptSalt) {
        EncUtil.encryptKey = encryptKey;
        EncUtil.encryptSalt = encryptSalt;
        resetEncryptor();
    }

    public static void resetEncryptor() {
        if (encryptKey == null || encryptKey.isEmpty()) {
            throw new IllegalStateException("Encryption password is not set!");
        }
        log.info("Initializing Encryptor");
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(EncUtil.encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setSaltGenerator(new StringFixedSaltGenerator(EncUtil.encryptSalt));
    }

    public static String encrypt(String str) {
        if (str == null || str.isEmpty()) {
            log.warn("Encrypt parameter is null or empty");
            return null;
        }
        try {
            return encryptor.encrypt(str);
        } catch (Exception e) {
            log.error("Encryption failed: {}", e.getMessage());
            ResetEncryptor(); // 재초기화 시도
            return encryptor.encrypt(str); // 재시도
        }
    }

    public static String decrypt(String encStr) {
        if (encStr == null || encStr.isEmpty()) {
            log.warn("Decrypt parameter is null or empty");
            return null;
        }
        try {
            return encryptor.decrypt(encStr);
        } catch (Exception e) {
            log.error("Decryption failed: {}", e.getMessage());
            ResetEncryptor(); // 재초기화 시도
            return encryptor.decrypt(encStr); // 재시도
        }
    }

    public static void setEncryptKey(String key) {
        encryptKey = key;
    }

    public static void setEncryptSalt(String salt) {
        encryptSalt = salt;
    }
}
