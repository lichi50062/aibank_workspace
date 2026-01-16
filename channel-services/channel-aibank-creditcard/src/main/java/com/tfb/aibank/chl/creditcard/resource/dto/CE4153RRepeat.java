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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 指定消費分期設定交易 下行電文 repeat
 * 
 * @author Evan
 */
public class CE4153RRepeat {

    /** 卡號 */
    private String pin;

    /** 消費日期 */
    private Date pchday;

    /** 授權碼 */
    private String authCode;

    /** 台幣金額 */
    private BigDecimal txAmt;

    /** 分期利率 */
    private BigDecimal rate;

    /** 扣帳順序 */
    private String ncsbsq;

    /** 入帳日 */
    private Date nccday;

    /** 產生組別 */
    private String ncgrop;

    /** 交易序號 */
    private String ncseqn;

    /** 原始地幣別 */
    private String srccur;

    /** 原始地金額 */
    private BigDecimal srcamt;

    /** 消費說明 */
    private String ncbtxt1;

    /** 目的地金額 */
    private BigDecimal desamt;

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin
     *            the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the pchday
     */
    public Date getPchday() {
        return pchday;
    }

    /**
     * @param pchday
     *            the pchday to set
     */
    public void setPchday(Date pchday) {
        this.pchday = pchday;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode
     *            the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the txAmt
     */
    public BigDecimal getTxAmt() {
        return txAmt;
    }

    /**
     * @param txAmt
     *            the txAmt to set
     */
    public void setTxAmt(BigDecimal txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * @return the ncsbsq
     */
    public String getNcsbsq() {
        return ncsbsq;
    }

    /**
     * @param ncsbsq
     *            the ncsbsq to set
     */
    public void setNcsbsq(String ncsbsq) {
        this.ncsbsq = ncsbsq;
    }

    /**
     * @return the nccday
     */
    public Date getNccday() {
        return nccday;
    }

    /**
     * @param nccday
     *            the nccday to set
     */
    public void setNccday(Date nccday) {
        this.nccday = nccday;
    }

    /**
     * @return the ncgrop
     */
    public String getNcgrop() {
        return ncgrop;
    }

    /**
     * @param ncgrop
     *            the ncgrop to set
     */
    public void setNcgrop(String ncgrop) {
        this.ncgrop = ncgrop;
    }

    /**
     * @return the ncseqn
     */
    public String getNcseqn() {
        return ncseqn;
    }

    /**
     * @param ncseqn
     *            the ncseqn to set
     */
    public void setNcseqn(String ncseqn) {
        this.ncseqn = ncseqn;
    }

    /**
     * @return the srccur
     */
    public String getSrccur() {
        return srccur;
    }

    /**
     * @param srccur
     *            the srccur to set
     */
    public void setSrccur(String srccur) {
        this.srccur = srccur;
    }

    /**
     * @return the srcamt
     */
    public BigDecimal getSrcamt() {
        return srcamt;
    }

    /**
     * @param srcamt
     *            the srcamt to set
     */
    public void setSrcamt(BigDecimal srcamt) {
        this.srcamt = srcamt;
    }

    /**
     * @return the ncbtxt1
     */
    public String getNcbtxt1() {
        return ncbtxt1;
    }

    /**
     * @param ncbtxt1
     *            the ncbtxt1 to set
     */
    public void setNcbtxt1(String ncbtxt1) {
        this.ncbtxt1 = ncbtxt1;
    }

    /**
     * @return the desamt
     */
    public BigDecimal getDesamt() {
        return desamt;
    }

    /**
     * @param desamt
     *            the desamt to set
     */
    public void setDesamt(BigDecimal desamt) {
        this.desamt = desamt;
    }

}
