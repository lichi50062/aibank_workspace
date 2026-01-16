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
package com.tfb.aibank.chl.creditcard.qu008.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQU008MonthlyEstimateDetail {

    /**
     * yyyy/MM
     */
    private String billYearMonth;

    /**
     * 月總和
     */
    private BigDecimal monthTotal;

    /**
     * 月總和顯示用
     */
    private String monthTotalDisplay;

    /**
     * 單筆消費分期
     */
    private BigDecimal unbillAmt;

    /**
     * 單筆消費分期顯示
     */
    private String unbillAmtDisplay;

    /**
     * 帳單分期
     */
    private BigDecimal billAmt;
    
    /**
     * 帳單分期顯示用
     */
    private String billAmtDisplay;

    public String getBillYearMonth() {
        return billYearMonth;
    }

    public void setBillYearMonth(String billYearMonth) {
        this.billYearMonth = billYearMonth;
    }

    public BigDecimal getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(BigDecimal monthTotal) {
        this.monthTotal = monthTotal;
    }

    public String getMonthTotalDisplay() {
        return monthTotalDisplay;
    }

    public void setMonthTotalDisplay(String monthTotalDisplay) {
        this.monthTotalDisplay = monthTotalDisplay;
    }

    public BigDecimal getUnbillAmt() {
        return unbillAmt;
    }

    public void setUnbillAmt(BigDecimal unbillAmt) {
        this.unbillAmt = unbillAmt;
    }

    public String getUnbillAmtDisplay() {
        return unbillAmtDisplay;
    }

    public void setUnbillAmtDisplay(String unbillAmtDisplay) {
        this.unbillAmtDisplay = unbillAmtDisplay;
    }

    public BigDecimal getBillAmt() {
        return billAmt;
    }

    public void setBillAmt(BigDecimal billAmt) {
        this.billAmt = billAmt;
    }

    public String getBillAmtDisplay() {
        return billAmtDisplay;
    }

    public void setBillAmtDisplay(String billAmtDisplay) {
        this.billAmtDisplay = billAmtDisplay;
    }
}
