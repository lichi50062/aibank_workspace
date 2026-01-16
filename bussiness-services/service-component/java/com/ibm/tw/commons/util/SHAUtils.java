package com.ibm.tw.commons.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ibm.tw.commons.exception.CryptException;

/**
 * SHA 加密
 * 
 * @author Evan
 */
public class SHAUtils {

    private static String DEFAULT_SALT = "defaultSalt";

    private SHAUtils() {
    }

    /**
     * SHA 加密
     * 
     * @param text
     * @param salt
     * @return
     * @throws CryptException
     */
    public static String hashWithSHA256AndSalt(String text) throws CryptException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(DEFAULT_SALT.getBytes(StandardCharsets.UTF_8));
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        }
        catch (NoSuchAlgorithmException e) {
            throw new CryptException("encrypt failure", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
