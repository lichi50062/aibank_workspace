/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 * 
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.crypto;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)AESCipherUtils.java
 * 
 * <p>Description:AES Cipher 工具程式, 可指定不同的 SexretKeyProvider</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/19, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AESCipherUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static Log logger = LogFactory.getLog(AESCipherUtils.class);
    /**
     * 密鑰提供者 instance
     */
    private SecretKeyProvider<?> secretKeyProvider = null;

    /**
     * 預設建構子 (使用 DummySecretKeyProvider)
     */
    public AESCipherUtils() {
        this(SecretKeyProviderFactory.getInstance().getProvider(AESCipherUtils.class));
    }

    /**
     * 依指定 secretKeyProvider 產生工具程式實例
     *
     * @param secretKeyProvider
     */
    public AESCipherUtils(SecretKeyProvider<?> secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    /**
     * 將傳入的字串加密
     *
     * @param msg
     * @return hex encoded string
     * @throws CryptException
     * @throws
     */
    public String encrypt(String msg, String encoding) throws CryptException {
        if (StringUtils.isBlank(msg)) {
            return msg;
        }
        try {
            return new String(Hex.encodeHex(AESUtils.encryptBytes(msg.getBytes(encoding), getSecretKey())));
        }
        catch (UnsupportedEncodingException e) {
            throw new CryptException("error decoding input string to bytes", e);
        }
    }

    /**
     * 將傳入的字串加密
     *
     * @param msg
     * @return hex encoded string
     * @throws CryptException
     * @throws
     */
    public String encrypt(String msg, Charset encoding) throws CryptException {
        if (StringUtils.isBlank(msg)) {
            return msg;
        }
        return new String(Hex.encodeHex(AESUtils.encryptBytes(msg.getBytes(encoding), getSecretKey())));
    }

    /**
     * 將傳入的字串
     *
     * @param plainTextBytes
     * @return
     * @throws CryptException
     */
    public byte[] encrypt(byte[] plainTextBytes) throws CryptException {
        return AESUtils.encryptBytes(plainTextBytes, getSecretKey());
    }

    /**
     * 將傳入的 hex string 解密
     * 
     * @param hexString
     * @param encoding
     * @return
     * @throws CryptException
     */
    public String decrypt(String hexString, String encoding) throws CryptException {
        if (StringUtils.isBlank(hexString)) {
            return hexString;
        }
        try {
            return new String(AESUtils.decryptBytes(Hex.decodeHex(hexString.toCharArray()), getSecretKey()), encoding);
        }
        catch (UnsupportedEncodingException | DecoderException e) {
            throw new CryptException("error decoding", e);
        }
    }

    /**
     * 將傳入的 hex string 解密
     * 
     * @param hexString
     * @param encoding
     * @return
     * @throws CryptException
     */
    public String decrypt(String hexString, Charset encoding) throws CryptException {
        if (StringUtils.isBlank(hexString)) {
            return hexString;
        }
        try {
            return new String(AESUtils.decryptBytes(Hex.decodeHex(hexString.toCharArray()), getSecretKey()), encoding);
        }
        catch (DecoderException e) {
            // Fortify - Critical - Privacy Violation log 打印調整
            throw new CryptException("error decoding", e);
        }
    }

    /**
     * 將傳入的byte array 解密
     *
     * @param cipherBytes
     * @return
     * @throws CryptException
     */
    public byte[] decrypt(byte[] cipherBytes) throws CryptException {
        return AESUtils.decryptBytes(cipherBytes, getSecretKey());
    }

    /**
     * 回傳 sexret key string
     *
     * @return
     * @throws CryptException
     */
    private SecretKeySpec getSecretKey() throws CryptException {
        if (SecretKeyType.BYTE_ARRAY.equals(secretKeyProvider.getSecretKeyType())) {
            return new SecretKeySpec((byte[]) secretKeyProvider.getSecretKey(), "AES");
        }
        else if (SecretKeyType.HEX_KEY_STRING.equals(secretKeyProvider.getSecretKeyType())) {
            try {
                return new SecretKeySpec(Hex.decodeHex(((String) secretKeyProvider.getSecretKey()).toCharArray()), "AES");
            }
            catch (DecoderException e) {
                throw new CryptException("error decoding key", e);
            }
        }
        else if (SecretKeyType.PASSWORD_STRING.equals(secretKeyProvider.getSecretKeyType())) {
            return new SecretKeySpec(((String) secretKeyProvider.getSecretKey()).getBytes(), "AES");
        }
        else if (SecretKeyType.SECRET_KEY_SPEC.equals(secretKeyProvider.getSecretKeyType())) {
            return (SecretKeySpec) secretKeyProvider.getSecretKey();
        }
        else {
            throw new CryptException("unknown secretKey type");
        }
    }

    /**
     * 將傳入字串加密
     *
     * @param plainText
     *            (會以 UTF-8 decode 成 bytes)
     * @return hex encoded cipher text
     * @throws CryptException
     */
    public String encrypt(String plainText) throws CryptException {
        // 傳入參數為 空值 或 空字串，則直接返回
        if (StringUtils.isBlank(plainText)) {
            return plainText;
        }
        return encrypt(plainText, DEFAULT_ENCODING);
    }

    /**
     * 將傳入Hex string decode 後解密
     *
     * @param hexString
     * @return 解密後字串, 以 UTF-8 encoded.
     * @throws CryptException
     */
    public String decrypt(String hexString) throws CryptException {
        if (StringUtils.isBlank(hexString)) {
            return hexString;
        }
        return decrypt(hexString, DEFAULT_ENCODING);
    }

    /**
     * 將傳入Hex string decode 後解密
     *
     * @param hexString
     * @return 解密後字串, 以 UTF-8 encoded.
     * @throws CryptException
     */
    public String decryptQuietly(String hexString) {
        if (StringUtils.isBlank(hexString)) {
            return hexString;
        }
        try {
            return decrypt(hexString, DEFAULT_ENCODING);
        }
        catch (CryptException e) {
            if (logger.isDebugEnabled()) {
                // fortify - Critical - Privacy Violation fix
                logger.debug("error decrypting input string , ex:" + e.getMessage());
            }
            return hexString;
        }
    }
}
