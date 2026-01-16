package com.tfb.aibank.component.fundcheckdata;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
 * @(#)FundLimitAmount.java
 *
 * <p>Description:FundLimitAmount - 基金申購最低限額</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01,20, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FundLimitAmount implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9127365578747878222L;

    /**
     * 基金幣別代碼
     */
    private String currencyCode;


    /**
     * 定期不定額申購
     */
    private BigDecimal periodNfamtPurchase;

    /**
     * 定額申購
     */
    private BigDecimal periodPurchase;

    /**
     * 單筆申購
     */
    private BigDecimal singlePurchase;

    /**
     * 後收型申購
     */
    private BigDecimal backendPurchase;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * FUND定額申購
     */
    private BigDecimal fundPeriodPurchase;

    /**
     * FUND定期不定額申購
     */
    private BigDecimal fundPeriodNfamtPurchase;

    /**
     * 取得基金幣別代碼
     *
     * @return String 基金幣別代碼
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * 設定基金幣別代碼
     *
     * @param currencyCode 要設定的基金幣別代碼
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * 取得定期不定額申購
     *
     * @return BigDecimal 定期不定額申購
     */
    public BigDecimal getPeriodNfamtPurchase() {
        return this.periodNfamtPurchase;
    }

    /**
     * 設定定期不定額申購
     *
     * @param periodNfamtPurchase 要設定的定期不定額申購
     */
    public void setPeriodNfamtPurchase(BigDecimal periodNfamtPurchase) {
        this.periodNfamtPurchase = periodNfamtPurchase;
    }

    /**
     * 取得定額申購
     *
     * @return BigDecimal 定額申購
     */
    public BigDecimal getPeriodPurchase() {
        return this.periodPurchase;
    }

    /**
     * 設定定額申購
     *
     * @param periodPurchase 要設定的定額申購
     */
    public void setPeriodPurchase(BigDecimal periodPurchase) {
        this.periodPurchase = periodPurchase;
    }

    /**
     * 取得單筆申購
     *
     * @return BigDecimal 單筆申購
     */
    public BigDecimal getSinglePurchase() {
        return this.singlePurchase;
    }

    /**
     * 設定單筆申購
     *
     * @param singlePurchase 要設定的單筆申購
     */
    public void setSinglePurchase(BigDecimal singlePurchase) {
        this.singlePurchase = singlePurchase;
    }

    /**
     * 取得更新時間
     *
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     *
     * @param updateTime 要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getBackendPurchase() {
        return backendPurchase;
    }

    public void setBackendPurchase(BigDecimal backendPurchase) {
        this.backendPurchase = backendPurchase;
    }

    public BigDecimal getFundPeriodNfamtPurchase() {
        return fundPeriodNfamtPurchase;
    }

    public void setFundPeriodNfamtPurchase(BigDecimal fundPeriodNfamtPurchase) {
        this.fundPeriodNfamtPurchase = fundPeriodNfamtPurchase;
    }

    public BigDecimal getFundPeriodPurchase() {
        return fundPeriodPurchase;
    }

    public void setFundPeriodPurchase(BigDecimal fundPeriodPurchase) {
        this.fundPeriodPurchase = fundPeriodPurchase;
    }
}
