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
package com.tfb.aibank.common.model;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)FastPurchaseSN.java
* 
* <p>Description: 境內外結構型商品快速申購</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/13, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on

public class FastPurchaseSN {

    /** 商品代號 */
    private String prodId;

    /** 申購批號 */
    private String batchSeq;

    /** 信託幣別類型 N：台幣 Y：外幣 */
    private String trustCurrType;

    /** 應用金額/庫存面額 */
    private BigDecimal purchaseAmt;

    /** 計價幣別 */
    private String prodCurr;

    /** 委託價格類型 / 贖回方式 */
    private String entrustType;

    /** 委託價格 / 贖回價格 */
    private BigDecimal entrustAmt;

    /** 信託本金 */
    private BigDecimal trustAmt;

    /** 扣款帳號 */
    private String debitAcct;

    /** 手續費率 / 信託管理費率 */
    private BigDecimal feeRate;

    /** 應付前手息 / 應收前手息 */
    private BigDecimal payableFee;

    /** 手續費金額 / 預估信託管理費 */
    private BigDecimal fee;

    /** 決定扣款金額 / 預估贖回入帳金額 */
    private BigDecimal totAmt;

    /** 價格日期 */
    private Date refValDate;

    /** 流水序號 */
    private String applySeq;

    /** 90天內貸轉投 */
    private String flagNumber;

    /** 不保本比重查詢 */
    private BigDecimal grntRate;

    /** 商品風險告知重要事項 */
    private String recYn;

    /** 授權交易人員 */
    private String authId;

    /** 商品名稱 */
    private String prodName;

    /** 失敗訊息 */
    private String errorDesc;

    public FastPurchaseSN() {
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getBatchSeq() {
        return batchSeq;
    }

    public void setBatchSeq(String batchSeq) {
        this.batchSeq = batchSeq;
    }

    public String getTrustCurrType() {
        return trustCurrType;
    }

    public void setTrustCurrType(String trustCurrType) {
        this.trustCurrType = trustCurrType;
    }

    public BigDecimal getPurchaseAmt() {
        return purchaseAmt;
    }

    public void setPurchaseAmt(BigDecimal purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }

    public String getProdCurr() {
        return prodCurr;
    }

    public void setProdCurr(String prodCurr) {
        this.prodCurr = prodCurr;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public BigDecimal getTrustAmt() {
        return trustAmt;
    }

    public void setTrustAmt(BigDecimal trustAmt) {
        this.trustAmt = trustAmt;
    }

    public String getDebitAcct() {
        return debitAcct;
    }

    public void setDebitAcct(String debitAcct) {
        this.debitAcct = debitAcct;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getPayableFee() {
        return payableFee;
    }

    public void setPayableFee(BigDecimal payableFee) {
        this.payableFee = payableFee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    public Date getRefValDate() {
        return refValDate;
    }

    public void setRefValDate(Date refValDate) {
        this.refValDate = refValDate;
    }

    public String getApplySeq() {
        return applySeq;
    }

    public void setApplySeq(String applySeq) {
        this.applySeq = applySeq;
    }

    public String getFlagNumber() {
        return flagNumber;
    }

    public void setFlagNumber(String flagNumber) {
        this.flagNumber = flagNumber;
    }

    /**
     * @return the grntRate
     */
    public BigDecimal getGrntRate() {
        return grntRate;
    }

    /**
     * @param grntRate
     *            the grntRate to set
     */
    public void setGrntRate(BigDecimal grntRate) {
        this.grntRate = grntRate;
    }

    /**
     * @return the recYn
     */
    public String getRecYn() {
        return recYn;
    }

    /**
     * @param recYn
     *            the recYn to set
     */
    public void setRecYn(String recYn) {
        this.recYn = recYn;
    }

    /**
     * @return the authId
     */
    public String getAuthId() {
        return authId;
    }

    /**
     * @param authId
     *            the authId to set
     */
    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

}
