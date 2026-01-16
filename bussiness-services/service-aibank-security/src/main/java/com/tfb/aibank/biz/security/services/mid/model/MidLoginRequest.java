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
package com.tfb.aibank.biz.security.services.mid.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)MidLoginRequest.java
 * 
 * <p>Description:台網MID驗證 - Login - Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "台網MID驗證 - Login - Request")
public class MidLoginRequest {

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private int companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 裝置ID */
    @Schema(description = "裝置ID")
    private String deviceIxd;

    /** 行動裝置鍵值 */
    @Schema(description = "行動裝置鍵值")
    private Integer deviceInfoKey;

    /** 認證電話號碼 */
    @Schema(description = "認證電話號碼")
    private String mobileNo;

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

    /**
     * @return the deviceIxd
     */
    public String getDeviceIxd() {
        return deviceIxd;
    }

    /**
     * @param deviceIxd
     *            the deviceIxd to set
     */
    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    /**
     * @return the deviceInfoKey
     */
    public Integer getDeviceInfoKey() {
        return deviceInfoKey;
    }

    /**
     * @param deviceInfoKey
     *            the deviceInfoKey to set
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
