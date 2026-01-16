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
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)NCCQU008BilledDetailVo.java
 * 
 * <p>Description: 明細區塊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008BilledDetailVo {

    /** 是否請款 */
    private Boolean isPayBill;

    /** 消費日期 */
    private String pchDay;

    /** 消費日期 毫秒 */
    private Long pchTime;

    /** 消費說明 */
    private String billDesc;

    /** 消費金額 格式化 */
    private String txAmt;

    /** 消費金額 */
    private BigDecimal txAmtOrigin;

    /** 信用卡卡號 */
    private String cardNo;

    /** 扣帳順序 */
    private String ncsbsq;

    /** 產生組別 */
    private String ncgrop;

    /** 交易序號 */
    private String ncseqn;

    /** 分期種類 */
    private String stagesType;

    /** 入帳日期 */
    private Date creditDate;

    /** 授權碼 */
    private String authCode;

    /** 分期利率 */
    private BigDecimal rate;

    /** 外幣幣別 */
    private String fxCur;

    /** 外幣金額 */
    private BigDecimal fxAmt;

    /** 關帳日 */
    private Date closeDay;

    /** 入帳日 */
    private Date accountDay;

    /** 電文交易代號 */
    private String htxtId;

    /** 稅款類別 */
    private String type;

    /** 卡別 */
    private String ctype;

    /** 是否申請 */
    private Boolean isApply;

    /** 是否請款 */
    private Boolean isGetMoney;

    /**
     * @return the isPayBill
     */
    public Boolean getIsPayBill() {
        return isPayBill;
    }

    /**
     * @param isPayBill
     *            the isPayBill to set
     */
    public void setIsPayBill(Boolean isPayBill) {
        this.isPayBill = isPayBill;
    }

    /**
     * @return the pchDay
     */
    public String getPchDay() {
        return pchDay;
    }

    /**
     * @param pchDay
     *            the pchDay to set
     */
    public void setPchDay(String pchDay) {
        this.pchDay = pchDay;
    }

    /**
     * @return the pchTime
     */
    public Long getPchTime() {
        return pchTime;
    }

    /**
     * @param pchTime
     *            the pchTime to set
     */
    public void setPchTime(Long pchTime) {
        this.pchTime = pchTime;
    }

    /**
     * @return the billDesc
     */
    public String getBillDesc() {
        return billDesc;
    }

    /**
     * @param billDesc
     *            the billDesc to set
     */
    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    /**
     * @return the txAmt
     */
    public String getTxAmt() {
        return txAmt;
    }

    /**
     * @param txAmt
     *            the txAmt to set
     */
    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * @return the txAmtOrigin
     */
    public BigDecimal getTxAmtOrigin() {
        return txAmtOrigin;
    }

    /**
     * @param txAmtOrigin
     *            the txAmtOrigin to set
     */
    public void setTxAmtOrigin(BigDecimal txAmtOrigin) {
        this.txAmtOrigin = txAmtOrigin;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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
     * @return the stagesType
     */
    public String getStagesType() {
        return stagesType;
    }

    /**
     * @param stagesType
     *            the stagesType to set
     */
    public void setStagesType(String stagesType) {
        this.stagesType = stagesType;
    }

    /**
     * @return the creditDate
     */
    public Date getCreditDate() {
        return creditDate;
    }

    /**
     * @param creditDate
     *            the creditDate to set
     */
    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
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
     * @return the fxCur
     */
    public String getFxCur() {
        return fxCur;
    }

    /**
     * @param fxCur
     *            the fxCur to set
     */
    public void setFxCur(String fxCur) {
        this.fxCur = fxCur;
    }

    /**
     * @return the fxAmt
     */
    public BigDecimal getFxAmt() {
        return fxAmt;
    }

    /**
     * @param fxAmt
     *            the fxAmt to set
     */
    public void setFxAmt(BigDecimal fxAmt) {
        this.fxAmt = fxAmt;
    }

    /**
     * @return the closeDay
     */
    public Date getCloseDay() {
        return closeDay;
    }

    /**
     * @param closeDay
     *            the closeDay to set
     */
    public void setCloseDay(Date closeDay) {
        this.closeDay = closeDay;
    }

    /**
     * @return the accountDay
     */
    public Date getAccountDay() {
        return accountDay;
    }

    /**
     * @param accountDay
     *            the accountDay to set
     */
    public void setAccountDay(Date accountDay) {
        this.accountDay = accountDay;
    }

    public String getHtxtId() {
        return htxtId;
    }

    public void setHtxtId(String htxtId) {
        this.htxtId = htxtId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCardNxLastFour() {
        return StringUtils.right(getCardNo(), 4);
    }

    public Boolean getIsApply() {
        return isApply;
    }

    public void setIsApply(Boolean isApply) {
        this.isApply = isApply;
    }

    public Boolean getIsGetMoney() {
        return isGetMoney;
    }

    public void setIsGetMoney(Boolean isGetMoney) {
        this.isGetMoney = isGetMoney;
    }

}
