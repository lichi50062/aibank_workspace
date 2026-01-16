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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.model.TxHeadRq;
import com.tfb.aibank.common.type.ReedType;

// @formatter:off
/**
 * @(#)NJW020Req.java
 * 
 * <p>Description:[NJW020 債券營業時間交易]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/16, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 * v1.0, 2025/07/03, Edward Tien 
 * <ol>
 *  <li>新增 String reedTypeNew 取代 ReedType reedType</li>
 * </ol>
 */
// @formatter:on
@SuppressWarnings("serial")
public class NJW020Req extends TxHeadRq {
    /** 帳號 */
    private String acctId16;
    /** 密碼 */
    private String custPswd32;
    /** 客戶ID */
    private String custId;
    /** 使用者代號 */
    private String curAcctId;
    /** 戶名代號 */
    private String curAcctName;
    /** 檢核確認碼 (1.檢核 2.確認) */
    private String confirmCode;
    /** 交易來源 */
    private String chSource;
    /** 交易類別 */
    private String txType;
    /** 交易日 */
    private Date tranDate;
    /** 生效日 */
    private Date trscDate;
    /** 交易帳號 */
    private String acno;
    /** 債券代號 */
    private String bondNo;
    /** 交易金額 */
    private BigDecimal txAmt;
    /** 信託業務別 (Y.外幣 N.台幣) */
    private String trustType;
    /** 贖回方式 (1.全部 2.部分) */
    private String backType;
    /** 憑證號碼 */
    private String eviNum;
    /** 限價方式 */
    private String reedTypeNew;
    /** 限價方式 */
    private ReedType reedType;
    /** 委賣單價 */
    private BigDecimal reedPrice;
    /** 參考報價 */
    private BigDecimal refPrice;
    /** 報價日期 */
    private String refPriceDate;
    /** 委託時間 */
    private String txTime;
    /** 預約單/長效單生效起日 */
    private String expStartDt;
    /** 預約單/長效單生效迄日 */
    private String expEndDt;
    /** 面額買進 */
    private String applyType;
    /** 初級或次級 */
    private String type;
    /** 手續費議價 */
    private String txFeeType;
    /** 手續費費率 */
    private BigDecimal txFeeRate;
    /** 被授權人ID */
    private String authId;

    /**
     * @return the acctId16
     */
    public String getAcctId16() {
        return acctId16;
    }

    /**
     * @param acctId16
     *            the acctId16 to set
     */
    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    /**
     * @return the custPswd32
     */
    public String getCustPswd32() {
        return custPswd32;
    }

    /**
     * @param custPswd32
     *            the custPswd32 to set
     */
    public void setCustPswd32(String custPswd32) {
        this.custPswd32 = custPswd32;
    }

    /**
     * @return the chSource
     */
    public String getChSource() {
        return chSource;
    }

    /**
     * @param chSource
     *            the chSource to set
     */
    public void setChSource(String chSource) {
        this.chSource = chSource;
    }

    /**
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the curAcctId
     */
    public String getCurAcctId() {
        return curAcctId;
    }

    /**
     * @param curAcctId
     *            the curAcctId to set
     */
    public void setCurAcctId(String curAcctId) {
        this.curAcctId = curAcctId;
    }

    /**
     * @return the curAcctName
     */
    public String getCurAcctName() {
        return curAcctName;
    }

    /**
     * @param curAcctName
     *            the curAcctName to set
     */
    public void setCurAcctName(String curAcctName) {
        this.curAcctName = curAcctName;
    }

    /**
     * @return the confirmCode
     */
    public String getConfirmCode() {
        return confirmCode;
    }

    /**
     * @param confirmCode
     *            the confirmCode to set
     */
    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    /**
     * @return the tranDate
     */
    public Date getTranDate() {
        return tranDate;
    }

    /**
     * @param tranDate
     *            the tranDate to set
     */
    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * @return the trscDate
     */
    public Date getTrscDate() {
        return trscDate;
    }

    /**
     * @param trscDate
     *            the trscDate to set
     */
    public void setTrscDate(Date trscDate) {
        this.trscDate = trscDate;
    }

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

    /**
     * @return the bondNo
     */
    public String getBondNo() {
        return bondNo;
    }

    /**
     * @param bondNo
     *            the bondNo to set
     */
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    /**
     * @return the txAmt
     */
    public BigDecimal getTxAmt() {
        return txAmt;
    }

    /**
     * @param txamt
     *            the txAmt to set
     */
    public void setTxAmt(BigDecimal txamt) {
        this.txAmt = txamt;
    }

    /**
     * @return the trustType
     */
    public String getTrustType() {
        return trustType;
    }

    /**
     * @param trustType
     *            the trustType to set
     */
    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    /**
     * @return the backType
     */
    public String getBackType() {
        return backType;
    }

    /**
     * @param backType
     *            the backType to set
     */
    public void setBackType(String backType) {
        this.backType = backType;
    }

    /**
     * @return the eviNum
     */
    public String getEviNum() {
        return eviNum;
    }

    /**
     * @param eviNum
     *            the eviNum to set
     */
    public void setEviNum(String eviNum) {
        this.eviNum = eviNum;
    }

    /**
     * @return the reedType
     */
    public ReedType getReedType() {
        return reedType;
    }

    /**
     * @param reedType
     *            the reedType to set
     */
    public void setReedType(ReedType reedType) {
        this.reedType = reedType;
    }

    /**
     * @return the reedPrice
     */
    public BigDecimal getReedPrice() {
        return reedPrice;
    }

    /**
     * @param reedPrice
     *            the reedPrice to set
     */
    public void setReedPrice(BigDecimal reedPrice) {
        this.reedPrice = reedPrice;
    }

    /**
     * @return the refPrice
     */
    public BigDecimal getRefPrice() {
        return refPrice;
    }

    /**
     * @param refPrice
     *            the refPrice to set
     */
    public void setRefPrice(BigDecimal refPrice) {
        this.refPrice = refPrice;
    }

    /**
     * @return the refPriceDate
     */
    public String getRefPriceDate() {
        return refPriceDate;
    }

    /**
     * @param refPriceDate
     *            the refPriceDate to set
     */
    public void setRefPriceDate(String refPriceDate) {
        this.refPriceDate = refPriceDate;
    }

    /**
     * @return the txTime
     */
    public String getTxTime() {
        return txTime;
    }

    /**
     * @param txTime
     *            the txTime to set
     */
    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    /**
     * @return the expStartDt
     */
    public String getExpStartDt() {
        return expStartDt;
    }

    /**
     * @param expStartDt
     *            the expStartDt to set
     */
    public void setExpStartDt(String expStartDt) {
        this.expStartDt = expStartDt;
    }

    /**
     * @return the expEndDt
     */
    public String getExpEndDt() {
        return expEndDt;
    }

    /**
     * @param expEndDt
     *            the expEndDt to set
     */
    public void setExpEndDt(String expEndDt) {
        this.expEndDt = expEndDt;
    }

    /**
     * @return the applyType
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * @param applyType
     *            the applyType to set
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTxFeeType() {
        return txFeeType;
    }

    public void setTxFeeType(String txFeeType) {
        this.txFeeType = txFeeType;
    }

    public BigDecimal getTxFeeRate() {
        return txFeeRate;
    }

    public void setTxFeeRate(BigDecimal txFeeRate) {
        this.txFeeRate = txFeeRate;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getReedTypeNew() {
        return reedTypeNew;
    }

    public void setReedTypeNew(String reedTypeNew) {
        this.reedTypeNew = reedTypeNew;
    }

}
