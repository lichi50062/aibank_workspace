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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @formatter:off
/**
 * @(#)Convert.java
 * 
 * <p>Description:電文欄位轉換標註</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Converter {

    /** 自定義轉換 */
    CustomConverter[] customConverters() default {};

    /** 日期和時間轉換 */
    DateAndTimeConverter[] datetimeConverters() default {};

    /** 數值轉換 */
    DecimalConverter[] decimalConverters() default {};

}
