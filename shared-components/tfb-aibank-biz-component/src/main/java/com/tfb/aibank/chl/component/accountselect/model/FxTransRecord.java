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
 * @(#)FxTransRecord.java
 * 
 * <p>Description:『最近轉帳/常用/約定』帳號選擇元件(外幣) - 轉帳紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxTransRecord implements Serializable {

    private static final long serialVersionUID = -3728337209318614722L;

    /** 鍵值 */
    private String key;

    /** 轉出帳號 */
    private String payerAccount;

    /** 轉出幣別 */
    private String payerCur;

    /** 轉入行庫代碼 */
    private String payeeBankId;

    /** 轉入行庫名稱 */
    private String payeeBankName;

    /** 轉入帳號 */
    private String payeeAccount;

    /** 是否為約定帳號 */
    private boolean isDesignate;

    /** 是否為常用帳號 */
    private boolean isFavorite;

    /** 暱稱 */
    private String payeeNickname;

    /** 頭像角色 */
    private String payeeAvatarCharacter;

    /** 約定轉入帳號帳戶名稱 (約定轉入) */
    private String payeeAccountName;

    /** 帳號：顯示用，自行14碼、他行不進行處理 */
    private String displayPayerAccountId;

    /** 帳號：顯示用，自行14碼、他行不進行處理 */
    private String displayPayeeAccountId;

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
     * @return the isFavorite
     */
    public boolean isFavorite() {
        return isFavorite;
    }

    /**
     * @param isFavorite
     *            the isFavorite to set
     */
    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
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
     * @return {@link #displayPayerAccountId}
     */
    public String getDisplayPayerAccountId() {
        return displayPayerAccountId;
    }

    /**
     * @param displayPayerAccountId
     *            {@link #displayPayerAccountId}
     */
    public void setDisplayPayerAccountId(String displayPayerAccountId) {
        this.displayPayerAccountId = displayPayerAccountId;
    }

    /**
     * @return {@link #displayPayeeAccountId}
     */
    public String getDisplayPayeeAccountId() {
        return displayPayeeAccountId;
    }

    /**
     * @param displayPayeeAccountId
     *            {@link #displayPayeeAccountId}
     */
    public void setDisplayPayeeAccountId(String displayPayeeAccountId) {
        this.displayPayeeAccountId = displayPayeeAccountId;
    }

}
