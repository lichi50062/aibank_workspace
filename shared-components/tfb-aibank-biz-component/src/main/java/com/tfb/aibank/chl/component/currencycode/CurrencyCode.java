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
package com.tfb.aibank.chl.component.currencycode;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CurrencyCode.java
 * 
 * <p>Description:幣別代碼檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "幣別代碼檔")
public class CurrencyCode implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TWD = "TWD";
    public static final String USD = "USD";
    public static final String JPY = "JPY";
    public static final String CNY = "CNY";
    public static final String HKD = "HKD";

    /**
     * 幣別檔編號
     */
    @Schema(description = "幣別檔編號")
    private String currencyCode;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 幣別名稱
     */
    @Schema(description = "幣別名稱")
    private String currencyName;

    /**
     * 幣別代碼1
     */
    @Schema(description = "幣別代碼1")
    private String currencyEname1;

    /**
     * 幣別代碼2
     */
    @Schema(description = "幣別代碼2")
    private String currencyEname2;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyEname1() {
        return currencyEname1;
    }

    public void setCurrencyEname1(String currencyEname1) {
        this.currencyEname1 = currencyEname1;
    }

    public String getCurrencyEname2() {
        return currencyEname2;
    }

    public void setCurrencyEname2(String currencyEname2) {
        this.currencyEname2 = currencyEname2;
    }

}
