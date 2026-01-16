package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)StockOverview.java
 * 
 * <p>Description:股票、ETF總覽，整合資訊使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class StockOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    public StockOverview() {
        // default constructor
    }

    public StockOverview(String category) {
        this.category = category;
    }

    /** 交易類型 */
    @Schema(description = "交易類型")
    private String category;

    /** 商品類型 */
    @Schema(description = "商品類型")
    private String productType;
    /** 交易市場 */
    @Schema(description = "交易市場")
    private String trxMarket;
    /** 商品代號 */
    @Schema(description = "商品代號")
    private String insuranceNo;
    /** 商品幣別 */
    @Schema(description = "商品幣別")
    private String curCode;
    /** 庫存股數 */
    @Schema(description = "庫存股數")
    private BigDecimal number;
    /** 狀態 */
    @Schema(description = "狀態")
    private String state;
    /** 交易幣別 */
    @Schema(description = "交易幣別")
    private String entrustCur;
    /** 平均買進價格 */
    @Schema(description = "平均買進價格")
    private BigDecimal avgBuyingPrice;
    /** 參考收盤價 */
    @Schema(description = "參考收盤價")
    private BigDecimal curAmt;
    /** 外幣現值 */
    @Schema(description = "外幣現值")
    private BigDecimal forCurBal;
    /** 台幣現值 */
    @Schema(description = "台幣現值")
    private BigDecimal acctBal;
    /** 收盤價日期 */
    @Schema(description = "收盤價日期")
    private Date date08;
    /** 參考匯率 */
    @Schema(description = "參考匯率")
    private BigDecimal referenceRate;
    /** 信託業務別 */
    @Schema(description = "信託業務別")
    private String trustBusinessType;
    /** 信託帳號 */
    @Schema(description = "信託帳號")
    private String trustAcct;
    /** 報酬率正負 */
    @Schema(description = "報酬率正負")
    private String returnRateSign;
    /** 報酬率 */
    @Schema(description = "報酬率")
    private BigDecimal returnRate;
    /** 新舊商品 */
    @Schema(description = "新舊商品")
    private String productType2;
    /** 商品名稱 */
    @Schema(description = "商品名稱")
    private String productName;
    /** 外幣成本 */
    @Schema(description = "外幣成本")
    private BigDecimal forCurCost;
    /** 台幣成本 */
    @Schema(description = "台幣成本")
    private BigDecimal acctBalCost;
    /** 買賣註記 */
    @Schema(description = "買賣註記")
    private String buyOrSell;
    /** 收益入帳帳號 */
    @Schema(description = "收益入帳帳號")
    private String creditAcct;
    /** 市場代碼 */
    @Schema(description = "市場代碼")
    private String trxMarketCode;
    /** 委託結果 */
    @Schema(description = "委託結果")
    private String result;
    /** 委託日期 */
    @Schema(description = "委託日期")
    private Date entrustDate;
    /** 市場交易日期 */
    @Schema(description = "市場交易日期")
    private Date tradeDate;
    /** 委託股數 */
    @Schema(description = "委託股數")
    private BigDecimal entrustAmt;
    /** 委託價格 */
    @Schema(description = "委託價格")
    private BigDecimal entrustPrice;
    /** 通路別 */
    @Schema(description = "通路別")
    private String channelType;
    /** 信託業務別 */
    @Schema(description = "信託業務別")
    private String trustType;
    /** 成交股數 */
    @Schema(description = "成交股數")
    private BigDecimal tradeAmt;
    /** 成交報價 */
    @Schema(description = "成交報價")
    private BigDecimal tradePrice;
    /** 成交金額 */
    @Schema(description = "成交金額")
    private BigDecimal tradeCost;
    /** 截止市場交易日期 */
    @Schema(description = "截止市場交易日期")
    private Date tradeDateEnd;
    /** 委託方式 */
    @Schema(description = "委託方式")
    private String orderType;
    /** 交易市場日期 */
    @Schema(description = "交易市場日期")
    private Date trxMarketDat;
    /** 買賣註記 */
    @Schema(description = "買賣註記")
    private String tradecde;
    /** 截止市場交易日期2 */
    @Schema(description = "截止市場交易日期2")
    private Date trxMarketDat2;
    /** 截止市場交易日期3 */
    @Schema(description = "截止市場交易日期3")
    private Date trxMarketDat3;
    /** 截止市場交易日期4 */
    @Schema(description = "截止市場交易日期4")
    private Date trxMarketDat4;
    /** 截止市場交易日期5 */
    @Schema(description = "截止市場交易日期5")
    private Date trxMarketDat5;
    /** 截止市場交易日期6 */
    @Schema(description = "截止市場交易日期6")
    private Date trxMarketDat6;
    /** 截止市場交易日期7 */
    @Schema(description = "截止市場交易日期7")
    private Date trxMarketDat7;
    /** 截止市場交易日期8 */
    @Schema(description = "截止市場交易日期8")
    private Date trxMarketDat8;
    /** 截止市場交易日期9 */
    @Schema(description = "截止市場交易日期9")
    private Date trxMarketDat9;
    /** 截止市場交易日期10 */
    @Schema(description = "截止市場交易日期10")
    private Date trxMarketDat10;
    /** 賣出在途使用金額 */
    @Schema(description = "賣出在途使用金額")
    private BigDecimal stockUForCurBal;
    /** 賣出在途使用金額-台幣 */
    @Schema(description = "賣出在途使用金額-台幣")
    private BigDecimal stockUBal;
    /** 股票/ETF圈存金額 */
    @Schema(description = "股票/ETF圈存金額")
    private BigDecimal stockCForCurBal;
    /** 股票/ETF圈存金額-台 */
    @Schema(description = "股票/ETF圈存金額-台")
    private BigDecimal stockCBal;
    /** 股票/ETF委託原幣金額 */
    @Schema(description = "股票/ETF委託原幣金額")
    private BigDecimal trmCurBal;
    /** 股票/ETF委託台幣金額 */
    @Schema(description = "股票/ETF委託台幣金額")
    private BigDecimal trmBal;
    /** 原幣金額 */
    @Schema(description = "原幣金額")
    private BigDecimal stockTForCurBal;
    /** 台幣金額 */
    @Schema(description = "台幣金額")
    private BigDecimal stockTBal;
    /** 累積配息 */
    @Schema(description = "累積配息")
    private BigDecimal dividend;
    /** 累積配息台幣 */
    @Schema(description = "累積配息台幣")
    private BigDecimal dividendTwd;

    // ======================== 以下為擴充欄位 ========================

    /** 商品幣別名稱 */
    @Schema(description = "商品幣別名稱")
    private String curName;

    /** 交易幣別名稱 */
    @Schema(description = "交易幣別名稱")
    private String entrustCurName;

    /** 序列 */
    @Schema(description = "序列")
    private int index;

    /** 信託型態註記 單筆 1 定期定額 2 */
    private String trustTypeFlag;

    // ======================== 以下為定期定額專屬欄位 ========================

    /** 含息報酬率正負 */
    private String returnRate2Sign;

    /** 含息報酬率 */
    private BigDecimal return2Rate;

    /** 信託型態 */
    private String trustClass;

    /** 停損點正負 */
    private String stopLossSign;

    /** 停損點 */
    private BigDecimal stopLossRate;

    /** 停利點正負 */
    private String stopEarnSign;

    /** 停利點 */
    private BigDecimal stopEarnRate;

    /** 外幣參考市值(含息) */
    private BigDecimal forCurBalDiv;

    /** 台幣市值(含息) */
    private BigDecimal acctBalDiv;

    /** 外幣成本（疑似筆誤命名） */
    private BigDecimal acctBalCostCost;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEntrustCur() {
        return entrustCur;
    }

    public void setEntrustCur(String entrustCur) {
        this.entrustCur = entrustCur;
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

    public String getCreditAcct() {
        return creditAcct;
    }

    public void setCreditAcct(String creditAcct) {
        this.creditAcct = creditAcct;
    }

    public String getTrxMarketCode() {
        return trxMarketCode;
    }

    public void setTrxMarketCode(String trxMarketCode) {
        this.trxMarketCode = trxMarketCode;
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

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getTrustType() {
        return trustType;
    }

    public void setTrustType(String trustType) {
        this.trustType = trustType;
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

    public Date getTradeDateEnd() {
        return tradeDateEnd;
    }

    public void setTradeDateEnd(Date tradeDateEnd) {
        this.tradeDateEnd = tradeDateEnd;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Date getTrxMarketDat7() {
        return trxMarketDat7;
    }

    public void setTrxMarketDat7(Date trxMarketDat7) {
        this.trxMarketDat7 = trxMarketDat7;
    }

    public Date getTrxMarketDat8() {
        return trxMarketDat8;
    }

    public void setTrxMarketDat8(Date trxMarketDat8) {
        this.trxMarketDat8 = trxMarketDat8;
    }

    public Date getTrxMarketDat9() {
        return trxMarketDat9;
    }

    public void setTrxMarketDat9(Date trxMarketDat9) {
        this.trxMarketDat9 = trxMarketDat9;
    }

    public Date getTrxMarketDat10() {
        return trxMarketDat10;
    }

    public void setTrxMarketDat10(Date trxMarketDat10) {
        this.trxMarketDat10 = trxMarketDat10;
    }

    public BigDecimal getStockUForCurBal() {
        return stockUForCurBal;
    }

    public void setStockUForCurBal(BigDecimal stockUForCurBal) {
        this.stockUForCurBal = stockUForCurBal;
    }

    public BigDecimal getStockUBal() {
        return stockUBal;
    }

    public void setStockUBal(BigDecimal stockUBal) {
        this.stockUBal = stockUBal;
    }

    public BigDecimal getStockCForCurBal() {
        return stockCForCurBal;
    }

    public void setStockCForCurBal(BigDecimal stockCForCurBal) {
        this.stockCForCurBal = stockCForCurBal;
    }

    public BigDecimal getStockCBal() {
        return stockCBal;
    }

    public void setStockCBal(BigDecimal stockCBal) {
        this.stockCBal = stockCBal;
    }

    public BigDecimal getTrmCurBal() {
        return trmCurBal;
    }

    public void setTrmCurBal(BigDecimal trmCurBal) {
        this.trmCurBal = trmCurBal;
    }

    public BigDecimal getTrmBal() {
        return trmBal;
    }

    public void setTrmBal(BigDecimal trmBal) {
        this.trmBal = trmBal;
    }

    public BigDecimal getStockTForCurBal() {
        return stockTForCurBal;
    }

    public void setStockTForCurBal(BigDecimal stockTForCurBal) {
        this.stockTForCurBal = stockTForCurBal;
    }

    public BigDecimal getStockTBal() {
        return stockTBal;
    }

    public void setStockTBal(BigDecimal stockTBal) {
        this.stockTBal = stockTBal;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getEntrustCurName() {
        return entrustCurName;
    }

    public void setEntrustCurName(String entrustCurName) {
        this.entrustCurName = entrustCurName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the returnRate2Sign
     */
    public String getReturnRate2Sign() {
        return returnRate2Sign;
    }

    /**
     * @param returnRate2Sign
     *            the returnRate2Sign to set
     */
    public void setReturnRate2Sign(String returnRate2Sign) {
        this.returnRate2Sign = returnRate2Sign;
    }

    /**
     * @return the return2Rate
     */
    public BigDecimal getReturn2Rate() {
        return return2Rate;
    }

    /**
     * @param return2Rate
     *            the return2Rate to set
     */
    public void setReturn2Rate(BigDecimal return2Rate) {
        this.return2Rate = return2Rate;
    }

    /**
     * @return the trustClass
     */
    public String getTrustClass() {
        return trustClass;
    }

    /**
     * @param trustClass
     *            the trustClass to set
     */
    public void setTrustClass(String trustClass) {
        this.trustClass = trustClass;
    }

    /**
     * @return the stopLossSign
     */
    public String getStopLossSign() {
        return stopLossSign;
    }

    /**
     * @param stopLossSign
     *            the stopLossSign to set
     */
    public void setStopLossSign(String stopLossSign) {
        this.stopLossSign = stopLossSign;
    }

    /**
     * @return the stopLossRate
     */
    public BigDecimal getStopLossRate() {
        return stopLossRate;
    }

    /**
     * @param stopLossRate
     *            the stopLossRate to set
     */
    public void setStopLossRate(BigDecimal stopLossRate) {
        this.stopLossRate = stopLossRate;
    }

    /**
     * @return the stopEarnSign
     */
    public String getStopEarnSign() {
        return stopEarnSign;
    }

    /**
     * @param stopEarnSign
     *            the stopEarnSign to set
     */
    public void setStopEarnSign(String stopEarnSign) {
        this.stopEarnSign = stopEarnSign;
    }

    /**
     * @return the stopEarnRate
     */
    public BigDecimal getStopEarnRate() {
        return stopEarnRate;
    }

    /**
     * @param stopEarnRate
     *            the stopEarnRate to set
     */
    public void setStopEarnRate(BigDecimal stopEarnRate) {
        this.stopEarnRate = stopEarnRate;
    }

    /**
     * @return the forCurBalDiv
     */
    public BigDecimal getForCurBalDiv() {
        return forCurBalDiv;
    }

    /**
     * @param forCurBalDiv
     *            the forCurBalDiv to set
     */
    public void setForCurBalDiv(BigDecimal forCurBalDiv) {
        this.forCurBalDiv = forCurBalDiv;
    }

    /**
     * @return the acctBalDiv
     */
    public BigDecimal getAcctBalDiv() {
        return acctBalDiv;
    }

    /**
     * @param acctBalDiv
     *            the acctBalDiv to set
     */
    public void setAcctBalDiv(BigDecimal acctBalDiv) {
        this.acctBalDiv = acctBalDiv;
    }

    /**
     * @return the acctBalCostCost
     */
    public BigDecimal getAcctBalCostCost() {
        return acctBalCostCost;
    }

    /**
     * @param acctBalCostCost
     *            the acctBalCostCost to set
     */
    public void setAcctBalCostCost(BigDecimal acctBalCostCost) {
        this.acctBalCostCost = acctBalCostCost;
    }

    /**
     * @return the trustTypeFlag
     */
    public String getTrustTypeFlag() {
        return trustTypeFlag;
    }

    /**
     * @param trustTypeFlag
     *            the trustTypeFlag to set
     */
    public void setTrustTypeFlag(String trustTypeFlag) {
        this.trustTypeFlag = trustTypeFlag;
    }

    /** 是否為單筆 */
    public boolean isSingle() {
        return StringUtils.equals("1", getTrustTypeFlag());
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
