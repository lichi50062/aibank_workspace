package com.tfb.aibank.chl.component.userdatacache.model;

// @formatter:off
/**
 * @(#)RemoveUserFidoResponse.java
 * 
 * <p>Description:${Description}</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/7/4, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RemoveUserFido2Response {

    /**
     * 狀態碼
     */
    private String status;

    /**
     * 錯誤訊息
     */
    private String errorMessage;

    /**
     * 時間逾時
     */
    private int timeout;

    /**
     * 回傳碼
     */
    private String returnCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
