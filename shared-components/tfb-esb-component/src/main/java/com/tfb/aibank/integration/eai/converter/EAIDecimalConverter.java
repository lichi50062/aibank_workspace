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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.ArrayUtils;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)EAIDecimalConverter.java
 * 
 * <p>Description:電文欄位(xsd:decimal)的數值轉換器</p>
 * <p>第一個參數：欄位的值</p>
 * <p>第二個參數：小數點位數</p>
 * <p>第三個參數：數字格式</p>
 * <p>第四個參數：正負號後置</p>
 * <p>第五個參數：顯示正號</p>
 * <p>第六個參數：排除不處理的值</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIDecimalConverter implements EAIConverter {

    private static final IBLog logger = IBLog.getLog(EAIDecimalConverter.class);

    @Override
    public String convertRq(Object... value) {
        String input = StringUtils.trimToEmptyEx((String) value[0]);
        int scale = (int) value[1];
        String pattern = (String) value[2];
        boolean isPostSign = (boolean) value[3];
        boolean showPlusSign = (boolean) value[4];

        if (StringUtils.isBlank(input)) {
            return "0";
        }

        // 原始的傳入值可直接以 new BigDecimal 轉回
        BigDecimal oriNumber = new BigDecimal(input);
        BigDecimal number = new BigDecimal(input);
        number = number.abs(); // 絕對值

        String sign = (-1 == oriNumber.signum()) ? "-" : (showPlusSign ? "+" : "");
        pattern = isPostSign ? pattern + sign : sign + pattern;

        if (number != null && scale != 0) {
            number = number.scaleByPowerOfTen(-scale);
        }

        // 依 pattern 決定回傳格式
        if (StringUtils.isNotBlank(pattern)) {
            return new DecimalFormat(pattern).format(number);
        }
        else {
            return number == null ? null : number.toPlainString();
        }
    }

    @Override
    public String convertRs(Object... value) {
        String input = StringUtils.trimToEmptyEx((String) value[0]);
        int scale = (int) value[1];
        String pattern = (String) value[2];
        boolean isPostSign = (boolean) value[3];
        boolean showPlusSign = (boolean) value[4];
        String[] exclude = (String[]) value[5];

        // 必須回傳值，以避免 XmlValueOutOfRangeException
        if (StringUtils.isBlank(input) || StringUtils.equalsAny(input, "+", "-")) {
            return "0";
        }

        String sign = null;
        char signChar = isPostSign ? input.charAt(input.length() - 1) : input.charAt(0);
        if (signChar == '+' || signChar == '-') {
            sign = (signChar == '+' && !showPlusSign) ? "" : String.valueOf(signChar);
            input = sign + (isPostSign ? input.substring(0, input.length() - 1) : input.substring(1));
        }

        // if all of char is '0'
        if (input.matches("0+")) {
            return "0";
        }

        BigDecimal number = null;
        if (StringUtils.isNotBlank(pattern)) {
            try {
                if (ArrayUtils.isEmpty(exclude)) {
                    number = new BigDecimal(new DecimalFormat(pattern).parse(input).toString());
                }
                else {
                    // 欄位值本身非數字，使用 exclude 進行排除
                    if (!ArrayUtils.contains(exclude, input)) {
                        number = new BigDecimal(new DecimalFormat(pattern).parse(input).toString());
                    }
                }
            }
            catch (ParseException e) {
                logger.error("error parsing input string: {}, pattern: {}", input, pattern);
                throw new RuntimeException("error parsing input string: ", e);
            }
        }
        else {
            number = new BigDecimal(input);
        }

        if (number != null) {
            if (scale != 0) {
                number = number.scaleByPowerOfTen(-scale);
            }
            return number.toPlainString();
        }
        else {
            return "0";
        }
    }
}
