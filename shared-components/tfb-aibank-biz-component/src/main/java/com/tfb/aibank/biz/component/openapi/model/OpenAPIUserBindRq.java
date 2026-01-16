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
package com.tfb.aibank.biz.component.openapi.model;

// @formatter:off
/**
 * @(#)OpenAPIUserQueryRq.java
 * 
 * <p>Description:註冊客戶綁定 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/20, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIUserBindRq implements OpenAPIRq {

    @Override
    public String getPath() {
        return "/user/bind";
    }

    /** (與手機門號不可同時空白）身分證字號，11碼，含誤別碼 */
    private String customerUid;

    /** (與身分證字不可同時空白）手機門號，10碼 */
    private String mobilePhone;

    /**
     * 帳戶類型 01：個人戶 02：企業戶 若已在自行有綁定，則回傳此欄位
     */
    private String accountType;

    /**
     * 銀行帳號 若已在自行有綁定，則回傳此欄位
     */
    private String bankAccount;

    /**
     * 是否在自行為預設收款帳戶 0：否 1：是 該手機門號已在他行預設綁定，則此欄位為0
     */
    private String isDefault;

    /**
     * @return the customerUid
     */
    public String getCustomerUid() {
        return customerUid;
    }

    /**
     * @param customerUid
     *            the customerUid to set
     */
    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     *            the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType
     *            the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the bankAccount
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount
     *            the bankAccount to set
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * @return the isDefault
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     *            the isDefault to set
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

}
