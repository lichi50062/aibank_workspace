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

import java.io.Serializable;
import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NJW020ResRep.java
 * 
 * <p>Description:[NJW020 債券營業時間交易-NJW020XXXXRepeatType]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/16, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@SuppressWarnings("serial")
public class NJW020ResRep implements Serializable {

    public NJW020ResRep() {
        super();
    }

    /** 剩餘面額 */
    private BigDecimal balanceAmt;

    /**
     * BondsName: 債券名稱
     */
    private String bondsName;

    /**
     * EviNum: 憑證號碼
     */
    private String eviNum;

    /**
     * Fee: 預估手續費
     */
    private BigDecimal fee;

    /**
     * CurCode: 交易幣別
     */
    private String curCode;

    /**
     * FeeRate: 預估手續費率
     */
    private BigDecimal feeRate;

    /**
     * TrustAmt: 預估成交金額
     */
    private BigDecimal trustAmt;

    /**
     * TxCurr: 計價幣別
     */
    private String txCurr;

    /**
     * TxMsgCode1: 專業投資人之提示訊息
     */
    private String txMsgCode1;

    /**
     * TxMsgCode2: 是否需錄音
     */
    private String txMsgCode2;

    /** 應付前手息 */
    private BigDecimal txFee1;

    /**
     * TxAmt1: 應收付金額
     */
    private BigDecimal txAmt1;

    /** 交易取消成功失敗碼 Y：正常 */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the balanceAmt
     */
    public BigDecimal getBalanceAmt() {
        return balanceAmt;
    }

    /**
     * @param balanceAmt
     *            the balanceAmt to set
     */
    public void setBalanceAmt(BigDecimal balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    /**
     * @return the bondsName
     */
    public String getBondsName() {
        return bondsName;
    }

    /**
     * @param bondsName
     *            the bondsName to set
     */
    public void setBondsName(String bondsName) {
        this.bondsName = bondsName;
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
     * @return the fee
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * @return the curCode
     */
    public String getCurCode() {
        return curCode;
    }

    /**
     * @param curCode
     *            the curCode to set
     */
    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    /**
     * @return the feeRate
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * @param feeRate
     *            the feeRate to set
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    /**
     * @return the trustAmt
     */
    public BigDecimal getTrustAmt() {
        return trustAmt;
    }

    /**
     * @param trustAmt
     *            the trustAmt to set
     */
    public void setTrustAmt(BigDecimal trustAmt) {
        this.trustAmt = trustAmt;
    }

    /**
     * @return the txCurr
     */
    public String getTxCurr() {
        return txCurr;
    }

    /**
     * @param txCurr
     *            the txCurr to set
     */
    public void setTxCurr(String txCurr) {
        this.txCurr = txCurr;
    }

    /**
     * @return the txMsgCode1
     */
    public String getTxMsgCode1() {
        return txMsgCode1;
    }

    /**
     * @param txMsgCode1
     *            the txMsgCode1 to set
     */
    public void setTxMsgCode1(String txMsgCode1) {
        this.txMsgCode1 = txMsgCode1;
    }

    /**
     * @return the txMsgCode2
     */
    public String getTxMsgCode2() {
        return txMsgCode2;
    }

    /**
     * @param txMsgCode2
     *            the txMsgCode2 to set
     */
    public void setTxMsgCode2(String txMsgCode2) {
        this.txMsgCode2 = txMsgCode2;
    }

    /**
     * @return the txFee1
     */
    public BigDecimal getTxFee1() {
        return txFee1;
    }

    /**
     * @param txFee1
     *            the txFee1 to set
     */
    public void setTxFee1(BigDecimal txFee1) {
        this.txFee1 = txFee1;
    }

    /**
     * @return the txAmt1
     */
    public BigDecimal getTxAmt1() {
        return txAmt1;
    }

    /**
     * @param txAmt1
     *            the txAmt1 to set
     */
    public void setTxAmt1(BigDecimal txAmt1) {
        this.txAmt1 = txAmt1;
    }

}
