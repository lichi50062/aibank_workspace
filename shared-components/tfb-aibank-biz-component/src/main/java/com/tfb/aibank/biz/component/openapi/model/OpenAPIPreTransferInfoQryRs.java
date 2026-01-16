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
 * @(#)OpenAPIPreTransferInfoQryRs.java
 * 
 * <p>Description:轉帳交易前代理行查詢收款戶資訊 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIPreTransferInfoQryRs implements OpenAPIRs {

    /**
     * 交易結果代碼
     */
    private String code;

    /**
     * 收款方金融機構代碼
     */
    private String bankCode;

    /**
     * 收款戶類型 01：個人收款戶 02：企業收款戶
     */
    private String accountType;

    /**
     * 收款戶名/企業戶名 （遮罩）
     */
    private String accountName;

    /**
     * 銀行帳號 若轉入對象為自行，則回傳此欄位
     */
    private String bankAccount;

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

}
