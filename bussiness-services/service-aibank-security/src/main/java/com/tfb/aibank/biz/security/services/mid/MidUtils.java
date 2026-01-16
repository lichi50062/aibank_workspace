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
package com.tfb.aibank.biz.security.services.mid;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.security.services.mid.bean.MidRs;

// @formatter:off
/**
 * @(#)MidUtils.java
 * 
 * <p>Description:台網MID驗證 - 共用工具</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/05, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MidUtils {

    private MidUtils() {
    }

    private static final IBLog logger = IBLog.getLog(MidUtils.class);

    private static final String S_H_A_256 = new String(Base64.getDecoder().decode("U0hBLTI1Ng=="), StandardCharsets.UTF_8); // 解決 Fortify 掃描 Code Correctness: Byte Array to String Conversion
    private static final char[] hexDictionaryArray = "0123456789ABCDEF".toCharArray();

    /**
     * 取得驗證碼
     * 
     * @param combineStr
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getIdentifyNo(String combineStr) throws NoSuchAlgorithmException {
        return getHashValue(combineStr, StandardCharsets.UTF_16LE);
    }

    private static String getHashValue(String identifyInfo, Charset encoding) throws NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance(S_H_A_256).digest(identifyInfo.getBytes(encoding));
        return bytesToHex(digest).toLowerCase();
    }

    private static String bytesToHex(byte[] data) {
        char[] dataHexChars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            int v = data[i] & 0xFF;
            dataHexChars[i * 2] = hexDictionaryArray[v >>> 4];
            dataHexChars[i * 2 + 1] = hexDictionaryArray[v & 0xF];
        }
        return new String(dataHexChars);
    }

    /**
     * 驗證Response驗證碼
     * 
     * @param rs
     * @param hashKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean verifyRespIdentifyNo(MidRs rs, String hashKey) throws NoSuchAlgorithmException {
        boolean isValid = false;
        String identityNo = rs.getIdentifyNo();
        String identityNoFromData = getIdentifyNo(rs.getIdentifyData(hashKey));
        logger.info("[MidUtils][verifyRespIdentifyNo] identityNo={}", identityNo);
        logger.info("[MidUtils][verifyRespIdentifyNo] identityNoFromData={}", identityNoFromData);
        if (StringUtils.isBlank(identityNo)) {
            logger.warn("[MidUtils][verifyRespIdentifyNo] Response沒有IdentifyNo");
        }
        else if (StringUtils.equals(identityNo, identityNoFromData)) {
            logger.info("[MidUtils][verifyRespIdentifyNo] IdentifyNo驗證相符");
            isValid = true;
        }
        else {
            logger.error("[MidUtils][verifyRespIdentifyNo] IdentifyNo驗證不相符");
        }
        return isValid;
    }

}
