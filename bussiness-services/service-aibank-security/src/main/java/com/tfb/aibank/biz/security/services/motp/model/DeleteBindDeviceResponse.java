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

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)DeleteBindDeviceResponse.java
 * 
 * <p>Description:停用MOTP設備綁定 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "停用MOTP設備綁定 - Response")
public class DeleteBindDeviceResponse {

    /** 使用者未綁定 */
    @Schema(description = "使用者未綁定")
    private boolean userNotBind;

    /** 查詢使用者綁定狀態失敗 */
    @Schema(description = "查詢使用者綁定狀態失敗")
    private boolean getUserVaildTokenFail;

    /** 刪除OTP使用者失敗 */
    @Schema(description = "刪除OTP使用者失敗")
    private boolean deleteOtpUserFail;

    /** 錯誤代碼 */
    @Schema(description = "錯誤代碼")
    private String errorCode;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String errorMsg;

    /** 手機端用戶ID */
    @Schema(description = "手機端用戶ID")
    private String clientId;

    /**
     * @return the userNotBind
     */
    public boolean isUserNotBind() {
        return userNotBind;
    }

    /**
     * @param userNotBind
     *            the userNotBind to set
     */
    public void setUserNotBind(boolean userNotBind) {
        this.userNotBind = userNotBind;
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
     * @return the deleteOtpUserFail
     */
    public boolean isDeleteOtpUserFail() {
        return deleteOtpUserFail;
    }

    /**
     * @param deleteOtpUserFail
     *            the deleteOtpUserFail to set
     */
    public void setDeleteOtpUserFail(boolean deleteOtpUserFail) {
        this.deleteOtpUserFail = deleteOtpUserFail;
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
