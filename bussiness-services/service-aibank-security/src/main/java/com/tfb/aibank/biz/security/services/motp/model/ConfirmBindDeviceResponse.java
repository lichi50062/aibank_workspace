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
 * @(#)ConfirmBindDeviceResponse.java
 * 
 * <p>Description:確認建立MOTP設備綁定 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "確認建立MOTP設備綁定 - Response")
public class ConfirmBindDeviceResponse {

    /** 註冊綁定資訊失敗 */
    @Schema(description = "註冊綁定資訊失敗")
    private boolean registerTokenFail;

    /** 錯誤代碼 */
    @Schema(description = "錯誤代碼")
    private String errorCode;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String errorMsg;

    /**
     * @return the registerTokenFail
     */
    public boolean isRegisterTokenFail() {
        return registerTokenFail;
    }

    /**
     * @param registerTokenFail
     *            the registerTokenFail to set
     */
    public void setRegisterTokenFail(boolean registerTokenFail) {
        this.registerTokenFail = registerTokenFail;
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

}
