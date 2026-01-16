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

// @formatter:off
/**
 * @(#)NCCQU008InstallBillCardVo.java
 * 
 * <p>Description:剩餘分期牌卡Vo</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008InstallBillCardByYearVo {

    /** 應繳金額 */
    private String billAmt;

    /** 帳單月份 */
    private Integer billMonth;

    /** 帳單年份 */
    private Integer billYear;

    /**
     * @return the billAmt
     */
    public String getBillAmt() {
        return billAmt;
    }

    /**
     * @param billAmt
     *            the billAmt to set
     */
    public void setBillAmt(String billAmt) {
        this.billAmt = billAmt;
    }

    /**
     * @return the billMonth
     */
    public Integer getBillMonth() {
        return billMonth;
    }

    /**
     * @param billMonth
     *            the billMonth to set
     */
    public void setBillMonth(Integer billMonth) {
        this.billMonth = billMonth;
    }

    /**
     * @return the billYear
     */
    public Integer getBillYear() {
        return billYear;
    }

    /**
     * @param billYear
     *            the billYear to set
     */
    public void setBillYear(Integer billYear) {
        this.billYear = billYear;
    }

}
