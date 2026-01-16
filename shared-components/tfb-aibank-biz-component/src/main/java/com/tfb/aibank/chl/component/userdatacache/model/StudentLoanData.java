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

// @formatter:off
/**
 * @(#)StudentLoanData.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/05, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class StudentLoanData {

    /** 學年度-學期 */
    private String yearTerm;

    /** 幣別 */
    private String curCod;

    /** 原貸款金額 */
    private BigDecimal oriLoanBal;

    /** 帳上餘額（原幣） */
    private BigDecimal actBal;

    /** 每期期金 */
    private BigDecimal insAmt;

    /**
     * @return the yearTerm
     */
    public String getYearTerm() {
        return yearTerm;
    }

    /**
     * @param yearTerm
     *            the yearTerm to set
     */
    public void setYearTerm(String yearTerm) {
        this.yearTerm = yearTerm;
    }

    /**
     * @return the curCod
     */
    public String getCurCod() {
        return curCod;
    }

    /**
     * @param curCod
     *            the curCod to set
     */
    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    /**
     * @return the oriLoanBal
     */
    public BigDecimal getOriLoanBal() {
        return oriLoanBal;
    }

    /**
     * @param oriLoanBal
     *            the oriLoanBal to set
     */
    public void setOriLoanBal(BigDecimal oriLoanBal) {
        this.oriLoanBal = oriLoanBal;
    }

    /**
     * @return the actBal
     */
    public BigDecimal getActBal() {
        return actBal;
    }

    /**
     * @param actBal
     *            the actBal to set
     */
    public void setActBal(BigDecimal actBal) {
        this.actBal = actBal;
    }

    /**
     * @return the insAmt
     */
    public BigDecimal getInsAmt() {
        return insAmt;
    }

    /**
     * @param insAmt
     *            the insAmt to set
     */
    public void setInsAmt(BigDecimal insAmt) {
        this.insAmt = insAmt;
    }

}
