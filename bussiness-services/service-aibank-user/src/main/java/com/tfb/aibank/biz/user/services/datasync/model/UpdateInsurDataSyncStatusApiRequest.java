/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.datasync.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UpdateInsurDataSyncStatusApiRequest.java
* 
* <p>Description: 富邦人壽彙整狀態上行request</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "富邦人壽彙整狀態上行request")
public class UpdateInsurDataSyncStatusApiRequest {

    /**
     * 來源系統代碼
     */
    @Schema(description = "來源系統代碼")
    private String sysId;

    /**
     * 源交易編號
     */
    @Schema(description = "源交易編號")
    private String sysCaseNo;

    /**
     * 驗證碼
     */
    @Schema(description = "驗證碼")
    private String authToken;

    /**
     * 客戶生日
     */
    @Schema(description = "客戶生日")
    private String custId;

    /**
     * 客戶生日
     */
    @Schema(description = "客戶生日")
    private String custBirth;

    /**
     * 異動情境
     */
    @Schema(description = "異動情境")
    private String updType;

    /**
     * 同意/不同意資料彙整
     */
    @Schema(description = "同意/不同意資料彙整")
    private String isAgree;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getSysCaseNo() {
        return sysCaseNo;
    }

    public void setSysCaseNo(String sysCaseNo) {
        this.sysCaseNo = sysCaseNo;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustBirth() {
        return custBirth;
    }

    public void setCustBirth(String custBirth) {
        this.custBirth = custBirth;
    }

    public String getUpdType() {
        return updType;
    }

    public void setUpdType(String updType) {
        this.updType = updType;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }
}
