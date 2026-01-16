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
package com.tfb.aibank.chl.component.loan;

import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.model.TxHeadRs;

// @formatter:off
/**
 * @(#)EB362601Res.java
 * 
 * <p>Description:還本繳息查詢 (公教房貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB362601Res extends TxHeadRs {

    private static final long serialVersionUID = 1L;
    /** 公教帳號 */
    private String acnoOt;
    /** 統一編號 */
    private String custNo;
    /** 戶名 */
    private String custName;
    /** 代繳機關代號 */
    private String grpOtAcno;
    /** 代繳機關戶名 */
    private String grpOtName;
    /** 代繳機關備註 */
    private String rmkAcnoOt;
    /** 預定還本金額 */
    private String prnAmt;
    /** 交易日 */
    private Date txDate;
    /** 貸款餘款 */
    private String bLoanAbl;
    /** 計息起日 */
    private Date dtlSdate;
    /** 計息迄日 */
    private Date dtlEdate;
    /** 每期本息 */
    private BigDecimal bInsAmt;
    /** 計息利率 */
    private String intRate;
    /** 應收本金 */
    private BigDecimal arPrnAmt;
    /** 應收利息 */
    private BigDecimal arIrnAmt;
    /** 應收遲延息 */
    private BigDecimal arDlyAmt;
    /** 應收違約金 */
    private BigDecimal arPntAmt;
    /** 應收合計 */
    private BigDecimal arTotAmt;
    /** 清償後餘額 */
    private String loanBal;
    /** 清償後每期本息 */
    private BigDecimal insAmt;

    public String getAcnoOt() {
        return acnoOt;
    }

    public void setAcnoOt(String acnoOt) {
        this.acnoOt = acnoOt;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getGrpOtAcno() {
        return grpOtAcno;
    }

    public void setGrpOtAcno(String grpOtAcno) {
        this.grpOtAcno = grpOtAcno;
    }

    public String getGrpOtName() {
        return grpOtName;
    }

    public void setGrpOtName(String grpOtName) {
        this.grpOtName = grpOtName;
    }

    public String getRmkAcnoOt() {
        return rmkAcnoOt;
    }

    public void setRmkAcnoOt(String rmkAcnoOt) {
        this.rmkAcnoOt = rmkAcnoOt;
    }

    public String getPrnAmt() {
        return prnAmt;
    }

    public void setPrnAmt(String prnAmt) {
        this.prnAmt = prnAmt;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getbLoanAbl() {
        return bLoanAbl;
    }

    public void setbLoanAbl(String bLoanAbl) {
        this.bLoanAbl = bLoanAbl;
    }

    public Date getDtlSdate() {
        return dtlSdate;
    }

    public void setDtlSdate(Date dtlSdate) {
        this.dtlSdate = dtlSdate;
    }

    public Date getDtlEdate() {
        return dtlEdate;
    }

    public void setDtlEdate(Date dtlEdate) {
        this.dtlEdate = dtlEdate;
    }

    public BigDecimal getbInsAmt() {
        return bInsAmt;
    }

    public void setbInsAmt(BigDecimal bInsAmt) {
        this.bInsAmt = bInsAmt;
    }

    public String getIntRate() {
        return intRate;
    }

    public void setIntRate(String intRate) {
        this.intRate = intRate;
    }

    public BigDecimal getArPrnAmt() {
        return arPrnAmt;
    }

    public void setArPrnAmt(BigDecimal arPrnAmt) {
        this.arPrnAmt = arPrnAmt;
    }

    public BigDecimal getArIrnAmt() {
        return arIrnAmt;
    }

    public void setArIrnAmt(BigDecimal arIrnAmt) {
        this.arIrnAmt = arIrnAmt;
    }

    public BigDecimal getArDlyAmt() {
        return arDlyAmt;
    }

    public void setArDlyAmt(BigDecimal arDlyAmt) {
        this.arDlyAmt = arDlyAmt;
    }

    public BigDecimal getArPntAmt() {
        return arPntAmt;
    }

    public void setArPntAmt(BigDecimal arPntAmt) {
        this.arPntAmt = arPntAmt;
    }

    public BigDecimal getArTotAmt() {
        return arTotAmt;
    }

    public void setArTotAmt(BigDecimal arTotAmt) {
        this.arTotAmt = arTotAmt;
    }

    public String getLoanBal() {
        return loanBal;
    }

    public void setLoanBal(String loanBal) {
        this.loanBal = loanBal;
    }

    public BigDecimal getInsAmt() {
        return insAmt;
    }

    public void setInsAmt(BigDecimal insAmt) {
        this.insAmt = insAmt;
    }
}
