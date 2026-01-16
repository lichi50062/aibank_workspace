/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.converter;

import java.util.Date;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;

// @formatter:off
/**
 * @(#)EAITimeConverter.java
 * 
 * <p>Description:電文欄位(xsd:time)的時間轉換器</p>
 * <p>第一個參數：欄位的值</p>
 * <p>第二個參數：輸入日期格式，RS限定</p>
 * <p>第三個參數：輸出日期格式，RQ限定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAITimeConverter implements EAIConverter {

    private static final IBLog logger = IBLog.getLog(EAITimeConverter.class);

    /** XML日期時間格式 */
    private static final String INPUT_DEFAULT_PATTERN = "HH:mm:ss.SSS";

    /** XML日期時間格式 */
    private static final String OUTPUT_DEFAULT_PATTERN = "HH:mm:ss";

    @Override
    public String convertRq(Object... value) {
        String input = (String) value[0];
        String inputPattern = StringUtils.defaultIfBlank((String) value[1], INPUT_DEFAULT_PATTERN);
        String outputPattern = (String) value[2];
        if (StringUtils.isBlank(input)) {
            return input;
        }

        Date inputDate = parse2Date(input, inputPattern);
        if (inputDate == null) {
            return input;
        }

        return DateFormatUtils.format(inputDate, outputPattern);
    }

    @Override
    public String convertRs(Object... value) {
        String input = (String) value[0];
        String inputPattern = (String) value[1];
        String outputPattern = StringUtils.defaultIfBlank((String) value[2], OUTPUT_DEFAULT_PATTERN);
        if (StringUtils.isBlank(input)) {
            return input;
        }

        Date inputDate = parse2Date(input, inputPattern);
        if (inputDate == null) {
            return input;
        }

        return DateFormatUtils.format(inputDate, outputPattern);
    }

    private Date parse2Date(String value, String pattern) {

        String input = StringUtils.trimToEmptyEx(value);
        if (StringUtils.isBlank(input)) {
            return null;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("parse2Date(String, String), input: {}, pattern: {}", input, pattern);
        }

        return DateUtils.getDateByDateFormat(input, pattern);
    }

}
