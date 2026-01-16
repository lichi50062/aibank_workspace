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
package com.tfb.aibank.chl.creditcard.qu011.model;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
 * @(#)NCCQU011CostcoHistory.java
 * 
 * <p>Description:好多金明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU011CostcoDetail {

    public NCCQU011CostcoDetail() {
        // default constructor
    }

    /** 月份小標 */
    private String monthDisplay;

    /** 交易日期 */
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date txndate;

    /** 交易日期 */
    @FormatDate(pattern = "MM/dd")
    private Date txndateMMDD;

    /** 交易點數正負符號(點數減少:以”-“呈現，EX:使用、到期,點數增加:以”+”呈現，EX:消費回饋、贈送) */
    private String signflg;

    /** 交易點數 */
    private BigDecimal txnpnts;

    /** 交易說明 */
    private String txntxt;

    /** 卡號(顯示) */
    private String crdno;

    /** 交易類型(ADD: 權益、USE: 使用、ADJ: 調整、EXP: 到期) */
    private String txntype;

    /** 特店類型(ADD: 權益分類[1: 店外、2: 店內 - 線下、3: 店內 - 線上、4: 店內 - 加油站、5: 保費]、USE: 使用分類[1: 消費折抵、2: 自動續約折抵]、ADJ: 調整分類[1: 其他 (EX: 個案調整 )、2: 行銷活動、3: 消費折抵點數退貨]、EXP: 到期分類[1: 定期歸零 ( 每年 10/31 到期 )]) */
    private String mertype;

    /** 備註 */
    private String memo;

    /** 信用卡識別資訊 */
    private String cardKey;

    public Date getTxndate() {
        return txndate;
    }

    public void setTxndate(Date txndate) {
        this.txndate = txndate;
    }

    public String getSignflg() {
        return signflg;
    }

    public void setSignflg(String signflg) {
        this.signflg = signflg;
    }

    public BigDecimal getTxnpnts() {
        return txnpnts;
    }

    public void setTxnpnts(BigDecimal txnpnts) {
        this.txnpnts = txnpnts;
    }

    public String getTxntxt() {
        return txntxt;
    }

    public void setTxntxt(String txntxt) {
        this.txntxt = txntxt;
    }

    public String getCrdno() {
        return crdno;
    }

    public void setCrdno(String crdno) {
        this.crdno = crdno;
    }

    public String getTxntype() {
        return txntype;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    public String getMertype() {
        return mertype;
    }

    public void setMertype(String mertype) {
        this.mertype = mertype;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getTxndateMMDD() {
        return txndateMMDD;
    }

    public void setTxndateMMDD(Date txndateMMDD) {
        this.txndateMMDD = txndateMMDD;
    }

    public String getMonthDisplay() {
        return monthDisplay;
    }

    public void setMonthDisplay(String monthDisplay) {
        this.monthDisplay = monthDisplay;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

}
