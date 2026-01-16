package com.ibm.tw.commons.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.ibmb.util.IBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES 加解密
 * 
 * @author jeff
 */
public class AESUtils {

    private static final Logger log = LoggerFactory.getLogger(AESUtils.class);
    private static Pattern m_aPattern = Pattern.compile("~[a-fA-F0-9]*~");
    private static final String CONFIG = new String(Base64.getDecoder().decode("QUVT"), StandardCharsets.UTF_8); // 解決 Fortify 掃描 Code Correctness: Byte Array to String Conversion

    private AESUtils() {
    }

    /**
     * @param msg
     * @return
     * @throws CryptException
     */
    public static String encrypt(String msg, byte[] keyBytes) throws CryptException {
        return new String(Hex.encodeHex(encryptBytes(msg.getBytes(), new SecretKeySpec(keyBytes, CONFIG))));
    }

    /**
     * @param msg
     * @param secretKeySpec
     * @return
     * @throws Exception
     */
    public static byte[] encryptBytes(byte[] msg, SecretKeySpec secretKeySpec) throws CryptException {
        try {
            Cipher cipher = Cipher.getInstance(CONFIG);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(msg);
        }
        catch (Exception e) {
            throw new CryptException("encrypt failure", e);
        }
    }

    /**
     * @param msg
     * @param spec
     * @return
     * @throws CryptException
     */
    public static byte[] decryptBytes(byte[] msg, SecretKeySpec spec) throws CryptException {
        try {
            Cipher cipher = Cipher.getInstance(CONFIG);
            cipher.init(Cipher.DECRYPT_MODE, spec);
            return cipher.doFinal(msg);
        }
        catch (Exception e) {
            throw new CryptException("decrypt failure", e);
        }
    }

    /**
     * 取得 加入識別字 資料加密
     * 
     * @param hexString
     * @return
     */
    public static String getMaskEncrypt(String sHexString) {
        return "~" + sHexString + "~";
    }

    /**
     * 是否為加入識別字的DES加密資料
     * 
     * @param hexString
     */
    public static boolean isMaskDESEncrypt(String sHexString) {
        return m_aPattern.matcher(sHexString).matches();
    }

    /**
     * 取得 去除識別字 資料加密
     * 
     * @param makeHexString
     * @return
     */
    public static String getEncrypt(String sMaskHexString) {
        return sMaskHexString.replaceAll("~", "");
    }

    /**
     * CBC
     * 
     * @param msg
     * @param secretKeySpec
     * @return
     * @throws CryptException
     */
    public static byte[] encryptBytesInCBC(byte[] msg, SecretKeySpec secretKeySpec, IvParameterSpec ivspec) throws CryptException {
        try {
            if (ivspec == null) {
                throw new IllegalArgumentException("ivspec is requires");
            }
            String transformation = "AES/CBC/PKCS5PADDING";
            // #4504 Weak Encryption: Insecure Mode of Operation 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);
            Cipher cipher = Cipher.getInstance(accessRSAPadding);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
            return cipher.doFinal(msg);
        }
        catch (Exception e) {
            throw new CryptException("encrypt failure", e);
        }
    }

    /**
     * CBC
     * 
     * @param msg
     * @param spec
     * @return
     * @throws CryptException
     */
    public static byte[] decryptBytesInCBC(byte[] msg, SecretKeySpec spec, IvParameterSpec ivspec) throws CryptException {
        try {
            if (ivspec == null) {
                throw new IllegalArgumentException("ivspec is required");
            }
            String transformation = "AES/CBC/PKCS5PADDING";
            // #4504 Weak Encryption: Insecure Mode of Operation 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);
            Cipher cipher = Cipher.getInstance(accessRSAPadding);
            cipher.init(Cipher.DECRYPT_MODE, spec, ivspec);
            return cipher.doFinal(msg);
        }
        catch (Exception e) {
            throw new CryptException("decrypt failure", e);
        }
    }

    public static byte[] encryptCoefficient(byte[] msg, SecretKeySpec secretKeySpec) throws CryptException {
        try {
            Cipher cipher = Cipher.getInstance(CONFIG);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(msg);
        }
        catch (Exception e) {
            throw new CryptException("encrypt failure", e);
        }
    }

    public static String encryptDataToBase64(String plainText, byte[] aesKxys, byte[] aesIv) throws CryptException {
        byte[] dd = encryptToBytes(plainText.getBytes(), aesKxys, aesIv);
        return Base64.getEncoder().encodeToString(dd);
    }

    public static String decryptDataFromBase64(String base64CipherText, byte[] aesKxys, byte[] aesIv) throws CryptException {
        byte[] dd = decryptToBytes(Base64.getDecoder().decode(base64CipherText), aesKxys, aesIv);
        return new String(dd, StandardCharsets.UTF_8);
    }

    public static byte[] encryptToBytes(byte[] plainTextBytes, byte[] aesKxys, byte[] aesIv) throws CryptException {
        SecretKeySpec skxySpec = new SecretKeySpec(aesKxys, CONFIG);
        IvParameterSpec ivSpec = new IvParameterSpec(aesIv);
        try {
            String transformation = "AES/CBC/PKCS5PADDING";
            // #4504 Weak Encryption: Insecure Mode of Operation 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);
            Cipher cipher = Cipher.getInstance(accessRSAPadding);
            cipher.init(Cipher.ENCRYPT_MODE, skxySpec, ivSpec);
            return cipher.doFinal(plainTextBytes);
        }
        catch (Exception e) {
            throw new CryptException("encrypt failure", e);
        }
    }

    public static byte[] encryptToBytes(byte[] plainTextBytes, byte[] aesKxys) throws CryptException {
        SecretKeySpec skxySpec = new SecretKeySpec(aesKxys, "AES");
        String transformation = "AES/ECB/PKCS5Padding";
        try {
            // #4504 Weak Encryption: Insecure Mode of Operation 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);

            Cipher cipher = Cipher.getInstance(accessRSAPadding);
            cipher.init(Cipher.ENCRYPT_MODE, skxySpec);
            return cipher.doFinal(plainTextBytes);
        }
        catch (Exception e) {
            throw new CryptException("encrypt failure", e);
        }
    }

    public static byte[] decryptToBytes(byte[] cipherBytes, byte[] aesKxys, byte[] aesIv) throws CryptException {
        SecretKeySpec skxySpec = new SecretKeySpec(aesKxys, CONFIG);
        IvParameterSpec ivSpec = new IvParameterSpec(aesIv);
        try {
            String transformation = "AES/CBC/PKCS5PADDING";
            // #4504 Weak Encryption: Insecure Mode of Operation 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);
            Cipher cipher = Cipher.getInstance(accessRSAPadding);
            cipher.init(Cipher.DECRYPT_MODE, skxySpec, ivSpec);
            return cipher.doFinal(cipherBytes);
        }
        catch (Exception e) {
            throw new CryptException("decrypt failure", e);
        }
    }

    public static byte[] decryptToBytes(byte[] cipherBytes, byte[] aesKxys) throws CryptException {
        try {
            // #4504 Weak Encryption: Insecure Mode of Operation
            String accessAES = IBUtils.toDataModel("AES", String.class);
            SecretKeySpec skxySpec = new SecretKeySpec(aesKxys, accessAES);
            String transformation = "AES/ECB/PKCS5Padding";
            // #4504 Weak Encryption: Insecure Mode of Operation 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);

            Cipher cipher = Cipher.getInstance(accessRSAPadding);
            cipher.init(Cipher.DECRYPT_MODE, skxySpec);
            return cipher.doFinal(cipherBytes);
        }
        catch (Exception e) {
            throw new CryptException("decrypt failure", e);
        }
    }
}
