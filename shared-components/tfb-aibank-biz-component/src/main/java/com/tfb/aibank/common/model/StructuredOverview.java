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

import java.math.BigDecimal;

import com.tfb.aibank.common.type.HybridProdType;

// @formatter:off
/**
 * @(#)StructuredOverview.java
 * 
 * <p>結構式商品-整合資訊使用<</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/14, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class StructuredOverview {

    private HybridProdType prodType;

    private String category;
    /** 產品名稱 */
    private String prdNam;
    /** 產品代碼 */
    private String prdId;
    /** 交易序號、存單號碼 */
    private String ivtdNo;
    /** 投資本金 */
    private BigDecimal ivamt2;
    /** 幣別 */
    private String ccy;
    /** 商品牌告利率 */
    private String deprat;
    /** 起息日 */
    private String depstr;
    /** 到期日 */
    private String depend;
    /** 報價日 */
    private String prddte;
    /** 參考報價 */
    private String sdamt2;
    /** 累積現金配息 */
    private BigDecimal plamt;
    /** 收件行 */
    private String ivbrh;
    /** 定存帳號 */
    private String ivtdac;
    /** 交易序號 */
    private String tranNo;
    /** 商品幣別 */
    private String curCode;
    /** 相對幣別 */
    private String trscCur;
    /** 交易日 */
    private String tranDate;
    /** 商品(預定)到期日 */
    private String endDate;
    /** 投資金額 */
    private BigDecimal investAmt;
    /** 履約價 */
    private BigDecimal tradeAmt;
    /** 折合台幣參考金額 */
    private BigDecimal refAmt;
    /** 參考金額 */
    private BigDecimal refAmtOri;
    /** 收件代碼 */
    private String ivrNo;
    /** 訊息碼 */
    private String msgCode;
    /** 交易編號PK */
    private String tranId;
    /** 交易日 */
    private String tradeDate;
    /** 參考即期價格 */
    private BigDecimal spotRate;
    /** 比價日 */
    private String expiryDate;
    /** 到期金額 */
    private BigDecimal deliveryDateAmount;
    /** 市場評估日 */
    private String marketEvalationDate;
    /** 商品幣別 */
    private String currency;
    /** 到期日 */
    private String deliveryDate;
    /** 履約價 */
    private BigDecimal strike;
    /** 比價日即期價 */
    private BigDecimal expiryDateSpotRate;
    /** 利息金額 */
    private BigDecimal interestAmount;
    /** 與本金之百分比 */
    private String compareToAmount;
    /** 相對幣別 */
    private String mappingCurrency;
    /** 交易金額 */
    private BigDecimal dcdAmount;
    /** 產品收益率 */
    private BigDecimal yield;
    /** 幣轉狀態 */
    private String currencyChange;
    /** 總費用 */
    private BigDecimal totalFee;
    /** 交易狀態 */
    private String tradeStatus;
    /** FORMAT_COD */
    private String lsForm;
    /** 參考台幣價格 */
    private BigDecimal dcdAmountNtd;
    /** 參考(投資)市值 */
    private BigDecimal dcdCurAmount;
    /** 折台前參考投資市值 */
    private BigDecimal dcdCurAmountNtd;
    /** 保值型 */
    private String breakevenId;

    /**
     * @return the dcdCurAmount
     */
    public BigDecimal getDcdCurAmount() {
        return dcdCurAmount;
    }

    /**
     * @param dcdCurAmount
     *            the dcdCurAmount to set
     */
    public void setDcdCurAmount(BigDecimal dcdCurAmount) {
        this.dcdCurAmount = dcdCurAmount;
    }

    /**
     * @return the dcdCurAmountNtd
     */
    public BigDecimal getDcdCurAmountNtd() {
        return dcdCurAmountNtd;
    }

    /**
     * @param dcdCurAmountNtd
     *            the dcdCurAmountNtd to set
     */
    public void setDcdCurAmountNtd(BigDecimal dcdCurAmountNtd) {
        this.dcdCurAmountNtd = dcdCurAmountNtd;
    }

    /**
     * @return the prodType
     */
    public HybridProdType getProdType() {
        return prodType;
    }

    /**
     * @param prodType
     *            the prodType to set
     */
    public void setProdType(HybridProdType prodType) {
        this.prodType = prodType;
    }

    /**
     * @return {@link #category}
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            {@link #category}
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return {@link #prdNam}
     */
    public String getPrdNam() {
        return prdNam;
    }

    /**
     * @param prdNam
     *            {@link #prdNam}
     */
    public void setPrdNam(String prdNam) {
        this.prdNam = prdNam;
    }

    /**
     * @return {@link #prdId}
     */
    public String getPrdId() {
        return prdId;
    }

    /**
     * @param prdId
     *            {@link #prdId}
     */
    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    /**
     * @return {@link #ivtdNo}
     */
    public String getIvtdNo() {
        return ivtdNo;
    }

    /**
     * @param ivtdNo
     *            {@link #ivtdNo}
     */
    public void setIvtdNo(String ivtdNo) {
        this.ivtdNo = ivtdNo;
    }

    /**
     * @return {@link #ivamt2}
     */
    public BigDecimal getIvamt2() {
        return ivamt2;
    }

    /**
     * @param ivamt2
     *            {@link #ivamt2}
     */
    public void setIvamt2(BigDecimal ivamt2) {
        this.ivamt2 = ivamt2;
    }

    /**
     * @return {@link #ccy}
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy
     *            {@link #ccy}
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * @return {@link #deprat}
     */
    public String getDeprat() {
        return deprat;
    }

    /**
     * @param deprat
     *            {@link #deprat}
     */
    public void setDeprat(String deprat) {
        this.deprat = deprat;
    }

    /**
     * @return {@link #depstr}
     */
    public String getDepstr() {
        return depstr;
    }

    /**
     * @param depstr
     *            {@link #depstr}
     */
    public void setDepstr(String depstr) {
        this.depstr = depstr;
    }

    /**
     * @return {@link #depend}
     */
    public String getDepend() {
        return depend;
    }

    /**
     * @param depend
     *            {@link #depend}
     */
    public void setDepend(String depend) {
        this.depend = depend;
    }

    /**
     * @return {@link #prddte}
     */
    public String getPrddte() {
        return prddte;
    }

    /**
     * @param prddte
     *            {@link #prddte}
     */
    public void setPrddte(String prddte) {
        this.prddte = prddte;
    }

    /**
     * @return {@link #sdamt2}
     */
    public String getSdamt2() {
        return sdamt2;
    }

    /**
     * @param sdamt2
     *            {@link #sdamt2}
     */
    public void setSdamt2(String sdamt2) {
        this.sdamt2 = sdamt2;
    }

    /**
     * @return {@link #plamt}
     */
    public BigDecimal getPlamt() {
        return plamt;
    }

    /**
     * @param plamt
     *            {@link #plamt}
     */
    public void setPlamt(BigDecimal plamt) {
        this.plamt = plamt;
    }

    /**
     * @return {@link #ivbrh}
     */
    public String getIvbrh() {
        return ivbrh;
    }

    /**
     * @param ivbrh
     *            {@link #ivbrh}
     */
    public void setIvbrh(String ivbrh) {
        this.ivbrh = ivbrh;
    }

    /**
     * @return {@link #ivtdac}
     */
    public String getIvtdac() {
        return ivtdac;
    }

    /**
     * @param ivtdac
     *            {@link #ivtdac}
     */
    public void setIvtdac(String ivtdac) {
        this.ivtdac = ivtdac;
    }

    /**
     * @return {@link #tranNo}
     */
    public String getTranNo() {
        return tranNo;
    }

    /**
     * @param tranNo
     *            {@link #tranNo}
     */
    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    /**
     * @return {@link #curCode}
     */
    public String getCurCode() {
        return curCode;
    }

    /**
     * @param curCode
     *            {@link #curCode}
     */
    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    /**
     * @return {@link #trscCur}
     */
    public String getTrscCur() {
        return trscCur;
    }

    /**
     * @param trscCur
     *            {@link #trscCur}
     */
    public void setTrscCur(String trscCur) {
        this.trscCur = trscCur;
    }

    /**
     * @return {@link #tranDate}
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * @param tranDate
     *            {@link #tranDate}
     */
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * @return {@link #endDate}
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            {@link #endDate}
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @return {@link #investAmt}
     */
    public BigDecimal getInvestAmt() {
        return investAmt;
    }

    /**
     * @param investAmt
     *            {@link #investAmt}
     */
    public void setInvestAmt(BigDecimal investAmt) {
        this.investAmt = investAmt;
    }

    /**
     * @return {@link #tradeAmt}
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * @param tradeAmt
     *            {@link #tradeAmt}
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * @return {@link #refAmt}
     */
    public BigDecimal getRefAmt() {
        return refAmt;
    }

    /**
     * @param refAmt
     *            {@link #refAmt}
     */
    public void setRefAmt(BigDecimal refAmt) {
        this.refAmt = refAmt;
    }

    /**
     * @return {@link #ivrNo}
     */
    public String getIvrNo() {
        return ivrNo;
    }

    /**
     * @param ivrNo
     *            {@link #ivrNo}
     */
    public void setIvrNo(String ivrNo) {
        this.ivrNo = ivrNo;
    }

    /**
     * @return {@link #msgCode}
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode
     *            {@link #msgCode}
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * @return {@link #tranId}
     */
    public String getTranId() {
        return tranId;
    }

    /**
     * @param tranId
     *            {@link #tranId}
     */
    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    /**
     * @return {@link #tradeDate}
     */
    public String getTradeDate() {
        return tradeDate;
    }

    /**
     * @param tradeDate
     *            {@link #tradeDate}
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * @return {@link #spotRate}
     */
    public BigDecimal getSpotRate() {
        return spotRate;
    }

    /**
     * @param spotRate
     *            {@link #spotRate}
     */
    public void setSpotRate(BigDecimal spotRate) {
        this.spotRate = spotRate;
    }

    /**
     * @return {@link #expiryDate}
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate
     *            {@link #expiryDate}
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return {@link #deliveryDateAmount}
     */
    public BigDecimal getDeliveryDateAmount() {
        return deliveryDateAmount;
    }

    /**
     * @param deliveryDateAmount
     *            {@link #deliveryDateAmount}
     */
    public void setDeliveryDateAmount(BigDecimal deliveryDateAmount) {
        this.deliveryDateAmount = deliveryDateAmount;
    }

    /**
     * @return {@link #marketEvalationDate}
     */
    public String getMarketEvalationDate() {
        return marketEvalationDate;
    }

    /**
     * @param marketEvalationDate
     *            {@link #marketEvalationDate}
     */
    public void setMarketEvalationDate(String marketEvalationDate) {
        this.marketEvalationDate = marketEvalationDate;
    }

    /**
     * @return {@link #currency}
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     *            {@link #currency}
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return {@link #deliveryDate}
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate
     *            {@link #deliveryDate}
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return {@link #strike}
     */
    public BigDecimal getStrike() {
        return strike;
    }

    /**
     * @param strike
     *            {@link #strike}
     */
    public void setStrike(BigDecimal strike) {
        this.strike = strike;
    }

    /**
     * @return {@link #expiryDateSpotRate}
     */
    public BigDecimal getExpiryDateSpotRate() {
        return expiryDateSpotRate;
    }

    /**
     * @param expiryDateSpotRate
     *            {@link #expiryDateSpotRate}
     */
    public void setExpiryDateSpotRate(BigDecimal expiryDateSpotRate) {
        this.expiryDateSpotRate = expiryDateSpotRate;
    }

    /**
     * @return {@link #interestAmount}
     */
    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    /**
     * @param interestAmount
     *            {@link #interestAmount}
     */
    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    /**
     * @return {@link #compareToAmount}
     */
    public String getCompareToAmount() {
        return compareToAmount;
    }

    /**
     * @param compareToAmount
     *            {@link #compareToAmount}
     */
    public void setCompareToAmount(String compareToAmount) {
        this.compareToAmount = compareToAmount;
    }

    /**
     * @return {@link #mappingCurrency}
     */
    public String getMappingCurrency() {
        return mappingCurrency;
    }

    /**
     * @param mappingCurrency
     *            {@link #mappingCurrency}
     */
    public void setMappingCurrency(String mappingCurrency) {
        this.mappingCurrency = mappingCurrency;
    }

    /**
     * @return {@link #dcdAmount}
     */
    public BigDecimal getDcdAmount() {
        return dcdAmount;
    }

    /**
     * @param dcdAmount
     *            {@link #dcdAmount}
     */
    public void setDcdAmount(BigDecimal dcdAmount) {
        this.dcdAmount = dcdAmount;
    }

    /**
     * @return {@link #yield}
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * @param yield
     *            {@link #yield}
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * @return {@link #currencyChange}
     */
    public String getCurrencyChange() {
        return currencyChange;
    }

    /**
     * @param currencyChange
     *            {@link #currencyChange}
     */
    public void setCurrencyChange(String currencyChange) {
        this.currencyChange = currencyChange;
    }

    /**
     * @return {@link #totalFee}
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee
     *            {@link #totalFee}
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @return {@link #tradeStatus}
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * @param tradeStatus
     *            {@link #tradeStatus}
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * @return {@link #lsForm}
     */
    public String getLsForm() {
        return lsForm;
    }

    /**
     * @param lsForm
     *            {@link #lsForm}
     */
    public void setLsForm(String lsForm) {
        this.lsForm = lsForm;
    }

    /**
     * @return {@link #dcdAmountNtd}
     */
    public BigDecimal getDcdAmountNtd() {
        return dcdAmountNtd;
    }

    /**
     * @param dcdAmountNtd
     *            {@link #dcdAmountNtd}
     */
    public void setDcdAmountNtd(BigDecimal dcdAmountNtd) {
        this.dcdAmountNtd = dcdAmountNtd;
    }

    /**
     * @return {@link #breakevenId}
     */
    public String getBreakevenId() {
        return breakevenId;
    }

    /**
     * @param breakevenId
     *            {@link #breakevenId}
     */
    public void setBreakevenId(String breakevenId) {
        this.breakevenId = breakevenId;
    }

    /**
     * @return {@link #refAmtOri}
     */
    public BigDecimal getRefAmtOri() {
        return refAmtOri;
    }

    /**
     * @param refAmtOri
     *            {@link #refAmtOri}
     */
    public void setRefAmtOri(BigDecimal refAmtOri) {
        this.refAmtOri = refAmtOri;
    }

}
