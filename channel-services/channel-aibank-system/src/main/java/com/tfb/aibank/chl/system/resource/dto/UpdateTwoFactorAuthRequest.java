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
package com.tfb.aibank.chl.system.resource.dto;

//@formatter:off
/**
* @(#)UpdateTwoFactorAuthRequest.java
* 
* <p>Description: 更新雙重驗證記錄檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/27, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateTwoFactorAuthRequest {

    /**
     * 更新行為 ("", timeout, fail, success)
     */
    private String updateAction;

    /**
     * person notification record key (rowId)
     */
    private Integer personNotificationRecordKey;

    /**
     * 雙重驗證 AppInfo
     */
    private AppInfo appInfo;

    /**
     * 信任裝置
     */
    private boolean trustDevice;
    /**
     * 語系
     */
    private String locale;

    public String getUpdateAction() {
        return updateAction;
    }

    public void setUpdateAction(String updateAction) {
        this.updateAction = updateAction;
    }

    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }

    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public boolean isTrustDevice() {
        return trustDevice;
    }

    public void setTrustDevice(boolean trustDevice) {
        this.trustDevice = trustDevice;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
