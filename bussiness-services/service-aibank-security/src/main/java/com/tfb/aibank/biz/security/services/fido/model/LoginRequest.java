/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class LoginRequest {

    /**
     * 使用者ID
     */
    @Schema(description = "使用者ID")
    private String custId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /**
     * 設備品牌
     */
    @Schema(description = "設備品牌")
    private String deviceManufacturer;

    /**
     * 設備品牌
     */
    @Schema(description = "設備品牌")
    private String deviceModel;

    /**
     * 動作 FIDO:註冊 SIGN:驗證
     * 
     */
    @Schema(description = "動作")
    private String action;

    /**
     * 裝置編號
     */
    @Schema(description = "裝置編號")
    private String deviceId;

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
     * @return the deviceManufacturer
     */
    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    /**
     * @param deviceManufacturer
     *            the deviceManufacturer to set
     */
    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    /**
     * @return the deviceModel
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     *            the deviceModel to set
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
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
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
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
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
