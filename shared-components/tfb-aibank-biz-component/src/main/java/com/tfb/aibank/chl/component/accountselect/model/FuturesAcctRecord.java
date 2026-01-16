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
 * @(#)FuturesAcctRecord.java
 * 
 * <p>Description:『最近轉帳/常用/約定』帳號選擇元件(臺幣) - 期貨帳號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FuturesAcctRecord implements Serializable {

    private static final long serialVersionUID = -7259921483476466959L;

    /** 鍵值 */
    private String key;

    /** 轉入行庫代碼 */
    private String payeeBankId;

    /** 轉入行庫名稱 */
    private String payeeBankName;

    /** 轉入帳號 */
    private String payeeAccount;

    /** 暱稱 */
    private String payeeNickname;

    /** 頭像角色 */
    private String payeeAvatarCharacter;

    /** 帳號：顯示用，自行14碼、他行不進行處理 */
    private String displayAccountId;

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
     * @return the payeeBankId
     */
    public String getPayeeBankId() {
        return payeeBankId;
    }

    /**
     * @param payeeBankId
     *            the payeeBankId to set
     */
    public void setPayeeBankId(String payeeBankId) {
        this.payeeBankId = payeeBankId;
    }

    /**
     * @return the payeeBankName
     */
    public String getPayeeBankName() {
        return payeeBankName;
    }

    /**
     * @param payeeBankName
     *            the payeeBankName to set
     */
    public void setPayeeBankName(String payeeBankName) {
        this.payeeBankName = payeeBankName;
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
     * @return the payeeNickname
     */
    public String getPayeeNickname() {
        return payeeNickname;
    }

    /**
     * @param payeeNickname
     *            the payeeNickname to set
     */
    public void setPayeeNickname(String payeeNickname) {
        this.payeeNickname = payeeNickname;
    }

    /**
     * @return the payeeAvatarCharacter
     */
    public String getPayeeAvatarCharacter() {
        return payeeAvatarCharacter;
    }

    /**
     * @param payeeAvatarCharacter
     *            the payeeAvatarCharacter to set
     */
    public void setPayeeAvatarCharacter(String payeeAvatarCharacter) {
        this.payeeAvatarCharacter = payeeAvatarCharacter;
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

}
