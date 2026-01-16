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
 * @(#)RecentlyFxTransferRecord.java
 * 
 * <p>Description:最近轉帳紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/23, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RecentlyFxTransferRecord {

    /** 轉出帳號 */
    private String payerAccount;

    /** 轉出幣別 */
    private String payerCur;

    /** 轉入帳號 */
    private String payeeAccount;

    /**
     * @return the payerAccount
     */
    public String getPayerAccount() {
        return payerAccount;
    }

    /**
     * @param payerAccount
     *            the payerAccount to set
     */
    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    /**
     * @return the payerCur
     */
    public String getPayerCur() {
        return payerCur;
    }

    /**
     * @param payerCur
     *            the payerCur to set
     */
    public void setPayerCur(String payerCur) {
        this.payerCur = payerCur;
    }

    /**
     * @return the payeeAccount
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     * @param payeeAccount
     *            the payeeAccount to set
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

}
