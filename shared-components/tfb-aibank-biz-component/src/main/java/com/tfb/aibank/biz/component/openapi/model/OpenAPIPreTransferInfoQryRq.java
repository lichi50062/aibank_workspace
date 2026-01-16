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
 * @(#)OpenAPIPreTransferInfoQryRq.java
 * 
 * <p>Description:轉帳交易前代理行查詢收款戶資訊 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OpenAPIPreTransferInfoQryRq implements OpenAPIRq {

    @Override
    public String getPath() {
        return "/user/preTransferInfoQry";
    }

    /**
     * （必填）收款方手機門號，10碼
     */
    private String mobilePhone;

    /**
     * （非必填）收款方金融機構代碼，3碼 因應財金門號轉帳平台API v1.0，此欄位目前請一律不傳即可
     */
    private String bankCode;

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

}
