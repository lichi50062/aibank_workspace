/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)TwoFactorAuthResponse.java
* 
* <p>Description: 雙重驗證登入API Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/26, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class TwoFactorAuthResponse {

    /**
     * 狀態
     */
    private String status;

    /**
     * 訊息
     */
    private String message;

    /**
     * 個人通知Key值
     */
    private Integer personNotificationRecordKey;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }

    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }

    @Override
    public String toString() {
        return "TwoFactorAuthResponse [status=" + status + ", message=" + message + ", personNotificationRecordKey=" + personNotificationRecordKey + "]";
    }

}
