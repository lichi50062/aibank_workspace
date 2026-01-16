package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)ExchangeRateHistoryEntity.java
 * 
 * <p>Description:歷史匯率檔 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "EXCHANGE_RATE_HISTORY")
public class ExchangeRateHistoryEntity implements Serializable {

    private static final long serialVersionUID = 8307336651528714724L;

    /**
     * 資料鍵值
     */
    @Id
    @Column(name = "RATE_KEY")
    private Integer rateKey;

    /**
     * 掛牌時間
     */
    @Basic
    @Column(name = "TX_TIME")
    private Date txTime;

    /**
     * 匯率類別，0：台幣對換匯率；1：外幣對換匯率
     */
    @Basic
    @Column(name = "RATE_TYPE")
    private String rateType;

    /**
     * 是否為當日匯率，0：否；1：是
     */
    @Basic
    @Column(name = "RATE_FLAG")
    private String rateFlag;

    /**
     * 幣別名稱1
     */
    @Basic
    @Column(name = "CURRENCY_ENAME_1")
    private String currencyEname1;

    /**
     * 幣別名稱2
     */
    @Basic
    @Column(name = "CURRENCY_ENAME_2")
    private String currencyEname2;

    /**
     * 買入
     */
    @Basic
    @Column(name = "BUY")
    private BigDecimal buy;

    /**
     * 賣出
     */
    @Basic
    @Column(name = "SELL")
    private BigDecimal sell;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

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

}
