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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)LoanDetailBean.java
 * 
 * <p>Description:繳貸款明細共用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LoanDetailBean {

    /** 交易日 */
    private Date txDate;

    /** 排序日期 */
    private Date orderTxDate;

    /** 排序 */
    private Integer orderIndex;

    /** 本金 */
    private BigDecimal prnAmt = BigDecimal.ZERO;

    /** 利息 */
    private BigDecimal intAmt = BigDecimal.ZERO;

    /** 違約金 */
    private BigDecimal pntAmt = BigDecimal.ZERO;

    /** 逾期違約金 */
    private BigDecimal dlyPntAmt = BigDecimal.ZERO;

    /** 遲延息 */
    private BigDecimal dlyAmt = BigDecimal.ZERO;

    /** 還款金額 */
    private BigDecimal dtlAmt = BigDecimal.ZERO;

    /** 貸款餘額 */
    private BigDecimal loanBal = BigDecimal.ZERO;

    /** 金額 */
    private BigDecimal amount = BigDecimal.ZERO;

    /** 學期別 */
    private String yrTerm;

    /** memo */
    private String memo;

    /** 帳務日 */
    private Date postDate;

    /** 交易代號 */
    private String txCode;

    /** 交易碼 */
    private String tranCode;

    /** 繳交期 */
    private String payPrd;

    /** 幣別. */
    private String cur;

    /** 違約金 */
    private BigDecimal totalPntAmt = BigDecimal.ZERO;

    public LoanDetailBean() {
        super();
    }

    public void updateTotal() {
        dtlAmt = prnAmt.add(intAmt).add(pntAmt).add(dlyAmt).add(dlyPntAmt);
    }

    /**
     * @return {@link #txDate}
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * @param txDate
     *            {@link #txDate}
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * @return {@link #prnAmt}
     */
    public BigDecimal getPrnAmt() {
        return prnAmt;
    }

    /**
     * @param prnAmt
     *            {@link #prnAmt}
     */
    public void setPrnAmt(BigDecimal prnAmt) {
        this.prnAmt = prnAmt;
    }

    /**
     * @return {@link #intAmt}
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * @param intAmt
     *            {@link #intAmt}
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * @return {@link #pntAmt}
     */
    public BigDecimal getPntAmt() {
        return pntAmt;
    }

    /**
     * @param pntAmt
     *            {@link #pntAmt}
     */
    public void setPntAmt(BigDecimal pntAmt) {
        this.pntAmt = pntAmt;
    }

    /**
     * @return {@link #dlyPntAmt}
     */
    public BigDecimal getDlyPntAmt() {
        return dlyPntAmt;
    }

    /**
     * @param dlyPntAmt
     *            {@link #dlyPntAmt}
     */
    public void setDlyPntAmt(BigDecimal dlyPntAmt) {
        this.dlyPntAmt = dlyPntAmt;
    }

    /**
     * @return {@link #dlyAmt}
     */
    public BigDecimal getDlyAmt() {
        return dlyAmt;
    }

    /**
     * @param dlyAmt
     *            {@link #dlyAmt}
     */
    public void setDlyAmt(BigDecimal dlyAmt) {
        this.dlyAmt = dlyAmt;
    }

    /**
     * @return {@link #dtlAmt}
     */
    public BigDecimal getDtlAmt() {
        return dtlAmt;
    }

    /**
     * @param dtlAmt
     *            {@link #dtlAmt}
     */
    public void setDtlAmt(BigDecimal dtlAmt) {
        this.dtlAmt = dtlAmt;
    }

    /**
     * @return {@link #loanBal}
     */
    public BigDecimal getLoanBal() {
        return loanBal;
    }

    /**
     * @param loanBal
     *            {@link #loanBal}
     */
    public void setLoanBal(BigDecimal loanBal) {
        this.loanBal = loanBal;
    }

    /**
     * @return {@link #amount}
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            {@link #amount}
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return {@link #yrTerm}
     */
    public String getYrTerm() {
        return yrTerm;
    }

    /**
     * @param yrTerm
     *            {@link #yrTerm}
     */
    public void setYrTerm(String yrTerm) {
        this.yrTerm = yrTerm;
    }

    /**
     * @return {@link #memo}
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            {@link #memo}
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return {@link #postDate}
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * @param postDate
     *            {@link #postDate}
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * @return {@link #txCode}
     */
    public String getTxCode() {
        return txCode;
    }

    /**
     * @param txCode
     *            {@link #txCode}
     */
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    /**
     * @return {@link #tranCode}
     */
    public String getTranCode() {
        return tranCode;
    }

    /**
     * @param tranCode
     *            {@link #tranCode}
     */
    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    /**
     * @return {@link #payPrd}
     */
    public String getPayPrd() {
        return payPrd;
    }

    /**
     * @param payPrd
     *            {@link #payPrd}
     */
    public void setPayPrd(String payPrd) {
        this.payPrd = payPrd;
    }

    /**
     * @return {@link #cur}
     */
    public String getCur() {
        return cur;
    }

    /**
     * @param cur
     *            {@link #cur}
     */
    public void setCur(String cur) {
        this.cur = cur;
    }

    /**
     * @return {@link #totalPntAmt}
     */
    public BigDecimal getTotalPntAmt() {
        return totalPntAmt;
    }

    /**
     * @param totalPntAmt
     *            {@link #totalPntAmt}
     */
    public void setTotalPntAmt(BigDecimal totalPntAmt) {
        this.totalPntAmt = totalPntAmt;
    }

    /**
     * @return {@link #orderTxDate}
     */
    public Date getOrderTxDate() {
        return orderTxDate;
    }

    /**
     * @param orderTxDate
     *            {@link #orderTxDate}
     */
    public void setOrderTxDate(Date orderTxDate) {
        this.orderTxDate = orderTxDate;
    }

    /**
     * @return {@link #orderIndex}
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * @param orderIndex
     *            {@link #orderIndex}
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

}
