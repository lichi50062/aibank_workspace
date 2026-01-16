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
package com.tfb.aibank.biz.user.services.nopain.model;

// @formatter:off
/**
 * @(#)AuthorizeProfileResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/31, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AuthorizeProfileResponse {

    // 回應代碼
    private String status;

    // 回應訊息
    private String message;

    // 回應資料
    private MbLoginModel data;

    // 錯誤資訊物件
    private Object errorData;

    // 回應時間
    private String time;

    // 回應結果
    private boolean success;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public MbLoginModel getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(MbLoginModel data) {
        this.data = data;
    }

    /**
     * @return the errorData
     */
    public Object getErrorData() {
        return errorData;
    }

    /**
     * @param errorData
     *            the errorData to set
     */
    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     *            the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
