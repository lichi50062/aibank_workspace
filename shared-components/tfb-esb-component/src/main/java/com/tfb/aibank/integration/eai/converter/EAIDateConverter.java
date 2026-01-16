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

import java.text.ParseException;
import java.util.Date;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;

// @formatter:off
/**
 * @(#)EAIDateConverter.java
 * 
 * <p>Description:電文欄位(xsd:date)的日期轉換器，輸入日期格式包含【c】，以民國年格式處理</p>
 * <p>第一個參數：欄位的值</p>
 * <p>第二個參數：輸入日期格式</p>
 * <p>第三個參數：輸出日期格式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIDateConverter implements EAIConverter {

    private static final IBLog logger = IBLog.getLog(EAIDateConverter.class);

    /** XML日期格式 */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    /** 民國年格式 */
    private static final String ROC_DATE_PATTERN = "ccc/MM/dd";
    private static final String SIMPLE_ROC_DATE_PATTERN = "cccMMdd";
    private static final String SIMPLE_ROC_DATE_PREFIX_ZERO_PATTERN = "0cccMMdd";
    private static final String SIMPLE_ROC_DATE_PREFIX_ZERO_PATTERN_2 = "ccccMMdd";
    private static final String SIMPLE_ROC_DATE_WITHOUT_DAY_PATTERN = "cccMM";

    @Override
    public String convertRq(Object... value) {
        String input = (String) value[0];
        String inputPattern = StringUtils.defaultIfBlank((String) value[1], DEFAULT_PATTERN);
        String outputPattern = (String) value[2];

        if (StringUtils.isBlank(input)) {
            return input;
        }

        Date inputDate = parse2Date(input, inputPattern);
        if (inputDate == null) {
            return input;
        }

        // 民國年格式
        if (StringUtils.indexOf(outputPattern, "c") != -1) {
            switch (outputPattern) {
            case ROC_DATE_PATTERN:
                return DateUtils.getROCDateStr(inputDate);
            case SIMPLE_ROC_DATE_PATTERN:
                return DateUtils.getROCDateStr(inputDate, StringUtils.EMPTY);
            case SIMPLE_ROC_DATE_PREFIX_ZERO_PATTERN:
            case SIMPLE_ROC_DATE_PREFIX_ZERO_PATTERN_2:
                return "0" + DateUtils.getROCDateStr(inputDate, StringUtils.EMPTY);
            case SIMPLE_ROC_DATE_WITHOUT_DAY_PATTERN:
                return StringUtils.left(DateUtils.getROCDateStr(inputDate, StringUtils.EMPTY), 5);
            default:
                logger.error("RQ 沒有符合的民國年格式({})，手動添加。", outputPattern);
            }
        }
        // 西元年格式
        else {
            return DateFormatUtils.format(inputDate, outputPattern);
        }
        return input;
    }

    @Override
    public String convertRs(Object... value) {
        String input = StringUtils.trimToEmptyEx((String) value[0]);
        String inputPattern = (String) value[1];
        String outputPattern = StringUtils.defaultIfBlank((String) value[2], DEFAULT_PATTERN);

        if (StringUtils.isBlank(input)) {
            return input;
        }

        // 配合CBS：若下行日期為00..0或99..9則當成空值
        if (StringUtils.equals(input, StringUtils.repeat("0", input.length())) || StringUtils.equals(input, StringUtils.repeat("9", input.length()))) {
            return StringUtils.EMPTY;
        }

        // 民國年格式
        if (StringUtils.indexOf(inputPattern, "c") != -1) {
            switch (inputPattern) {
            case ROC_DATE_PATTERN:
                return DateFormatUtils.format(DateUtils.getROCDate(input), outputPattern);
            case SIMPLE_ROC_DATE_PATTERN:
                return DateFormatUtils.format(DateUtils.getSimpleROCDate(input), outputPattern);
            case SIMPLE_ROC_DATE_PREFIX_ZERO_PATTERN:
            case SIMPLE_ROC_DATE_PREFIX_ZERO_PATTERN_2:
                return DateFormatUtils.format(DateUtils.getROCDate(input.substring(1), StringUtils.EMPTY), outputPattern);
            case SIMPLE_ROC_DATE_WITHOUT_DAY_PATTERN:
                return DateFormatUtils.format(DateUtils.getSimpleROCDate(input + "01"), outputPattern);
            default:
                logger.error("RS 沒有符合的民國年格式({})，手動添加。", inputPattern);
            }
        }
        // 西元年格式
        else {
            Date inputDate = parse2Date(input, inputPattern);
            if (inputDate == null) {
                return input;
            }
            return DateFormatUtils.format(inputDate, outputPattern);
        }

        return input;
    }

    private Date parse2Date(String input, String pattern) {
        if (logger.isDebugEnabled()) {
            logger.debug("parse2Date(String, String), input: {}, pattern: {}", input, pattern);
        }
        try {
            switch (pattern) {
            case "yyyy-MM-dd":
                return DateFormatUtils.SQL_DATE_FORMAT.parse(input);
            case "yyyyMMdd":
                return DateFormatUtils.SIMPLE_DATE_FORMAT.parse(input);
            case "yyyy/MM/dd":
                return DateFormatUtils.CE_DATE_FORMAT.parse(input);
            default:
                return DateUtils.getDateByDateFormat(input, pattern);
            }
        }
        catch (ParseException e) {
            logger.error("parse to date, pattern ({}) throw exception.", pattern);
        }
        return null;
    }

}
