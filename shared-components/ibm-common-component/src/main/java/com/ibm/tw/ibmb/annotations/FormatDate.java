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
package com.ibm.tw.ibmb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 將 java.util.Date，轉型為YYYY/MM/DD日期格式的字串
 * 
 * @author Edward Tien
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormatDate {

    /**
     * 格式化的樣式
     * 
     * @return
     */
    String pattern() default "yyyy/MM/dd";
}
