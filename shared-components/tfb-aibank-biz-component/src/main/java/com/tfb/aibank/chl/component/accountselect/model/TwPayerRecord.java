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

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.model.account.TransOutAccount;

// @formatter:off
/**
 * @(#)PayerRecord.java
 * 
 * <p>Description:『最近轉帳/常用/約定』帳號選擇元件(臺幣) - 轉出</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwPayerRecord implements Serializable {

    private static final long serialVersionUID = 3632363433437057778L;

    private static final String PAYER_KEY_PREFIX = "TW_PAYER_%s_%s";

    public TwPayerRecord() {
        // default constructor
    }

    public TwPayerRecord(TransOutAccount account, Integer index) {
        account.prepareAccountInfo(account);
        this.key = String.format(PAYER_KEY_PREFIX, account.getAccountId(), index);
        this.payerAccount = account.getAccountId();
        this.payerAccountName = account.getDisplayAcctNickname();
        this.balance = "$" + StringUtils.getMoneyStr(ConvertUtils.bigDecimal2Str(account.getAvalAmt()));
        this.digitalFlg = account.isDigital() ? StringUtils.Y : StringUtils.N;
        this.digital3Flg = account.isDigital3();
        this.displayAccountId = account.getDisplayAccountId();
        this.branchName = account.getBranchName();
    }

    /** 鍵值 */
    private String key;

    /** 轉出帳號 */
    private String payerAccount;

    /** 轉出帳號名稱 */
    private String payerAccountName;

    /** 餘額 */
    private String balance;

    /** 數位存款註記 */
    private String digitalFlg;

    /** 帳號：顯示用，自行14碼、他行不進行處理 */
    private String displayAccountId;

    /** 是否為數位三類存款 */
    private boolean digital3Flg;

    /** 分行名稱 */
    private String branchName;

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
     * @return the balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * @param balance
     *            the balance to set
     */
    public void setBalance(String balance) {
        this.balance = balance;
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

    /**
     * @return {@link #branchName}
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName
     *            {@link #branchName}
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
