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
package com.tfb.aibank.integration.eai.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.tfb.aibank.integration.eai.converter.EAIConverter;

// @formatter:off
/**
 * @(#)DecimalConverter.java
 * 
 * <p>Description:電文欄位數值轉換器</p>
 * <pre>
 * scale 可為負數, 若為負數, 則輸入值會被 乘 10 ^ scale
 * 例如: 
 *  value 為 12.34, scale 為 -2
 *  則轉換後的值為 1234
 * 
 * pattern: 上行時會先處理 scale, 再處理 pattern
 * 例如: 
 *  value 為 12.34, scale 為 -2, pattern 為 "#,##0"
 *  則轉換後的值為 1,234
 * 
 * 反之, 下行則先以 pattern 處理值, 再處理 scale,
 * 例如:
 *  value 為 1,234,567.12, pattern 為 "#,##0.00", scale 為 -2
 *  則轉換後的值為 BigDecimal(123456712)
 * </pre>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalConverter {

    /** 轉換器類別 */
    Class<? extends EAIConverter> converter();

    /** 小數點位數 */
    int scale() default 0;

    /** 數字格式 */
    String pattern() default "";

    /** 顯示正號 */
    boolean showPlusSign() default false;

    /** 正負號後置 (showPlusSign 為真時才會生效) */
    boolean isPostSign() default false;

    /** 排除不處理的值 */
    String[] exclude() default { "" };

    /** 欄位XPATH */
    String fieldXPath();
}
