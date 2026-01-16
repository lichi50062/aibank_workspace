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
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;

// @formatter:off
/**
 * @(#)EAIDateConverter.java
 * 
 * <p>Description:電文欄位(xsd:dateTime)日期時間轉換器，輸入格式包含【c】，以民國年格式處理</p>
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
public class EAIDateTimeConverter implements EAIConverter {

    private static final IBLog logger = IBLog.getLog(EAIDateTimeConverter.class);

    /** XML日期時間格式 */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /** 民國年格式 */
    private static final String ROC_DATETIME_PATTERN = "ccc/MM/dd HH:mm:ss";
    private static final String SIMPLE_ROC_DATETIME_PATTERN = "cccMMdd HH:mm:ss";
    private static final String SIMPLE_ROC_DATETIME_PREFIX_ZERO_PATTERN = "0cccMMdd HH:mm:ss";

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

        // 取出時間
        String time = DateFormatUtils.CE_TIME_FORMAT.format(inputDate);

        // 民國年格式
        if (StringUtils.indexOf(outputPattern, "c") != -1) {
            switch (outputPattern) {
            case ROC_DATETIME_PATTERN:
                return DateUtils.getROCDateStr(inputDate) + " " + time;
            case SIMPLE_ROC_DATETIME_PATTERN:
                return DateUtils.getROCDateStr(inputDate, StringUtils.EMPTY) + " " + time;
            case SIMPLE_ROC_DATETIME_PREFIX_ZERO_PATTERN:
                return "0" + DateUtils.getROCDateStr(inputDate) + " " + time;
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
            case ROC_DATETIME_PATTERN:
                Date date1 = DateUtils.getROCDate(StringUtils.left(input, 9)); // 將 ROC 字串，轉成 Date 物件
                return DateFormatUtils.format(getDateWithTime(date1, StringUtils.right(inputPattern, 8)), outputPattern);
            case SIMPLE_ROC_DATETIME_PATTERN:
                Date date2 = DateUtils.getSimpleROCDate(StringUtils.left(input, 7)); // 將 ROC 字串，轉成 Date 物件
                return DateFormatUtils.format(getDateWithTime(date2, StringUtils.right(inputPattern, 8)), outputPattern);
            case SIMPLE_ROC_DATETIME_PREFIX_ZERO_PATTERN:
                Date date3 = DateUtils.getROCDate(input.substring(1), StringUtils.EMPTY); // 將 ROC 字串，轉成 Date 物件
                return DateFormatUtils.format(getDateWithTime(date3, StringUtils.right(inputPattern, 8)), outputPattern);
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
            case "yyyy-MM-dd HH:mm:ss.SSS":
                return DateFormatUtils.SQL_DATETIME_FORMAT.parse(input);
            case "yyyyMMddHHmmss":
                return DateFormatUtils.SIMPLE_DATETIME_FORMAT.parse(input);
            case "yyyy/MM/dd HH:mm:ss":
                return DateFormatUtils.CE_DATETIME_FORMAT.parse(input);
            case "yyyy-MM-dd'T'HH:mm:ss.SSS":
                return DateFormatUtils.CLIENTDT_FORMAT.parse(input);
            case "yyyy-MM-dd'T'HH:mm:ssZZ":
                return DateFormatUtils.CLIENTDT_4_FORMAT.parse(input);
            default:
                return DateUtils.getDateByDateFormat(input, pattern);
            }
        }
        catch (ParseException e) {
            logger.error("parse to date, pattern ({}) throw exception.", pattern);
        }
        return null;
    }

    private Date getDateWithTime(Date date, String time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, str2Int(time.split(":")[0]));
        cal.set(Calendar.MINUTE, str2Int(time.split(":")[1]));
        cal.set(Calendar.SECOND, str2Int(time.split(":")[2]));
        return cal.getTime();
    }

    private int str2Int(String str) {
        if (StringUtils.isNotBlank(str) && NumberUtils.isCreatable(str)) {
            return Integer.parseInt(str);
        }
        return 0;
    }

}
