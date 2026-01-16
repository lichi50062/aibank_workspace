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
package com.tfb.aibank.chl.system.ot011.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NDSAG004Currency.java
 * 
 * <p>Description:幣別物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT011Currency {

    public NSTOT011Currency(String curCode, String curName, BigDecimal rate, Integer sort) {
        this.curCode = curCode;
        this.curName = curName;
        this.rate = rate;
        this.sort = sort;
    }

    /** 幣別代碼 */
    private String curCode;
    /** 幣別名稱 */
    private String curName;
    /** 匯率 */
    private BigDecimal rate;
    /** 排序編號 */
    private Integer sort;

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
