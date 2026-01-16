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
package com.tfb.aibank.biz.security.services.motp.model;

import com.tfb.aibank.biz.security.services.motp.helper.model.DeviceModel;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CreateBindDeviceResponse.java
 * 
 * <p>Description:建立MOTP設備綁定 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "建立MOTP設備綁定 - Response")
public class CreateBindDeviceResponse {

    /** 使用者已綁定 */
    @Schema(description = "使用者已綁定")
    private boolean userBind;

    /** 查詢使用者綁定狀態失敗 */
    @Schema(description = "查詢使用者綁定狀態失敗")
    private boolean getUserVaildTokenFail;

    /** 建立MOTP綁定帳戶失敗 */
    @Schema(description = "建立MOTP綁定帳戶失敗")
    private boolean createOtpUserFail;

    /** 取得帳戶綁定資訊失敗 */
    @Schema(description = "取得帳戶綁定資訊失敗")
    private boolean initPushFail;

    /** 錯誤代碼 */
    @Schema(description = "錯誤代碼")
    private String errorCode;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String errorMsg;

    /** 初始金鑰 */
    @Schema(description = "初始金鑰")
    private String ik;

    /** 加密資料之金鑰 */
    @Schema(description = "加密資料之金鑰")
    private String pushKey;

    /** 保留欄位 */
    @Schema(description = "保留欄位")
    private String flag;

    /** App profile類型 */
    @Schema(description = "App profile類型")
    private String type;

    /** 使用者名稱 */
    @Schema(description = "使用者名稱")
    private String account;

    /** MOTP裝置綁定資訊鍵值 */
    @Schema(description = "MOTP裝置綁定資訊鍵值")
    private Integer motpDeviceKey;

    /** 行動裝置型號對應表資料 */
    @Schema(description = "行動裝置型號對應表資料")
    private DeviceModel deviceModel;

    /**
     * @return the userBind
     */
    public boolean isUserBind() {
        return userBind;
    }

    /**
     * @param userBind
     *            the userBind to set
     */
    public void setUserBind(boolean userBind) {
        this.userBind = userBind;
    }

    /**
     * @return the getUserVaildTokenFail
     */
    public boolean isGetUserVaildTokenFail() {
        return getUserVaildTokenFail;
    }

    /**
     * @param getUserVaildTokenFail
     *            the getUserVaildTokenFail to set
     */
    public void setGetUserVaildTokenFail(boolean getUserVaildTokenFail) {
        this.getUserVaildTokenFail = getUserVaildTokenFail;
    }

    /**
     * @return the createOtpUserFail
     */
    public boolean isCreateOtpUserFail() {
        return createOtpUserFail;
    }

    /**
     * @param createOtpUserFail
     *            the createOtpUserFail to set
     */
    public void setCreateOtpUserFail(boolean createOtpUserFail) {
        this.createOtpUserFail = createOtpUserFail;
    }

    /**
     * @return the initPushFail
     */
    public boolean isInitPushFail() {
        return initPushFail;
    }

    /**
     * @param initPushFail
     *            the initPushFail to set
     */
    public void setInitPushFail(boolean initPushFail) {
        this.initPushFail = initPushFail;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * @return the ik
     */
    public String getIk() {
        return ik;
    }

    /**
     * @param ik
     *            the ik to set
     */
    public void setIk(String ik) {
        this.ik = ik;
    }

    /**
     * @return the pushKey
     */
    public String getPushKey() {
        return pushKey;
    }

    /**
     * @param pushKey
     *            the pushKey to set
     */
    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the motpDeviceKey
     */
    public Integer getMotpDeviceKey() {
        return motpDeviceKey;
    }

    /**
     * @param motpDeviceKey
     *            the motpDeviceKey to set
     */
    public void setMotpDeviceKey(Integer motpDeviceKey) {
        this.motpDeviceKey = motpDeviceKey;
    }

    /**
     * @return the deviceModel
     */
    public DeviceModel getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     *            the deviceModel to set
     */
    public void setDeviceModel(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

}
