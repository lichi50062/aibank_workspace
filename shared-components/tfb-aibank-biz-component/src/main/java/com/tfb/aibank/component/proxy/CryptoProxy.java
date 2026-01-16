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
package com.tfb.aibank.component.proxy;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)CryptoProxy.java
 * 
 * <p>Description:透過此代理器取得HSM的加解密服務(取得HSM連線完後即斷線)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CryptoProxy {
    public static enum CipherBlockingType {
        CBC(2), ECB(1);

        int hsmBlockingType;

        private CipherBlockingType(int hsmBlockingType) {
            this.hsmBlockingType = hsmBlockingType;
        }

        /**
         * 取得 hsmBlockingType
         *
         * @return 傳回 hsmBlockingType。
         */
        public int getHsmBlockingType() {
            return hsmBlockingType;
        }
    }

    // @formatter:off
    /**
     * @(#)CryptoProxy.java
     * 
     * <p>Description:HSM 環境 & encoding 列表</p>
     * 
     * <p>Modify History:</p>
     * v1.0, 2023/06/16, Horance Chou	
     * <ol>
     *  <li>初版</li>
     * </ol>
     */
    // @formatter:on
    public static enum HSMEnvType {

        FOR_IB_EATM(0, "CP937"),

        FOR_IB_LOGIN(1, "CP1047"),

        FOR_IB_DB(2, "UTF-8"),

        FOR_WEBATM_RSA(3, "UTF-8"),

        FOR_MOBILE_MAC(4, "CP1047"),
        // 無卡提款，新增ASCII編碼
        FOR_CARDLESS(5, "CP850");

        private int code;
        private String charset;

        private HSMEnvType(int code, String charset) {
            this.code = code;
            this.charset = charset;
        }

        /**
         * 取得 charset
         *
         * @return 傳回 charset。
         */
        public String getCharset() {
            return charset;
        }

        /**
         * 取得 code
         *
         * @return 傳回 code。
         */
        public int getCode() {
            return code;
        }
    }

    public static char[] getChars(byte[] bytes, String encoding) {
        Charset cs = getCharset(encoding);
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    public static String getSafeString(byte[] bytes, String encoding) {
        StringBuilder sBuilder = new StringBuilder();
        char[] byteToChar = getChars(bytes, encoding);
        for (int i = 0; i < byteToChar.length; i++) {
            sBuilder.append(byteToChar[i]);
        }
        Arrays.fill(byteToChar, ' ');
        return sBuilder.toString();
    }

    public static Charset getCharset(String charsetName) {
        try {
            return Charset.forName(charsetName);
        }
        catch (UnsupportedCharsetException e) {
            String[] aliases = new String[] { "Big5-HKSCS", "GBK", "GB2312" };
            for (String alias : aliases) {
                if (StringUtils.indexOfIgnoreCase(charsetName, alias) != -1) {
                    return Charset.forName(alias);
                }
            }
            return Charset.defaultCharset();
        }
    }

}
