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
package com.ibm.tw.commons.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

/**
 * 數字處理工具集
 * <p>
 * 數字工具集主要功能繼承自{@link org.apache.commons.lang3.math.NumberUtils}， 並增加下列功能：
 * <ul>
 * <li>BigDecimal處理功能</li>
 * </ul>
 * </p>
 *
 * @author Jeff Liu
 * @version 1.0, 2004/10/18
 * @see {@link org.apache.commons.lang3.math.NumberUtils}
 * @since
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

    /**
     * 根據有效位數轉換BigDecimal，採用四捨五入
     * 
     * <pre>
     * setScale(BigDecimal(12.24, 1)) = BigDecimal(12.2)
     * setScale(BigDecimal(12.25, 1)) = BigDecimal(12.3)
     * </pre>
     * 
     * @param sAmount
     * @param iScale
     * @return
     */
    public static BigDecimal setScale(BigDecimal value, int iScale) {
        if ((null == value) || (iScale < 0)) {
            return null;
        }
        return setScale(value, iScale, RoundingMode.HALF_UP);
    }

    /**
     * 根據有效位數轉換BigDecimal，採用指定的進位方式
     * 
     * @param value
     * @param iScale
     * @param roundingMode
     * @return
     */
    public static BigDecimal setScale(BigDecimal value, int iScale, RoundingMode roundingMode) {
        if ((null == value) || (iScale < 0)) {
            return null;
        }
        return value.setScale(iScale, roundingMode);
    }

    /**
     * 根據有效位數轉換BigDecimal，採用指定的進位方式
     * 
     * @param value
     * @param iScale:為正值，往右進位、為負值，往左進位
     * @param roundingMode
     * @return
     */
    public static BigDecimal setScaleByPowerOfTen(BigDecimal value, int iScale) {
        if ((null == value)) {
            return null;
        }
        return value.scaleByPowerOfTen(iScale);
    }

    /**
     * 加總
     */
    public static BigDecimal add(BigDecimal... addends) {
        if (addends == null) {
            return BigDecimal.ZERO;
        }
        return Arrays.stream(addends).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * <p>
     * Returns either the passed in BigDecimal, or if the BigDecimal is {@code null}, the value of {@code defaultValue}.
     * </p>
     * 
     * @param value
     * @param defaultValue
     * @return
     */
    public static <T extends Number> T defaultValue(final T value, final T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
