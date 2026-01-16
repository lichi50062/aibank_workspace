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
package com.tfb.aibank.common.model;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)DepositOverview.java
 * 
 * <p>存款總覽-整合明細使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/17, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DepositOverview {

    /** 帳號 */
    private String accountNo;

    /** 產品 1.活存 2.定存 */
    private String product;

    /** 幣別 */
    private String currency;

    /** 存款餘額 */
    private BigDecimal actBal;

    /** 可用餘額 */
    private BigDecimal avlBal;

    /** 定存單號碼 */
    private String cdNo;

    /** 計息方式，1固定2機動 */
    private String interestType;

    /** 利率 */
    private BigDecimal interest;

    /** 定存到期日 */
    private Date cdEndDate;

    /**
     * @return {@link #accountNo}
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            {@link #accountNo}
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return {@link #product}
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product
     *            {@link #product}
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return {@link #currency}
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     *            {@link #currency}
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return {@link #actBal}
     */
    public BigDecimal getActBal() {
        return actBal;
    }

    /**
     * @param actBal
     *            {@link #actBal}
     */
    public void setActBal(BigDecimal actBal) {
        this.actBal = actBal;
    }

    /**
     * @return {@link #avlBal}
     */
    public BigDecimal getAvlBal() {
        return avlBal;
    }

    /**
     * @param avlBal
     *            {@link #avlBal}
     */
    public void setAvlBal(BigDecimal avlBal) {
        this.avlBal = avlBal;
    }

    /**
     * @return {@link #cdNo}
     */
    public String getCdNo() {
        return cdNo;
    }

    /**
     * @param cdNo
     *            {@link #cdNo}
     */
    public void setCdNo(String cdNo) {
        this.cdNo = cdNo;
    }

    /**
     * @return {@link #interestType}
     */
    public String getInterestType() {
        return interestType;
    }

    /**
     * @param interestType
     *            {@link #interestType}
     */
    public void setInterestType(String interestType) {
        this.interestType = interestType;
    }

    /**
     * @return {@link #interest}
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * @param interest
     *            {@link #interest}
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * @return {@link #cdEndDate}
     */
    public Date getCdEndDate() {
        return cdEndDate;
    }

    /**
     * @param cdEndDate
     *            {@link #cdEndDate}
     */
    public void setCdEndDate(Date cdEndDate) {
        this.cdEndDate = cdEndDate;
    }

}
