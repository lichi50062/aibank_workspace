/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)N8088N1ResRep.java
 * 
 * <p>Description:N8088N1 海外股票、ETF定期定額總覽明細 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/24, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class N8088N1ResRep implements Serializable {

    private static final long serialVersionUID = 2898093556797487705L;

    /**
     * 格式 0001，0002，0004，0005
     */
    private String category;

    /**
     * 商品類型
     */
    private String productType;

    /**
     * 交易市場
     */
    private String trxMarket;

    /**
     * 商品代號
     */
    private String insuranceNo;

    /**
     * 商品幣別
     */
    private String curCode;

    /**
     * 庫存股數
     */
    private BigDecimal number;

    /**
     * 交易幣別
     */
    private String entrustCur;

    /**
     * 狀態
     */
    private String state;

    /**
     * 平均買進價格
     */
    private BigDecimal avgBuyingPrice;

    /**
     * 參考收盤價
     */
    private BigDecimal curAmt;

    /**
     * 外幣現值 / 外幣參考市值(贖回入帳金額)
     */
    private BigDecimal forCurBal;

    /**
     * 台幣現值 / 台幣市值
     */
    private BigDecimal acctBal;

    /**
     * 收盤價日期
     */
    private Date date08;

    /**
     * 參考匯率
     */
    private BigDecimal referenceRate;

    /**
     * 信託業務別
     */
    private String trustBusinessType;

    /**
     * 信託帳號
     */
    private String trustAcct;

    /**
     * 報酬率正負
     */
    private String returnRateSign;

    /**
     * 報酬率 / 報酬率(不含息)
     */
    private BigDecimal returnRate;

    /**
     * 新舊商品
     */
    private String productType2;

    /**
     * 商品名稱
     */
    private String productName;

    /**
     * 外幣成本
     */
    private BigDecimal forCurCost;

    /**
     * 含息報酬率正負
     */
    private String returnRate2Sign;

    /**
     * 含息報酬率
     */
    private BigDecimal return2Rate;

    /**
     * 台幣成本
     */
    private BigDecimal acctBalCost;

    /**
     * 買賣註記
     */
    private String buyOrSell;

    /**
     * 市場代碼
     */
    private String trxMarketCode;

    /**
     * 信託型態
     */
    private String trustClass;

    /**
     * 停損點正負
     */
    private String stopLossSign;

    /**
     * 停損點
     */
    private BigDecimal stopLossRate;

    /**
     * 停利點正負
     */
    private String stopEarnSign;

    /**
     * 停利點
     */
    private BigDecimal stopEarnRate;

    /**
     * 委託結果
     */
    private String result;

    /**
     * 委託日期
     */
    private Date entrustDate;

    /**
     * 市場交易日期
     */
    private Date tradeDate;

    /**
     * 委託股數
     */
    private BigDecimal entrustAmt;

    /**
     * 委託價格
     */
    private BigDecimal entrustPrice;

    /**
     * 信託業務別
     */
    private String trustType;

    /**
     * 成交股數
     */
    private BigDecimal tradeAmt;

    /**
     * 成交報價
     */
    private BigDecimal tradePrice;

    /**
     * 成交金額
     */
    private BigDecimal tradeCost;

    /**
     * 外幣參考市值(含息)
     */
    private BigDecimal forCurBalDiv;

    /**
     * 台幣市值(含息)
     */
    private BigDecimal acctBalDiv;

    /**
     * 外幣成本
     */
    private BigDecimal acctBalCostCost;

    /**
     * 交易市場日期
     */
    private Date trxMarketDat;

    /**
     * 買賣註記
     */
    private String tradecde;

    /**
     * 截止市場交易日期2
     */
    private Date trxMarketDat2;

    /**
     * 截止市場交易日期3
     */
    private Date trxMarketDat3;

    /**
     * 截止市場交易日期4
     */
    private Date trxMarketDat4;

    /**
     * 截止市場交易日期5
     */
    private Date trxMarketDat5;

    /**
     * 截止市場交易日期6
     */
    private Date trxMarketDat6;

    /** 
     * 累積配息 
     */
    private BigDecimal dividend;

    /** 
     * 累積配息台幣 
     */
    private BigDecimal dividendTwd;
    
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTrxMarket() {
        return trxMarket;
    }

    public void setTrxMarket(String trxMarket) {
        this.trxMarket = trxMarket;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getEntrustCur() {
        return entrustCur;
    }

    public void setEntrustCur(String entrustCur) {
        this.entrustCur = entrustCur;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getAvgBuyingPrice() {
        return avgBuyingPrice;
    }

    public void setAvgBuyingPrice(BigDecimal avgBuyingPrice) {
        this.avgBuyingPrice = avgBuyingPrice;
    }

    public BigDecimal getCurAmt() {
        return curAmt;
    }

    public void setCurAmt(BigDecimal curAmt) {
        this.curAmt = curAmt;
    }

    public BigDecimal getForCurBal() {
        return forCurBal;
    }

    public void setForCurBal(BigDecimal forCurBal) {
        this.forCurBal = forCurBal;
    }

    public BigDecimal getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(BigDecimal acctBal) {
        this.acctBal = acctBal;
    }

    public Date getDate08() {
        return date08;
    }

    public void setDate08(Date date08) {
        this.date08 = date08;
    }

    public BigDecimal getReferenceRate() {
        return referenceRate;
    }

    public void setReferenceRate(BigDecimal referenceRate) {
        this.referenceRate = referenceRate;
    }

    public String getTrustBusinessType() {
        return trustBusinessType;
    }

    public void setTrustBusinessType(String trustBusinessType) {
        this.trustBusinessType = trustBusinessType;
    }

    public String getTrustAcct() {
        return trustAcct;
    }

    public void setTrustAcct(String trustAcct) {
        this.trustAcct = trustAcct;
    }

    public String getReturnRateSign() {
        return returnRateSign;
    }

    public void setReturnRateSign(String returnRateSign) {
        this.returnRateSign = returnRateSign;
    }

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public String getProductType2() {
        return productType2;
    }

    public void setProductType2(String productType2) {
        this.productType2 = productType2;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getForCurCost() {
        return forCurCost;
    }

    public void setForCurCost(BigDecimal forCurCost) {
        this.forCurCost = forCurCost;
    }

    public String getReturnRate2Sign() {
        return returnRate2Sign;
    }

    public void setReturnRate2Sign(String returnRate2Sign) {
        this.returnRate2Sign = returnRate2Sign;
    }

    public BigDecimal getReturn2Rate() {
        return return2Rate;
    }

    public void setReturn2Rate(BigDecimal return2Rate) {
        this.return2Rate = return2Rate;
    }

    public BigDecimal getAcctBalCost() {
        return acctBalCost;
    }

    public void setAcctBalCost(BigDecimal acctBalCost) {
        this.acctBalCost = acctBalCost;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public String getTrxMarketCode() {
        return trxMarketCode;
    }

    public void setTrxMarketCode(String trxMarketCode) {
        this.trxMarketCode = trxMarketCode;
    }

    public String getTrustClass() {
        return trustClass;
    }

    public void setTrustClass(String trustClass) {
        this.trustClass = trustClass;
    }

    public String getStopLossSign() {
        return stopLossSign;
    }

    public void setStopLossSign(String stopLossSign) {
        this.stopLossSign = stopLossSign;
    }

    public BigDecimal getStopLossRate() {
        return stopLossRate;
    }

    public void setStopLossRate(BigDecimal stopLossRate) {
        this.stopLossRate = stopLossRate;
    }

    public String getStopEarnSign() {
        return stopEarnSign;
    }

    public void setStopEarnSign(String stopEarnSign) {
        this.stopEarnSign = stopEarnSign;
    }

    public BigDecimal getStopEarnRate() {
        return stopEarnRate;
    }

    public void setStopEarnRate(BigDecimal stopEarnRate) {
        this.stopEarnRate = stopEarnRate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public BigDecimal getTradeCost() {
        return tradeCost;
    }

    public void setTradeCost(BigDecimal tradeCost) {
        this.tradeCost = tradeCost;
    }

    public BigDecimal getForCurBalDiv() {
        return forCurBalDiv;
    }

    public void setForCurBalDiv(BigDecimal forCurBalDiv) {
        this.forCurBalDiv = forCurBalDiv;
    }

    public BigDecimal getAcctBalDiv() {
        return acctBalDiv;
    }

    public void setAcctBalDiv(BigDecimal acctBalDiv) {
        this.acctBalDiv = acctBalDiv;
    }

    public BigDecimal getAcctBalCostCost() {
        return acctBalCostCost;
    }

    public void setAcctBalCostCost(BigDecimal acctBalCostCost) {
        this.acctBalCostCost = acctBalCostCost;
    }

    public Date getTrxMarketDat() {
        return trxMarketDat;
    }

    public void setTrxMarketDat(Date trxMarketDat) {
        this.trxMarketDat = trxMarketDat;
    }

    public String getTradecde() {
        return tradecde;
    }

    public void setTradecde(String tradecde) {
        this.tradecde = tradecde;
    }

    public Date getTrxMarketDat2() {
        return trxMarketDat2;
    }

    public void setTrxMarketDat2(Date trxMarketDat2) {
        this.trxMarketDat2 = trxMarketDat2;
    }

    public Date getTrxMarketDat3() {
        return trxMarketDat3;
    }

    public void setTrxMarketDat3(Date trxMarketDat3) {
        this.trxMarketDat3 = trxMarketDat3;
    }

    public Date getTrxMarketDat4() {
        return trxMarketDat4;
    }

    public void setTrxMarketDat4(Date trxMarketDat4) {
        this.trxMarketDat4 = trxMarketDat4;
    }

    public Date getTrxMarketDat5() {
        return trxMarketDat5;
    }

    public void setTrxMarketDat5(Date trxMarketDat5) {
        this.trxMarketDat5 = trxMarketDat5;
    }

    public Date getTrxMarketDat6() {
        return trxMarketDat6;
    }

    public void setTrxMarketDat6(Date trxMarketDat6) {
        this.trxMarketDat6 = trxMarketDat6;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getTrustType() {
        return trustType;
    }

    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

	public BigDecimal getDividend() {
		return dividend;
	}

	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}

	public BigDecimal getDividendTwd() {
		return dividendTwd;
	}

	public void setDividendTwd(BigDecimal dividendTwd) {
		this.dividendTwd = dividendTwd;
	}

}
