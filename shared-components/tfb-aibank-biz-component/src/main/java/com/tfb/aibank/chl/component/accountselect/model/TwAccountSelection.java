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
 * @(#)TwAccountSelection.java
 * 
 * <p>Description:帳號選擇結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/13, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwAccountSelection {

    /** 轉出帳號 */
    private String payerAccount;

    /** 轉出帳號名稱 */
    private String payerAccountName;

    /** 轉入銀行 */
    private String payeeBank;

    /** 轉入帳號 */
    private String payeeAccount;

    /** 是否為約定帳號 */
    private boolean isDesignate;

    /** 約定轉入帳號帳戶名稱 */
    private String payeeAccountName;

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
     * @return the payeeBank
     */
    public String getPayeeBank() {
        return payeeBank;
    }

    /**
     * @param payeeBank
     *            the payeeBank to set
     */
    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
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

    /**
     * @return the isDesignate
     */
    public boolean isDesignate() {
        return isDesignate;
    }

    /**
     * @param isDesignate
     *            the isDesignate to set
     */
    public void setDesignate(boolean isDesignate) {
        this.isDesignate = isDesignate;
    }

    /**
     * @return the payeeAccountName
     */
    public String getPayeeAccountName() {
        return payeeAccountName;
    }

    /**
     * @param payeeAccountName
     *            the payeeAccountName to set
     */
    public void setPayeeAccountName(String payeeAccountName) {
        this.payeeAccountName = payeeAccountName;
    }

    /**
     * @return the payerAccountName
     */
    public String getPayerAccountName() {
        return payerAccountName;
    }

    /**
     * @param payerAccountName
     *            the payerAccountName to set
     */
    public void setPayerAccountName(String payerAccountName) {
        this.payerAccountName = payerAccountName;
    }

}
