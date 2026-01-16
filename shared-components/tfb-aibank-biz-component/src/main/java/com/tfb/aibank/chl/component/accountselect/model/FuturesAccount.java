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
package com.tfb.aibank.chl.component.accountselect.model;

// @formatter:off
/**
 * @(#)FuturesAccount.java
 * 
 * <p>Description:期貨帳號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FuturesAccount {

    /** 期貨類別 */
    private String futuresType;

    /** 期貨商代號 */
    private String futuresCode;

    /** 期貨分公司 */
    private String futuresCompany;

    /** 投資人帳號 */
    private String userAcc;

    /** 幣別 */
    private String currency;

    /**
     * @return the futuresType
     */
    public String getFuturesType() {
        return futuresType;
    }

    /**
     * @param futuresType
     *            the futuresType to set
     */
    public void setFuturesType(String futuresType) {
        this.futuresType = futuresType;
    }

    /**
     * @return the futuresCode
     */
    public String getFuturesCode() {
        return futuresCode;
    }

    /**
     * @param futuresCode
     *            the futuresCode to set
     */
    public void setFuturesCode(String futuresCode) {
        this.futuresCode = futuresCode;
    }

    /**
     * @return the futuresCompany
     */
    public String getFuturesCompany() {
        return futuresCompany;
    }

    /**
     * @param futuresCompany
     *            the futuresCompany to set
     */
    public void setFuturesCompany(String futuresCompany) {
        this.futuresCompany = futuresCompany;
    }

    /**
     * @return the userAcc
     */
    public String getUserAcc() {
        return userAcc;
    }

    /**
     * @param userAcc
     *            the userAcc to set
     */
    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
