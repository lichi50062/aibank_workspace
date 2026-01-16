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
package com.ibm.tw.ibmb.server.gateway.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @formatter:off
/**
 * @(#)HeaderParamUtils.java
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
public class HeaderParamUtils {
    private static final Logger logger = LoggerFactory.getLogger(HeaderParamUtils.class);

    private static final Map<String, String> pathCharWhiteList = new HashMap<>();

    private static final String CHAR_WHITE_LIST = " ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ＿１２３４５６７８９０？：．／abcdefghijklmnopqrstuvwxyz_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ./?=,:;(){}\\\"+|!#@$%^&*~-'[]";

    private HeaderParamUtils() {
        throw new IllegalStateException("ValidateParamUtils");
    }

    static {
        String pathCharWhiteListSourceHasSpecial = CHAR_WHITE_LIST;
        for (char c : pathCharWhiteListSourceHasSpecial.toCharArray()) {
            String charStr = c + "";
            pathCharWhiteList.put(charStr, charStr);
        }
    }

    /**
     * 驗證參數
     *
     * @param param
     * @return
     */
    public static String validParam(String param) {
        if (param != null && !param.isBlank()) {
            return validByList(param, pathCharWhiteList);
        }
        return "";
    }

    private static String validByList(String param, Map<String, String> paramList) {
        if (param == null || param.length() <= 0) {
            return "";
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

}
