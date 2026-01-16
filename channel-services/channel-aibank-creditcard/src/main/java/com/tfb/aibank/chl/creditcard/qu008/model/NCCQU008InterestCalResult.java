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

// @formatter:off
/**
 * @(#)NCCQU008InterestCalResult.java
 * 
 * <p>Description:利息計算結果Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008InterestCalResult {
    /** 分期期數 */
    private String payTimes;

    /** 本金 */
    private String captial;

    /** 本金 */
    private BigDecimal captialOrigin;

    /** 利息 */
    private String interest;

    /** 利息 */
    private BigDecimal interestOrigin;

    /** 合計 */
    private String total;

    /**
     * @return the payTimes
     */
    public String getPayTimes() {
        return payTimes;
    }

    /**
     * @param payTimes
     *            the payTimes to set
     */
    public void setPayTimes(String payTimes) {
        this.payTimes = payTimes;
    }

    /**
     * @return the captial
     */
    public String getCaptial() {
        return captial;
    }

    /**
     * @param captial
     *            the captial to set
     */
    public void setCaptial(String captial) {
        this.captial = captial;
    }

    /**
     * @return the interest
     */
    public String getInterest() {
        return interest;
    }

    /**
     * @param interest
     *            the interest to set
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total
     *            the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the captialOrigin
     */
    public BigDecimal getCaptialOrigin() {
        return captialOrigin;
    }

    /**
     * @param captialOrigin
     *            the captialOrigin to set
     */
    public void setCaptialOrigin(BigDecimal captialOrigin) {
        this.captialOrigin = captialOrigin;
    }

    /**
     * @return the interestOrigin
     */
    public BigDecimal getInterestOrigin() {
        return interestOrigin;
    }

    /**
     * @param interestOrigin
     *            the interestOrigin to set
     */
    public void setInterestOrigin(BigDecimal interestOrigin) {
        this.interestOrigin = interestOrigin;
    }

}
