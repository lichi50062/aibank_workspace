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
package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)EtfOverview.java
 * 
 * <p>海外ETF(NR061N)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/21, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EtfOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 商品代號. */
    private String insuranceNo;

    /** 信託帳號. */
    private String trustAcct;

    /** 商品名稱. */
    private String insuranceName;

    /** 交易幣別 */
    private String curName;

    /** 報酬率正負. */
    private String returnRateSign;

    /** 報酬率. */
    private BigDecimal returnRate;

    /** 停損點正負. */
    private String stopLossSign;

    /** 停損點. */
    private BigDecimal stopLossRate;

    /** 停利點正負. */
    private String stopEarnSign;

    /** 停利點. */
    private BigDecimal stopEarnRate;

    /** 信託業務別. */
    private String trustKind;

    /** 單筆/定期定額. */
    private String singleType;

    /** 投資成本. */
    private BigDecimal balCost;

    /**
     * @return {@link #insuranceNo}
     */
    public String getInsuranceNo() {
        return insuranceNo;
    }

    /**
     * @param insuranceNo
     *            {@link #insuranceNo}
     */
    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    /**
     * @return {@link #trustAcct}
     */
    public String getTrustAcct() {
        return trustAcct;
    }

    /**
     * @param trustAcct
     *            {@link #trustAcct}
     */
    public void setTrustAcct(String trustAcct) {
        this.trustAcct = trustAcct;
    }

    /**
     * @return {@link #insuranceName}
     */
    public String getInsuranceName() {
        return insuranceName;
    }

    /**
     * @param insuranceName
     *            {@link #insuranceName}
     */
    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    /**
     * @return the curName
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName
     *            the curName to set
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }

    /**
     * @return {@link #returnRateSign}
     */
    public String getReturnRateSign() {
        return returnRateSign;
    }

    /**
     * @param returnRateSign
     *            {@link #returnRateSign}
     */
    public void setReturnRateSign(String returnRateSign) {
        this.returnRateSign = returnRateSign;
    }

    /**
     * @return {@link #returnRate}
     */
    public BigDecimal getReturnRate() {
        return returnRate;
    }

    /**
     * @param returnRate
     *            {@link #returnRate}
     */
    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    /**
     * @return {@link #stopLossSign}
     */
    public String getStopLossSign() {
        return stopLossSign;
    }

    /**
     * @param stopLossSign
     *            {@link #stopLossSign}
     */
    public void setStopLossSign(String stopLossSign) {
        this.stopLossSign = stopLossSign;
    }

    /**
     * @return {@link #stopLossRate}
     */
    public BigDecimal getStopLossRate() {
        return stopLossRate;
    }

    /**
     * @param stopLossRate
     *            {@link #stopLossRate}
     */
    public void setStopLossRate(BigDecimal stopLossRate) {
        this.stopLossRate = stopLossRate;
    }

    /**
     * @return {@link #stopEarnSign}
     */
    public String getStopEarnSign() {
        return stopEarnSign;
    }

    /**
     * @param stopEarnSign
     *            {@link #stopEarnSign}
     */
    public void setStopEarnSign(String stopEarnSign) {
        this.stopEarnSign = stopEarnSign;
    }

    /**
     * @return {@link #stopEarnRate}
     */
    public BigDecimal getStopEarnRate() {
        return stopEarnRate;
    }

    /**
     * @param stopEarnRate
     *            {@link #stopEarnRate}
     */
    public void setStopEarnRate(BigDecimal stopEarnRate) {
        this.stopEarnRate = stopEarnRate;
    }

    /**
     * @return {@link #balCost}
     */
    public BigDecimal getBalCost() {
        return balCost;
    }

    /**
     * @param balCost
     *            {@link #balCost}
     */
    public void setBalCost(BigDecimal balCost) {
        this.balCost = balCost;
    }

    /**
     * @return {@link #trustKind}
     */
    public String getTrustKind() {
        return trustKind;
    }

    /**
     * @param trustKind
     *            {@link #trustKind}
     */
    public void setTrustKind(String trustKind) {
        this.trustKind = trustKind;
    }

    /**
     * @return {@link #singleType}
     */
    public String getSingleType() {
        return singleType;
    }

    /**
     * @param singleType
     *            {@link #singleType}
     */
    public void setSingleType(String singleType) {
        this.singleType = singleType;
    }

}
