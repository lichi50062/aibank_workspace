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
package com.tfb.aibank.biz.component.twcaapi;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.message.BasicHeader;

import com.google.gson.reflect.TypeToken;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiRq;
import com.tfb.aibank.biz.component.twcaapi.model.TwcaApiRs;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)MidUtils.java
 * 
 * <p>Description:台網驗證 - 共用工具</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/14, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaUtils {

    private static final IBLog logger = IBLog.getLog(TwcaUtils.class);

    private static final String S_H_A_256 = new String(Base64.getDecoder().decode("U0hBLTI1Ng=="), StandardCharsets.UTF_8); // 解決 Fortify 掃描 Code Correctness: Byte Array to String Conversion

    private static final char[] hexDictionaryArray = "0123456789ABCDEF".toCharArray();

    private static final String CONTENT_TYPE_FORM_URLENCODED_UTF8 = "application/x-www-form-urlencoded; charset=utf-8";

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
    public static boolean verifyRespIdentifyNo(TwcaApiRs rs, String hashKey) throws NoSuchAlgorithmException {
        boolean isValid = false;
        String identityNo = rs.getIdentifyNo();
        String identityNoFromData = getIdentifyNo(rs.getIdentifyData(hashKey));
        logger.info("[TwcaUtils][verifyRespIdentifyNo] identityNo={}", identityNo);
        logger.info("[TwcaUtils][verifyRespIdentifyNo] identityNoFromData={}", identityNoFromData);
        if (StringUtils.isBlank(identityNo)) {
            logger.warn("[TwcaUtils][verifyRespIdentifyNo] Response沒有IdentifyNo");
        }
        else if (StringUtils.equals(identityNo, identityNoFromData)) {
            logger.info("[TwcaUtils][verifyRespIdentifyNo] IdentifyNo驗證相符");
            isValid = true;
        }
        else {
            logger.error("[TwcaUtils][verifyRespIdentifyNo] IdentifyNo驗證不相符");
        }
        return isValid;
    }

    public static <T extends TwcaApiRs> T sendAndReceive(TwcaApiRq rq, Class<T> responseType, String connectUrl, int timeout) throws ActionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchAlgorithmException {

        Map<String, Object> params = GsonHelper.newInstance().fromJson(JsonUtils.getJson(rq), new TypeToken<Map<String, Object>>() {
        }.getType());

        Map<String, String> formData = new HashMap<>();
        for (Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                formData.put(entry.getKey(), entry.getValue().toString());
            }
        }

        logger.info("Twca api Service: Call={}, Payload={}", connectUrl, formData);

        List<Header> headers = Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_FORM_URLENCODED_UTF8));

        CustomHttpResponse httpResponse = HttpClient5.doPost(connectUrl, headers, formData, timeout);

        int statusCode = httpResponse.getCode();
        String rsPayload = StringUtils.defaultString(httpResponse.getResponse());

        logger.info("Twca api Service: StatusCode={}, Payload={}", statusCode, rsPayload);

        if (statusCode != HttpStatus.SC_SUCCESS || StringUtils.isBlank(rsPayload)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TWCA_API_SERVICE_ERROR);
        }

        return JsonUtils.getObject(rsPayload, responseType);
    }

}
