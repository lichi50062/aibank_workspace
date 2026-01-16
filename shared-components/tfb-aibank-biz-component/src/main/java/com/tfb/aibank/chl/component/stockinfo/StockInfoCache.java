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
package com.tfb.aibank.chl.component.stockinfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
 * @(#)StockInfo.java
 * 
 * <p>Description:海外ETF/海外股票資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/29, Jojo Wei
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class StockInfoCache implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品代號
     */
    private String insuranceCode;

    /**
     * 語系
     */
    private String locale;

    /**
     * 限專業投資人 (Y/ )
     */
    private String checkProfessional;

    /**
     * 是否須檢核W8BEN (Y/ )
     */
    private String checkW8ben;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 商品幣別
     */
    private String currencyCode;

    /**
     * 適用客戶身分 Y:OBU N:DBU 9: 全部
     */
    private String dbuObu;

    /**
     * 零股結束時間
     */
    private String divideEndTime;

    /**
     * 零股開始時間
     */
    private String divideStartTime;

    /**
     * 交易所代號
     */
    private String exchangeCode;

    /**
     * 禁止交易註記 B：買 S：賣 A：買賣
     */
    private String forbidFlag;

    /**
     * 約定限價結束時間
     */
    private String fubEndTime;

    /**
     * 約定限價開始時間
     */
    private String fubStartTime;

    /**
     * 商品名稱
     */
    private String insuranceName;

    /**
     * 約定限價買
     */
    private BigDecimal limitPriceBuy;

    /**
     * 約定限價賣
     */
    private BigDecimal limitPriceSell;

    /**
     * 交易市場
     */
    private String market;

    /**
     * 交易市場代碼
     */
    private String marketCode;

    /**
     * 市場交易日期
     */
    private Date marketDate;

    /**
     * 最少交易單位
     */
    private int minAmount;

    /**
     * 最少交易金額
     */
    private int minCost;

    /**
     * 投資標的類型
     */
    private String pdtype1;

    /**
     * 發行公司
     */
    private String productCompany;

    /**
     * 投資地區
     */
    private String productCountry;

    /**
     * 新舊商品 Y：舊 ‘’：新
     */
    private String productKind;

    /**
     * 商品類型 E：海外ETF S：海外股票
     */
    private String productType;

    /**
     * 是否報價註記 (Y/N)
     */
    private String quotationFlag;

    /**
     * 資產風險別P1：微P2：低P3：中P4：高
     */
    private String ramRiskType;

    /**
     * 賣出是否可用市價
     */
    private String sellByMkt;

    /**
     * 存摺簡稱
     */
    private String shortPdName;

    /**
     * 收盤價
     */
    private BigDecimal stockValue;

    /**
     * 收盤價日期
     */
    private Date stockValueDate;

    /**
     * 交易截止時間
     */
    private String tradeDeadline;

    /**
     * 交易開始時間
     */
    private String tradeInitial;

    /**
     * 信託業務別 N：臺幣 其他：外幣
     */
    private String trustType;

    /**
     * 有效小數位數
     */
    private int validDecimal;

    /**
     * 近1天報酬率
     */
    private BigDecimal rtnrate1d;

    /**
     * 近1個月報酬率
     */
    private BigDecimal rtnrate1m;

    /**
     * 近1週報酬率
     */
    private BigDecimal rtnrate1w;

    /**
     * 近3個月報酬率
     */
    private BigDecimal rtnrate3m;
    
    /**
     * 限專區註記
     */
    private String specialBrhCode;
    
    /**
     * 限高資產註記
     */
    private String highAssetCode;

    /** 原幣最低申購金額 */
    private BigDecimal dailyMinAmount;

    /** 原幣定額跳動級距額 */
    private BigDecimal dailySpreadAmount;

    /** 原幣最高投資金額 */
    private BigDecimal dailyHighAmount;

    /** 臺幣最低申購金額 */
    private BigDecimal dailyMinAmountTw;

    /** 臺幣定額跳動級距額 */
    private BigDecimal dailySpreadAmountTw;

    /** 臺幣最高投資金額 */
    private BigDecimal dailyHighAmountTw;

    /** 定期定額商品風險等級 */
    private String dailyProductRisk;

    /** 定額跳動級距額臺幣 */
    private BigDecimal dailyMinSpreadAmountTw;

    /** 是否可做定期定額 */
    private String dailyCost;

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCheckProfessional() {
        return checkProfessional;
    }

    public void setCheckProfessional(String checkProfessional) {
        this.checkProfessional = checkProfessional;
    }

    public String getCheckW8ben() {
        return checkW8ben;
    }

    public void setCheckW8ben(String checkW8ben) {
        this.checkW8ben = checkW8ben;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDbuObu() {
        return dbuObu;
    }

    public void setDbuObu(String dbuObu) {
        this.dbuObu = dbuObu;
    }

    public String getDivideEndTime() {
        return divideEndTime;
    }

    public void setDivideEndTime(String divideEndTime) {
        this.divideEndTime = divideEndTime;
    }

    public String getDivideStartTime() {
        return divideStartTime;
    }

    public void setDivideStartTime(String divideStartTime) {
        this.divideStartTime = divideStartTime;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getForbidFlag() {
        return forbidFlag;
    }

    public void setForbidFlag(String forbidFlag) {
        this.forbidFlag = forbidFlag;
    }

    public String getFubEndTime() {
        return fubEndTime;
    }

    public void setFubEndTime(String fubEndTime) {
        this.fubEndTime = fubEndTime;
    }

    public String getFubStartTime() {
        return fubStartTime;
    }

    public void setFubStartTime(String fubStartTime) {
        this.fubStartTime = fubStartTime;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public BigDecimal getLimitPriceBuy() {
        return limitPriceBuy;
    }

    public void setLimitPriceBuy(BigDecimal limitPriceBuy) {
        this.limitPriceBuy = limitPriceBuy;
    }

    public BigDecimal getLimitPriceSell() {
        return limitPriceSell;
    }

    public void setLimitPriceSell(BigDecimal limitPriceSell) {
        this.limitPriceSell = limitPriceSell;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }

    public String getPdtype1() {
        return pdtype1;
    }

    public void setPdtype1(String pdtype1) {
        this.pdtype1 = pdtype1;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public String getProductCountry() {
        return productCountry;
    }

    public void setProductCountry(String productCountry) {
        this.productCountry = productCountry;
    }

    public String getProductKind() {
        return productKind;
    }

    public void setProductKind(String productKind) {
        this.productKind = productKind;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getQuotationFlag() {
        return quotationFlag;
    }

    public void setQuotationFlag(String quotationFlag) {
        this.quotationFlag = quotationFlag;
    }

    public String getRamRiskType() {
        return ramRiskType;
    }

    public void setRamRiskType(String ramRiskType) {
        this.ramRiskType = ramRiskType;
    }

    public String getSellByMkt() {
        return sellByMkt;
    }

    public void setSellByMkt(String sellByMkt) {
        this.sellByMkt = sellByMkt;
    }

    public String getShortPdName() {
        return shortPdName;
    }

    public void setShortPdName(String shortPdName) {
        this.shortPdName = shortPdName;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    public Date getStockValueDate() {
        return stockValueDate;
    }

    public void setStockValueDate(Date stockValueDate) {
        this.stockValueDate = stockValueDate;
    }

    public String getTradeDeadline() {
        return tradeDeadline;
    }

    public void setTradeDeadline(String tradeDeadline) {
        this.tradeDeadline = tradeDeadline;
    }

    public String getTradeInitial() {
        return tradeInitial;
    }

    public void setTradeInitial(String tradeInitial) {
        this.tradeInitial = tradeInitial;
    }

    public String getTrustType() {
        return trustType;
    }

    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    public int getValidDecimal() {
        return validDecimal;
    }

    public void setValidDecimal(int validDecimal) {
        this.validDecimal = validDecimal;
    }

    public BigDecimal getRtnrate1d() {
        return rtnrate1d;
    }

    public void setRtnrate1d(BigDecimal rtnrate1d) {
        this.rtnrate1d = rtnrate1d;
    }

    public BigDecimal getRtnrate1m() {
        return rtnrate1m;
    }

    public void setRtnrate1m(BigDecimal rtnrate1m) {
        this.rtnrate1m = rtnrate1m;
    }

    public BigDecimal getRtnrate1w() {
        return rtnrate1w;
    }

    public void setRtnrate1w(BigDecimal rtnrate1w) {
        this.rtnrate1w = rtnrate1w;
    }

    public BigDecimal getRtnrate3m() {
        return rtnrate3m;
    }

    public void setRtnrate3m(BigDecimal rtnrate3m) {
        this.rtnrate3m = rtnrate3m;
    }

    public BigDecimal getDailyMinAmount() {
        return dailyMinAmount;
    }

    public void setDailyMinAmount(BigDecimal dailyMinAmount) {
        this.dailyMinAmount = dailyMinAmount;
    }

    public BigDecimal getDailySpreadAmount() {
        return dailySpreadAmount;
    }

    public void setDailySpreadAmount(BigDecimal dailySpreadAmount) {
        this.dailySpreadAmount = dailySpreadAmount;
    }

    public BigDecimal getDailyHighAmount() {
        return dailyHighAmount;
    }

    public void setDailyHighAmount(BigDecimal dailyHighAmount) {
        this.dailyHighAmount = dailyHighAmount;
    }

    public BigDecimal getDailyMinAmountTw() {
        return dailyMinAmountTw;
    }

    public void setDailyMinAmountTw(BigDecimal dailyMinAmountTw) {
        this.dailyMinAmountTw = dailyMinAmountTw;
    }

    public BigDecimal getDailySpreadAmountTw() {
        return dailySpreadAmountTw;
    }

    public void setDailySpreadAmountTw(BigDecimal dailySpreadAmountTw) {
        this.dailySpreadAmountTw = dailySpreadAmountTw;
    }

    public BigDecimal getDailyHighAmountTw() {
        return dailyHighAmountTw;
    }

    public void setDailyHighAmountTw(BigDecimal dailyHighAmountTw) {
        this.dailyHighAmountTw = dailyHighAmountTw;
    }

    public String getDailyProductRisk() {
        return dailyProductRisk;
    }

    public void setDailyProductRisk(String dailyProductRisk) {
        this.dailyProductRisk = dailyProductRisk;
    }

    public BigDecimal getDailyMinSpreadAmountTw() {
        return dailyMinSpreadAmountTw;
    }

    public void setDailyMinSpreadAmountTw(BigDecimal dailyMinSpreadAmountTw) {
        this.dailyMinSpreadAmountTw = dailyMinSpreadAmountTw;
    }

    public String getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(String dailyCost) {
        this.dailyCost = dailyCost;
    }
	public String getSpecialBrhCode() {
		return specialBrhCode;
	}

	public void setSpecialBrhCode(String specialBrhCode) {
		this.specialBrhCode = specialBrhCode;
	}

	public String getHighAssetCode() {
		return highAssetCode;
	}

	public void setHighAssetCode(String highAssetCode) {
		this.highAssetCode = highAssetCode;
	}

}