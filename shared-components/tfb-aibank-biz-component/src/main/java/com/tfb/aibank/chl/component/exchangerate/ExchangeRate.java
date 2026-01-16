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
package com.tfb.aibank.chl.component.exchangerate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExchangeRate.java
 * 
 * <p>Description:匯率檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "匯率檔")
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "資料鍵值")
    private Long rateKey;

    @Schema(description = "掛牌時間")
    private Date txTime;

    @Schema(description = "匯率類別，0：台幣對換匯率；1：外幣對換匯率")
    private String rateType;

    @Schema(description = "幣別代碼1")
    private String currencyEname1;

    @Schema(description = "幣別代碼2")
    private String currencyEname2;

    //@formatter:off
    /**
     * 台幣對外幣匯率
     * 0：即期
     * 10：10天期
     * 30：30天期
     * 60：60天期
     * 90：90天期
     * 120：120天期
     * 180：180天期
     * 500：現鈔<hr>
     * 外幣對外幣匯率
     * 0：即期<hr>
     * 保管銀行牌告匯率
     * 0：即期
     * 1：現鈔
     * 2：大額
     */
    //@formatter:on
    @Schema(description = "外匯類別編號")
    private String exchangeTypeNo;

    @Schema(description = "買入金額")
    private BigDecimal buy;

    @Schema(description = "賣出金額")
    private BigDecimal sell;

    @Schema(description = "小額買入成本")
    private BigDecimal smallBuyCost;

    @Schema(description = "小額賣出成本")
    private BigDecimal smallSellCost;

    @Schema(description = "大額買入成本")
    private BigDecimal largeBuyCost;

    @Schema(description = "大額賣出成本")
    private BigDecimal largeSellCost;

    @Schema(description = "順序")
    private Integer currencySort;

    @Schema(description = "建立時間")
    private Date createTime;

    @Schema(description = "對臺幣匯率")
    private BigDecimal toTwd;

    @Schema(description = "對美元匯率")
    private BigDecimal toUsd;

    /**
     * 有優惠匯率
     */
    private boolean withPreferentialRate;

    private BigDecimal sellOriginal;

    private BigDecimal sellAfterDiscount;
    /**
     * 優惠匯率讓分
     */
    private BigDecimal discount;

    /**
     * 資料鍵值
     */
    public Long getRateKey() {
        return rateKey;
    }

    /**
     * 資料鍵值
     */
    public void setRateKey(Long rateKey) {
        this.rateKey = rateKey;
    }

    /**
     * 掛牌時間
     */
    public Date getTxTime() {
        return txTime;
    }

    /**
     * 掛牌時間
     */
    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }

    /**
     * 匯率類別，0：台幣對換匯率；1：外幣對換匯率
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 匯率類別，0：台幣對換匯率；1：外幣對換匯率
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * 幣別代碼1
     */
    public String getCurrencyEname1() {
        return currencyEname1;
    }

    /**
     * 幣別代碼1
     */
    public void setCurrencyEname1(String currencyEname1) {
        this.currencyEname1 = currencyEname1;
    }

    /**
     * 幣別代碼2
     */
    public String getCurrencyEname2() {
        return currencyEname2;
    }

    /**
     * 幣別代碼2
     */
    public void setCurrencyEname2(String currencyEname2) {
        this.currencyEname2 = currencyEname2;
    }

    /**
     * 外匯類別編號
     */
    public String getExchangeTypeNo() {
        return exchangeTypeNo;
    }

    /**
     * 外匯類別編號
     */
    public void setExchangeTypeNo(String exchangeTypeNo) {
        this.exchangeTypeNo = exchangeTypeNo;
    }

    /**
     * 買入金額
     */
    public BigDecimal getBuy() {
        return buy;
    }

    /**
     * 買入金額
     */
    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    /**
     * 賣出金額
     */
    public BigDecimal getSell() {
        return sell;
    }

    /**
     * 賣出金額
     */
    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    /**
     * 小額買入成本
     */
    public BigDecimal getSmallBuyCost() {
        return smallBuyCost;
    }

    /**
     * 小額買入成本
     */
    public void setSmallBuyCost(BigDecimal smallBuyCost) {
        this.smallBuyCost = smallBuyCost;
    }

    /**
     * 小額賣出成本
     */
    public BigDecimal getSmallSellCost() {
        return smallSellCost;
    }

    /**
     * 小額賣出成本
     */
    public void setSmallSellCost(BigDecimal smallSellCost) {
        this.smallSellCost = smallSellCost;
    }

    /**
     * 大額買入成本
     */
    public BigDecimal getLargeBuyCost() {
        return largeBuyCost;
    }

    /**
     * 大額買入成本
     */
    public void setLargeBuyCost(BigDecimal largeBuyCost) {
        this.largeBuyCost = largeBuyCost;
    }

    /**
     * 大額賣出成本
     */
    public BigDecimal getLargeSellCost() {
        return largeSellCost;
    }

    /**
     * 大額賣出成本
     */
    public void setLargeSellCost(BigDecimal largeSellCost) {
        this.largeSellCost = largeSellCost;
    }

    /**
     * 順序
     */
    public Integer getCurrencySort() {
        return currencySort;
    }

    /**
     * 順序
     */
    public void setCurrencySort(Integer currencySort) {
        this.currencySort = currencySort;
    }

    /**
     * 建立時間
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 對臺幣匯率
     */
    public BigDecimal getToTwd() {
        return toTwd;
    }

    /**
     * 對臺幣匯率
     */
    public void setToTwd(BigDecimal toTwd) {
        this.toTwd = toTwd;
    }

    /**
     * 對美元匯率
     */
    public BigDecimal getToUsd() {
        return toUsd;
    }

    /**
     * 對美元匯率
     */
    public void setToUsd(BigDecimal toUsd) {
        this.toUsd = toUsd;
    }

    public boolean isWithPreferentialRate() {
        return withPreferentialRate;
    }

    public void setWithPreferentialRate(boolean withPreferentialRate) {
        this.withPreferentialRate = withPreferentialRate;
    }

    public BigDecimal getSellAfterDiscount() {
        return sellAfterDiscount;
    }

    public void setSellAfterDiscount(BigDecimal sellAfterDiscount) {
        this.sellAfterDiscount = sellAfterDiscount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getSellOriginal() {
        return sellOriginal;
    }

    public void setSellOriginal(BigDecimal sellOriginal) {
        this.sellOriginal = sellOriginal;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" + "rateKey=" + rateKey + '\'' + "txTime=" + txTime + '\'' + "rateType=" + rateType + '\'' + "currencyEname1=" + currencyEname1 + '\'' + "currencyEname2=" + currencyEname2 + '\'' + "exchangeTypeNo=" + exchangeTypeNo + '\'' + "buy=" + buy + '\'' + "sell=" + sell + '\'' + "smallBuyCost=" + smallBuyCost + '\'' + "smallSellCost=" + smallSellCost + '\'' + "largeBuyCost=" + largeBuyCost + '\'' + "largeSellCost=" + largeSellCost + '\'' + "currencySort=" + currencySort + '\'' + "createTime=" + createTime + '\'' + "toTwd=" + toTwd + '\'' + "toUsd=" + toUsd + '\'' + '}';
    }

}
