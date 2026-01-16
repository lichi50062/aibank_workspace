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

import java.util.List;

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
public class NCCQU008InstallBillCardVo {

    /** 帳單年份 */
    private Integer billYear;

    /** 剩餘分期牌卡 by 年份 */
    private List<NCCQU008InstallBillCardByYearVo> billCardByYear;

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

    /**
     * @return the billCardByYear
     */
    public List<NCCQU008InstallBillCardByYearVo> getBillCardByYear() {
        return billCardByYear;
    }

    /**
     * @param billCardByYear
     *            the billCardByYear to set
     */
    public void setBillCardByYear(List<NCCQU008InstallBillCardByYearVo> billCardByYear) {
        this.billCardByYear = billCardByYear;
    }

}
