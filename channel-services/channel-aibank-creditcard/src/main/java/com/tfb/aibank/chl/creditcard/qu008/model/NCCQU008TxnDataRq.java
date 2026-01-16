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
package com.tfb.aibank.chl.creditcard.qu008.model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

// @formatter:off
/**
 * @(#)NCCQU008TxnDataRq.java
 * 
 * <p>Description:消費資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008TxnDataRq {

    /** 卡號 */
    @SerializedName("CardKey")
    private String cardKey;

    /** 消費日期 */
    @SerializedName("TxnDate")
    private String txnDate;

    /** 入帳日期 */
    @SerializedName("BillDate")
    private String billDate;

    /** 消費說明 */
    @SerializedName("Memo")
    private String memo;

    /** 消費金額 */
    @SerializedName("Amt")
    private BigDecimal amt;

    /** 交易序號 */
    @SerializedName("Seq")
    private String seq;

    /** 年利率 */
    @SerializedName("Rate")
    private String rate;

    /** 關帳日 YYYY/MM/DD */
    @SerializedName("CloseDate")
    private String closeDate;

    /** 1-未請款，2-已請款 */
    @SerializedName("TxnType")
    private String txnType;

    /** 扣帳順序 */
    @SerializedName("Sbsq")
    private String sbsq;

    /** 組別 */
    @SerializedName("Group")
    private String group;

    /**
     * @return the cardNo
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the txnDate
     */
    public String getTxnDate() {
        return txnDate;
    }

    /**
     * @param txnDate
     *            the txnDate to set
     */
    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    /**
     * @return the billDate
     */
    public String getBillDate() {
        return billDate;
    }

    /**
     * @param billDate
     *            the billDate to set
     */
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the amt
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * @param amt
     *            the amt to set
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * @return the seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq
     *            the seq to set
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    /**
     * @return the rate
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * @return the closeDate
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate
     *            the closeDate to set
     */
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the txnType
     */
    public String getTxnType() {
        return txnType;
    }

    /**
     * @param txnType
     *            the txnType to set
     */
    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    /**
     * @return the sbsq
     */
    public String getSbsq() {
        return sbsq;
    }

    /**
     * @param sbsq
     *            the sbsq to set
     */
    public void setSbsq(String sbsq) {
        this.sbsq = sbsq;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

}
