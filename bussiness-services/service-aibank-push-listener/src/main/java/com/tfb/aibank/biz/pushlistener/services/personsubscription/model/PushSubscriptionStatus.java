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
package com.tfb.aibank.biz.pushlistener.services.personsubscription.model;

// @formatter:off
/**
 * @(#)SubscriptionStatus.java
 * 
 * <p>Description:推播訂閱狀態</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushSubscriptionStatus {

    /** 公司鍵值 */
    private Integer companyKey;

    /** 公司類型 */
    private Integer companyKind;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 推播代碼 */
    private String pushCode;

    /** 訊息設定 0:未開啟訊息通知(全天不可發送) 1:已開啟訊息通知(全天可發送) 2:夜間勿擾(2200~0800不發送) */
    private Integer notifyPass;

    /** 行動裝置UUID */
    private String deviceUuId;

    /** 是否授權訊息通知，1:已授權；0或空白:未授權 */
    private Integer notifyFlag;

    /** 是否裝置已綁定 */
    private boolean isDeviceBound;

    /** 是否開啟推播通知 */
    private boolean isEnable;

    /** 是否訂閱推播通知 */
    private boolean isSubscribe;

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
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

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public void setSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public Integer getNotifyPass() {
        return notifyPass;
    }

    public void setNotifyPass(Integer notifyPass) {
        this.notifyPass = notifyPass;
    }

    public String getDeviceUuId() {
        return deviceUuId;
    }

    public void setDeviceUuId(String deviceUuId) {
        this.deviceUuId = deviceUuId;
    }

    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    public boolean isDeviceBound() {
        return isDeviceBound;
    }

    public void setDeviceBound(boolean isDeviceBound) {
        this.isDeviceBound = isDeviceBound;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

}
