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
package com.tfb.aibank.chl.component.favoriteaccount.model;

//@formatter:off
/**
* @(#)UpdateFavoriteAccountRequest.java
* 
* <p>Description:更新常用帳號 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateFavoriteAccountRequest {

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private int companyKind;

    /** 誤別碼 */
    private String uidDup;

    /** 用戶代碼 */
    private String nameCode;

    /** 轉出帳號 */
    private String payerAccount;

    /** 轉入帳號暱稱 */
    private String accountAlias;

    /** 轉入行庫(舊) */
    private String oldPayeeBank;

    /** 轉入帳號(舊) */
    private String oldPayeeAccount;

    /** 轉入行庫 */
    private String payeeBank;

    /** 轉入帳號 */
    private String payeeAccount;

    /** 轉帳通知email */
    private String notifyEmail;

    /** 頭像 */
    private String avatar;

    /** 約定 */
    private String isDesignate;

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the companyKind
     */
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
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
     * @return the accountAlias
     */
    public String getAccountAlias() {
        return accountAlias;
    }

    /**
     * @param accountAlias
     *            the accountAlias to set
     */
    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
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
     * @return the notifyEmail
     */
    public String getNotifyEmail() {
        return notifyEmail;
    }

    /**
     * @param notifyEmail
     *            the notifyEmail to set
     */
    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the isDesignate
     */
    public String getIsDesignate() {
        return isDesignate;
    }

    /**
     * @param isDesignate
     *            the isDesignate to set
     */
    public void setIsDesignate(String isDesignate) {
        this.isDesignate = isDesignate;
    }

    /**
     * @return the oldPayeeBank
     */
    public String getOldPayeeBank() {
        return oldPayeeBank;
    }

    /**
     * @param oldPayeeBank
     *            the oldPayeeBank to set
     */
    public void setOldPayeeBank(String oldPayeeBank) {
        this.oldPayeeBank = oldPayeeBank;
    }

    /**
     * @return the oldPayeeAccount
     */
    public String getOldPayeeAccount() {
        return oldPayeeAccount;
    }

    /**
     * @param oldPayeeAccount
     *            the oldPayeeAccount to set
     */
    public void setOldPayeeAccount(String oldPayeeAccount) {
        this.oldPayeeAccount = oldPayeeAccount;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

}
