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

    public static void ResetEncryptor() {
        log.error("ResetEncryptor");
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(EncUtil.encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setSaltGenerator(new StringFixedSaltGenerator(EncUtil.encryptSalt));
    }

    public EncUtil(@Value("${common.encrypt.key}") String encryptKey,
                   @Value("${common.encrypt.salt}") String encryptSalt) {
        EncUtil.encryptKey = encryptKey;
        EncUtil.encryptSalt = encryptSalt;
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(EncUtil.encryptKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setSaltGenerator(new StringFixedSaltGenerator(EncUtil.encryptSalt));
    }



    public static String encrypt(String str) {
        String encStr = null;
        if (str == null || str.equals("null") || str.isEmpty() || str.trim().length() < 1) {
            log.error("encrypt parameter null");
            return null;
        }
        try {
            encStr = encryptor.encrypt(str);
        } catch (Exception e) {
            log.error("encrypt getMessage {}", e.getMessage());
            e.printStackTrace();

            ResetEncryptor();
        }
        return encStr;
    }

    public static String decrypt(String encStr) {
        String decStr = null;
        if (encStr == null || encStr.equals("null") || encStr.isEmpty() || encStr.trim().length() < 1) {
            log.error("decrypt parameter null");
            return null;
        }
        try {
            decStr = encryptor.decrypt(encStr);
        } catch (Exception e) {
            log.error("decrypt getMessage {}", e.getMessage());
            e.printStackTrace();

            ResetEncryptor();
        }
        return decStr;
    }



}
