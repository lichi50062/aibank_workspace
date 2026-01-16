/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NmiOverview.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/13, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NmiOverview {

    public NmiOverview() {
        // default constructor
    }

    /** 契約編號 */
    private String potId;

    /** 計畫名稱 */
    private String planName;

    /** 目標金額 */
    private BigDecimal targetAmt;

    /** 累計投資金額(原幣) */
    private BigDecimal increaseAmtBas;

    /** 累計應收管理費 */
    private BigDecimal increaseCharge;

    /** 參考市值(原幣) */
    private BigDecimal marketValBas;

    /** 參考損益正負號(原幣) */
    private String signDigitBas;

    /** 參考損益(原幣) */
    private BigDecimal profitAndLossBas;

    /** 投資報酬率%(原幣) */
    private BigDecimal retrunBas;

    /** 目標達成率% */
    private BigDecimal targetRate;

    /** 適用優惠代碼 */
    private String couponCode;

    /** 累計投資金額(台幣) */
    private Integer increaseAmtTwd;

    /** 參考市值(台幣) */
    private Integer marketValTwd;

    /** 參考損益正負號(台幣) */
    private String signDigitTwd;

    /** 參考損益(台幣) */
    private Integer profitAndLossTwd;

    /** 投資報酬率%(台幣) */
    private Integer retrunTwd;

    /** 信託結帳日(民國) */
    private String trustClosingDate;

    /** 自動換匯(Y/N) */
    private String chgPurchase;

    /** 配息運用方式 */
    private String divType;

    /** 累計配息金額 */
    private BigDecimal dividendamount;

    /** 含息報酬率正負號 */
    private String signcod;

    /** 含息報酬率 */
    private BigDecimal interestrateofreturn;

    /**
     * @return the potId
     */
    public String getPotId() {
        return potId;
    }

    /**
     * @param potId
     *            the potId to set
     */
    public void setPotId(String potId) {
        this.potId = potId;
    }

    /**
     * @return the planName
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * @param planName
     *            the planName to set
     */
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    /**
     * @return the targetAmt
     */
    public BigDecimal getTargetAmt() {
        return targetAmt;
    }

    /**
     * @param targetAmt
     *            the targetAmt to set
     */
    public void setTargetAmt(BigDecimal targetAmt) {
        this.targetAmt = targetAmt;
    }

    /**
     * @return the increaseAmtBas
     */
    public BigDecimal getIncreaseAmtBas() {
        return increaseAmtBas;
    }

    /**
     * @param increaseAmtBas
     *            the increaseAmtBas to set
     */
    public void setIncreaseAmtBas(BigDecimal increaseAmtBas) {
        this.increaseAmtBas = increaseAmtBas;
    }

    /**
     * @return the increaseCharge
     */
    public BigDecimal getIncreaseCharge() {
        return increaseCharge;
    }

    /**
     * @param increaseCharge
     *            the increaseCharge to set
     */
    public void setIncreaseCharge(BigDecimal increaseCharge) {
        this.increaseCharge = increaseCharge;
    }

    /**
     * @return the marketValBas
     */
    public BigDecimal getMarketValBas() {
        return marketValBas;
    }

    /**
     * @param marketValBas
     *            the marketValBas to set
     */
    public void setMarketValBas(BigDecimal marketValBas) {
        this.marketValBas = marketValBas;
    }

    /**
     * @return the signDigitBas
     */
    public String getSignDigitBas() {
        return signDigitBas;
    }

    /**
     * @param signDigitBas
     *            the signDigitBas to set
     */
    public void setSignDigitBas(String signDigitBas) {
        this.signDigitBas = signDigitBas;
    }

    /**
     * @return the profitAndLossBas
     */
    public BigDecimal getProfitAndLossBas() {
        return profitAndLossBas;
    }

    /**
     * @param profitAndLossBas
     *            the profitAndLossBas to set
     */
    public void setProfitAndLossBas(BigDecimal profitAndLossBas) {
        this.profitAndLossBas = profitAndLossBas;
    }

    /**
     * @return the retrunBas
     */
    public BigDecimal getRetrunBas() {
        return retrunBas;
    }

    /**
     * @param retrunBas
     *            the retrunBas to set
     */
    public void setRetrunBas(BigDecimal retrunBas) {
        this.retrunBas = retrunBas;
    }

    /**
     * @return the targetRate
     */
    public BigDecimal getTargetRate() {
        return targetRate;
    }

    /**
     * @param targetRate
     *            the targetRate to set
     */
    public void setTargetRate(BigDecimal targetRate) {
        this.targetRate = targetRate;
    }

    /**
     * @return the couponCode
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * @param couponCode
     *            the couponCode to set
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * @return the increaseAmtTwd
     */
    public Integer getIncreaseAmtTwd() {
        return increaseAmtTwd;
    }

    /**
     * @param increaseAmtTwd
     *            the increaseAmtTwd to set
     */
    public void setIncreaseAmtTwd(Integer increaseAmtTwd) {
        this.increaseAmtTwd = increaseAmtTwd;
    }

    /**
     * @return the marketValTwd
     */
    public Integer getMarketValTwd() {
        return marketValTwd;
    }

    /**
     * @param marketValTwd
     *            the marketValTwd to set
     */
    public void setMarketValTwd(Integer marketValTwd) {
        this.marketValTwd = marketValTwd;
    }

    /**
     * @return the signDigitTwd
     */
    public String getSignDigitTwd() {
        return signDigitTwd;
    }

    /**
     * @param signDigitTwd
     *            the signDigitTwd to set
     */
    public void setSignDigitTwd(String signDigitTwd) {
        this.signDigitTwd = signDigitTwd;
    }

    /**
     * @return the profitAndLossTwd
     */
    public Integer getProfitAndLossTwd() {
        return profitAndLossTwd;
    }

    /**
     * @param profitAndLossTwd
     *            the profitAndLossTwd to set
     */
    public void setProfitAndLossTwd(Integer profitAndLossTwd) {
        this.profitAndLossTwd = profitAndLossTwd;
    }

    /**
     * @return the retrunTwd
     */
    public Integer getRetrunTwd() {
        return retrunTwd;
    }

    /**
     * @param retrunTwd
     *            the retrunTwd to set
     */
    public void setRetrunTwd(Integer retrunTwd) {
        this.retrunTwd = retrunTwd;
    }

    /**
     * @return the trustClosingDate
     */
    public String getTrustClosingDate() {
        return trustClosingDate;
    }

    /**
     * @param trustClosingDate
     *            the trustClosingDate to set
     */
    public void setTrustClosingDate(String trustClosingDate) {
        this.trustClosingDate = trustClosingDate;
    }

    /**
     * @return the chgPurchase
     */
    public String getChgPurchase() {
        return chgPurchase;
    }

    /**
     * @param chgPurchase
     *            the chgPurchase to set
     */
    public void setChgPurchase(String chgPurchase) {
        this.chgPurchase = chgPurchase;
    }

    /**
     * @return the divType
     */
    public String getDivType() {
        return divType;
    }

    /**
     * @param divType
     *            the divType to set
     */
    public void setDivType(String divType) {
        this.divType = divType;
    }

    /**
     * @return the dividendamount
     */
    public BigDecimal getDividendamount() {
        return dividendamount;
    }

    /**
     * @param dividendamount
     *            the dividendamount to set
     */
    public void setDividendamount(BigDecimal dividendamount) {
        this.dividendamount = dividendamount;
    }

    /**
     * @return the signcod
     */
    public String getSigncod() {
        return signcod;
    }

    /**
     * @param signcod
     *            the signcod to set
     */
    public void setSigncod(String signcod) {
        this.signcod = signcod;
    }

    /**
     * @return the interestrateofreturn
     */
    public BigDecimal getInterestrateofreturn() {
        return interestrateofreturn;
    }

    /**
     * @param interestrateofreturn
     *            the interestrateofreturn to set
     */
    public void setInterestrateofreturn(BigDecimal interestrateofreturn) {
        this.interestrateofreturn = interestrateofreturn;
    }

}
