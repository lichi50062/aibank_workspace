package com.tfb.aibank.chl.component.exchangeratehistory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)ExchangeRateHistoryModel.java
 *
 * <p>Description:[歷史匯率Model]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/9,
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExchangeRateHistory implements Serializable {

    private static final long serialVersionUID = 8307336651528714724L;

    private Integer rn;

    /**
     * 資料鍵值
     */
    private Integer rateKey;

    /**
     * 掛牌時間
     */
    private Date txTime;

    /**
     * 匯率類別，0：台幣對換匯率；1：外幣對換匯率
     */
    private String rateType;

    /**
     * 是否為當日匯率，0：否；1：是
     */
    private String rateFlag;

    /**
     * 幣別名稱1
     */
    private String currencyEname1;

    /**
     * 幣別名稱2
     */
    private String currencyEname2;

    /**
     * 買入
     */
    private BigDecimal buy;

    /**
     * 賣出
     */
    private BigDecimal sell;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    public Integer getRn() {
        return rn;
    }

    public void setRn(Integer rn) {
        this.rn = rn;
    }

    public Integer getRateKey() {
        return rateKey;
    }

    public void setRateKey(Integer rateKey) {
        this.rateKey = rateKey;
    }

    public Date getTxTime() {
        return txTime;
    }

    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getRateFlag() {
        return rateFlag;
    }

    public void setRateFlag(String rateFlag) {
        this.rateFlag = rateFlag;
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

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "ExchangeRateHistoryModel{" +
                "rateKey=" + rateKey +
                ", txTime=" + txTime +
                ", rateType='" + rateType + '\'' +
                ", rateFlag='" + rateFlag + '\'' +
                ", currencyEname1='" + currencyEname1 + '\'' +
                ", currencyEname2='" + currencyEname2 + '\'' +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
