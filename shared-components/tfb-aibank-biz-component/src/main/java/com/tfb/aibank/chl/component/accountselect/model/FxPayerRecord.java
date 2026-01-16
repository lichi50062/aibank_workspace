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

import java.io.Serializable;

// @formatter:off
/**
 * @(#)FxPayerRecord.java
 * 
 * <p>Description:『最近轉帳/常用/約定』帳號選擇元件(外幣) - 轉出</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxPayerRecord implements Serializable {

    private static final long serialVersionUID = 5730612836436016561L;

    /** 鍵值 */
    private String key;

    /** 轉出帳號 */
    private String payerAccount;

    /** 轉出帳號名稱 */
    private String payerAccountName;

    /** 數位存款註記 */
    private String digitalFlg;

    /** 帳號：顯示用，自行14碼、他行不進行處理 */
    private String displayAccountId;

    /** 是否為數位三類存款 */
    private boolean digital3Flg;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

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

    /**
     * @return the digitalFlg
     */
    public String getDigitalFlg() {
        return digitalFlg;
    }

    /**
     * @param digitalFlg
     *            the digitalFlg to set
     */
    public void setDigitalFlg(String digitalFlg) {
        this.digitalFlg = digitalFlg;
    }

    /**
     * @return {@link #displayAccountId}
     */
    public String getDisplayAccountId() {
        return displayAccountId;
    }

    /**
     * @param displayAccountId
     *            {@link #displayAccountId}
     */
    public void setDisplayAccountId(String displayAccountId) {
        this.displayAccountId = displayAccountId;
    }

    /**
     * @return {@link #digital3Flg}
     */
    public boolean isDigital3Flg() {
        return digital3Flg;
    }

    /**
     * @param digital3Flg
     *            {@link #digital3Flg}
     */
    public void setDigital3Flg(boolean digital3Flg) {
        this.digital3Flg = digital3Flg;
    }

}
