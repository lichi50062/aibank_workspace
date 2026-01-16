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
import java.text.NumberFormat;

/**
 * 處理數學運算
 *
 * @author Edward Tien
 */
public class ArithUtils {
    // 默認除法運算精度
    private static final int DEF_DIV_SCALE = 10;

    // 這個類不能實例化
    private ArithUtils() {
    }

    /**
     * 提供精確的加法運算。
     *
     * @param v1
     *            加數
     * @param v2
     *            被加數
     * @return 兩個參數的和
     */
    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    /**
     * 提供精確的加法運算。
     *
     * @param v1
     *            加數
     * @param v2
     *            被加數
     * @return 兩個參數的和
     */
    public static double add(double v1, double v2) {
        return add(String.valueOf(v1), String.valueOf(v2)).doubleValue();
    }

    /**
     * 提供精確的減法運算。
     *
     * @param v1
     *            減數
     * @param v2
     *            被減數
     * @return 兩個參數的差
     */
    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }

    /**
     * 提供精確的減法運算。
     *
     * @param v1
     *            減數
     * @param v2
     *            被減數
     * @return 兩個參數的差
     */
    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return sub(b1, b2);
    }

    /**
     * 提供精確的減法運算。
     *
     * @param v1
     *            減數
     * @param v2
     *            被減數
     * @return 兩個參數的差
     */
    public static double sub(double v1, double v2) {
        return sub(String.valueOf(v1), String.valueOf(v2)).doubleValue();
    }

    /**
     * 提供精確的乘法運算。
     *
     * @param v1
     *            被乘數
     * @param v2
     *            乘數
     * @return 兩個參數的積
     */
    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * 提供精確的乘法運算。
     *
     * @param v1
     *            被乘數
     * @param v2
     *            乘數
     * @return 兩個參數的積
     */
    public static double mul(double v1, double v2) {
        return mul(String.valueOf(v1), String.valueOf(v2)).doubleValue();
    }

    /**
     * 提供（相對）精確的除法運算，當發生除不盡的情況時，精確到 小數點以後10位，以後的數字四捨五入。
     *
     * @param v1
     *            被除數
     * @param v2
     *            除數
     * @return 兩個參數的商
     */
    public static BigDecimal div(String v1, String v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相對）精確的除法運算。當發生除不盡的情況時，由scale參數指 定精度，以後的數字四捨五入。
     *
     * @param v1
     *            被除數
     * @param v2
     *            除數
     * @param scale
     *            表示表示需要精確到小數點以後幾位。
     * @return 兩個參數的商
     */
    public static BigDecimal div(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供（相對）精確的除法運算。當發生除不盡的情況時，由scale參數指 定精度，由round參數指 定進位方式。
     *
     * @param v1
     * @param v2
     * @param scale
     * @param round
     * @return
     */
    public static BigDecimal div(String v1, String v2, int scale, RoundingMode round) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, round);
    }

    /**
     * 提供（相對）精確的除法運算。當發生除不盡的情況時，由scale參數指 定精度，由round參數指 定進位方式。
     *
     * @param v1
     * @param v2
     * @param scale
     * @param round
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale, RoundingMode round) {
        if (v2 == null || v1 == null) {
            return BigDecimal.ZERO;
        }

        if (v2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return v1.divide(v2, scale, round);
    }

    /**
     * 提供（相對）精確的除法運算，當發生除不盡的情況時，精確到 小數點以後10位，以後的數字四捨五入。
     *
     * @param v1
     *            被除數
     * @param v2
     *            除數
     * @return 兩個參數的商
     */
    public static double div(double v1, double v2) {
        return div(String.valueOf(v1), String.valueOf(v2), DEF_DIV_SCALE).doubleValue();
    }

    /**
     * 提供（相對）精確的除法運算。當發生除不盡的情況時，由scale參數指 定精度，以後的數字四捨五入。
     *
     * @param v1
     *            被除數
     * @param v2
     *            除數
     * @param scale
     *            表示表示需要精確到小數點以後幾位。
     * @return 兩個參數的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return div(String.valueOf(v1), String.valueOf(v2), scale).doubleValue();
    }

    /**
     *
     * @param v1
     *            被除數
     * @param v2
     *            除數
     * @param scale
     *            表示表示需要精確到小數點以後幾位。
     * @param round
     *            決定四捨五入或無條件進位
     * @return 兩個參數的商
     */
    public static double div(double v1, double v2, int scale, RoundingMode round) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return div(String.valueOf(v1), String.valueOf(v2), scale, round).doubleValue();
    }

    /**
     * 提供精確的小數位四捨五入處理。
     *
     * @param v
     *            需要四捨五入的數字
     * @param scale
     *            小數點後保留幾位
     * @return 四捨五入後的結果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(String.valueOf(v));
        return b.divide(BigDecimal.ONE, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精確的小數位無條件捨去處理。
     *
     * @param v
     *            需要無條件捨去的數字
     * @param scale
     *            小數點後保留幾位
     * @return 無條件捨去後的結果
     */
    public static double floor(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(String.valueOf(v));
        return b.divide(BigDecimal.ONE, scale, RoundingMode.FLOOR).doubleValue();
    }

    /**
     * 計算百分比，並格式化成兩位小數。
     *
     * @param v1
     *            要四舍五入的數字
     * @return 計算百分比後的结果
     */
    public static String percent(BigDecimal v1) {
        if (v1 == null) {
            return "0.00%";
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(v1);
    }

    /**
     * 計算百分比
     *
     * @param count
     *            分子
     * @param total
     *            分母
     * @return 返回計算所得百分數
     */
    public static String getPercent(BigDecimal count, BigDecimal total) {
        if (count == null || total == null || total.equals(BigDecimal.ZERO)) {
            return "0.00%";
        }
        BigDecimal f = count.divide(total, 4, RoundingMode.HALF_UP);
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(f);
    }

    /**
     * 取餘數
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal remainder(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2);
    }

    /**
     * 取餘數
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double remainder(double v1, double v2) {
        return remainder(String.valueOf(v1), String.valueOf(v2)).doubleValue();
    }

}
