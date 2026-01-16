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
 * @(#)DateConverter.java
 * 
 * <p>Description:電文欄位日期和時間轉換器</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAndTimeConverter {

    /** 轉換器類別 */
    Class<? extends EAIConverter> converter();

    /** 輸入日期格式 */
    String inputPattern() default "";

    /** 輸出日期格式 */
    String outputPattern() default "";

    /** 欄位XPATH */
    String fieldXPath();
}
