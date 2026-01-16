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
 * @(#)CustomConverter.java
 * 
 * <p>Description:電文欄位自定義轉換器</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomConverter {

    /** 轉換器類別 */
    Class<? extends EAIConverter> converter();

    /** 被替換的字串 */
    String replaceRegex() default "*";

    /** 替換的字串 */
    String replacement() default "";

    /** 字串長度 */
    int padSize() default 0;

    /** 補位字元 */
    String padChar() default " ";

    /** 欄位XPATH */
    String fieldXPath();
}
