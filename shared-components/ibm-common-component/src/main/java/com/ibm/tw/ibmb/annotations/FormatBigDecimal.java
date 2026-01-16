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
import java.math.RoundingMode;

/**
 * 將java.math.BigDecimal轉型為符合數字格式的字串
 * 
 * @author Edward Tien
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormatBigDecimal {
    /** 是否有千分位 */
    boolean thousand() default true;

    /** 指定小數幾位，若無則預設原本位數 */
    int scale() default -1;

    /** 若有指定小數位，指定進位原則，預設為四捨五入 */
    RoundingMode roundingMode() default RoundingMode.HALF_UP;
}
