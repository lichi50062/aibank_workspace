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
 * @(#)OpenAPIUserQueryRs.java
 * 
 * <p>Description:查詢客戶綁定 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIUserQueryRs implements OpenAPIRs {

    /**
     * 交易結果代碼
     */
    private String code;

    /**
     * 綁定時間，yyyy/MM/DD HH:mm:ss 若已在自行有綁定，則回傳此欄位
     */
    private String updateTime;

    /**
     * 身分證字號，11碼，含誤別碼 若已在自行有綁定，則回傳此欄位
     */
    private String customerUid;

    /**
     * 手機門號，10碼 若已在自行有綁定，則回傳此欄位
     */
    private String mobilePhone;

    /**
     * 帳戶名，最多40字 若已在自行有綁定，則回傳此欄位
     */
    private String accountName;

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
     * 預設綁定金融機構代碼 該手機門號已在自行或他行預設綁定，則此欄位將回傳
     */
    private String bankCode;

    /**
     * 交易通路ID 定義如下： FISC：財金 ROE_Client：ESB MOBBANK：行銀 BK_CTI：CTI BANK_ETABS：端末 TCS：主機
     */
    private String clientId;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

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
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     *            the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * @param bankCode
     *            the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
