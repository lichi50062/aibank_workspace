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
package com.ibm.tw.ibmb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.owasp.encoder.Encode;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)ValidateParamUtils.java
 * 
 * <p>Description:應  fortify 不可信賴參數驗證問題 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ValidateParamUtils {

    private static final IBLog logger = IBLog.getLog(ValidateParamUtils.class);

    private static final Map<String, String> pathCharWhiteList = new HashMap<>();

    private static final String CHAR_WHITE_LIST = " ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ＿１２３４５６７８９０？：．／abcdefghijklmnopqrstuvwxyz_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ./?=,:;(){}\\\"+|!#@$%^&*~-'[]";

    private ValidateParamUtils() {
        throw new IllegalStateException("ValidateParamUtils");
    }

    static {
        String pathCharWhiteListSourceHasSpecial = CHAR_WHITE_LIST;
        if (StringUtils.isNotBlank(pathCharWhiteListSourceHasSpecial)) {
            for (char c : pathCharWhiteListSourceHasSpecial.toCharArray()) {
                String charStr = c + "";
                pathCharWhiteList.put(charStr, charStr);
            }
        }
    }

    /**
     * 驗證參數
     *
     * @param param
     * @return
     */
    public static String validParam(String param) {
        String objStr = JsonUtils.getJson(param);
        String resultStr = JsonUtils.getObject(objStr, String.class);
        return validByList(resultStr, pathCharWhiteList);
    }

    /**
     * 驗證參數
     *
     * @param param
     * @return
     */
    public static List<String> validParam(List<String> params) {
        String joinedParam = String.join(",", params);
        String objStr = JsonUtils.getJson(joinedParam);
        String resultStr = JsonUtils.getObject(objStr, String.class);

        String validateResult = validByList(resultStr, pathCharWhiteList);
        return Arrays.asList(validateResult.split(","));
    }

    /**
     * 驗證參數
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object validParamObj(Object params) {
        if (params instanceof Map) {
            return validParam((Map<String, Object>) params);
        }
        else {
            return validParam((String) params);
        }
    }

    /**
     * 驗證 email 參數 Map<String, Object> params
     * 
     * @param params
     * @return
     */
    public static Map<String, Object> validParam(Map<String, Object> params) {

        if (params == null) {
            return params;
        }
        Map<String, Object> sanitized = new HashMap<>();

        params.forEach((key, value) -> {
            if (value instanceof String) {
                sanitized.put(key, validParam((String) value));
            }
            else if (value != null) {
                sanitized.put(key, value); // Add other validations as needed
            }
        });
        return sanitized;
    }

    /**
     * 驗證參數 List
     * 
     * @param params
     * @return
     */
    public static List<String> validParamList(List<String> params) {
        if (CollectionUtils.isEmpty(params)) {
            return new ArrayList<>();
        }
        return params.stream().map(param -> validParam(param)).toList();
    }

    /**
     * 驗證參數 Set
     * 
     * @param params
     * @return
     */
    public static Set<String> validParamSet(Set<String> params) {
        if (CollectionUtils.isEmpty(params)) {
            return new HashSet<>();
        }
        Set<String> paramSet = new HashSet<>();
        params.forEach((param) -> {
            paramSet.add(validParam(param));
        });
        return paramSet;
    }

    private static String validByList(String param, Map<String, String> paramList) {
        if (StringUtils.isBlank(param)) {
            return StringUtils.EMPTY;
        }

        StringBuilder temp = new StringBuilder();
        boolean isHasDecode = false;
        try {
            // fortify 使其支援中文(白名單驗證百分比編碼)
            param = URLEncoder.encode(param, "UTF-8");
            isHasDecode = true;
        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        for (int i = 0; i < param.length(); ++i) {

            temp.append(paramList.get(param.charAt(i) + ""));

        }
        param = temp.toString();
        try {
            if (isHasDecode) {
                return URLDecoder.decode(param, "UTF-8");
            }
            else {
                return param;
            }

        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return param;
        }
    }

    /**
     * htmlContent 驗證
     * 
     * @param htmlContent
     * @return
     */
    public static String validateHtmlContent(String htmlContent) {
        String encode = Encode.forHtml(htmlContent);
        String unEncode = encode.replaceAll("&amp;", "&").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&#34;", "\"").replaceAll("&#39;", "'");
        return unEncode;
    }

}
