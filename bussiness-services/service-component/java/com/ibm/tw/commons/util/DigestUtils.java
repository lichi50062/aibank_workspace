/*
 * =========================================================================== IBM Confidential AIS Source Materials
 * 
 * 
 * (C) Copyright IBM Corp. 2007.
 * 
 * ===========================================================================
 */
package com.ibm.tw.commons.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ibm.tw.commons.exception.CryptException;

/**
 * 資料簽章工具<BR>
 * 支援下列演算法:
 * <li>MD5</li>
 * <li>SHA-1</li>
 * <li>SHA-256</li>
 * 
 * @author Kevin
 * @version 1.0, 2007/7/5
 * @see
 * @since
 */
@SuppressWarnings("deprecation")
public class DigestUtils extends org.apache.commons.codec.digest.DigestUtils {

    /**
     * 將資料透過SHA-256雜湊加密
     * 
     * @param content
     * @return
     */
    public static byte[] sha256TFB(String content) {
        return sha256TFB(content.getBytes());
    }

    /**
     * 將資料透過SHA-256雜湊加密
     * 
     * @param content
     * @return
     */
    public static byte[] sha256TFB(byte[] content) {

        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-256");
            return sha.digest(content);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 取得sha 256 hex string
     * 
     * @param content
     * @return
     * @throws CryptException
     */
    public static String sha256HexTFB(String content) {
        return ConvertUtils.byteArray2HexString(sha256TFB(content));
    }

    /**
     * 將資料透過SHA-512雜湊加密
     * 
     * @param content
     * @return
     */
    public static byte[] sha512TFB(String content) {
        return sha512TFB(content.getBytes());
    }

    /**
     * 將資料透過SHA-512雜湊加密
     * 
     * @param content
     * @return
     */
    public static byte[] sha512TFB(byte[] content) {

        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-512");
            return sha.digest(content);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 取得sha 512 hex string
     * 
     * @param content
     * @return
     * @throws CryptException
     */
    public static String sha512HexTFB(String content) {
        return ConvertUtils.byteArray2HexString(sha512TFB(content));
    }

}
