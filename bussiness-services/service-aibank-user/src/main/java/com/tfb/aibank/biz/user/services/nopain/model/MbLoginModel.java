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
 * @(#)MbLoginModel.java
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
public class MbLoginModel {

    // 身分證字號
    private String uid;

    // 誤別碼
    private String uidDup;

    // 使用者代碼
    private String uuid;

    // 戶名代碼
    private String nameCode;

    // 使用者類型
    private String companyKind;

    // 公司鍵值
    private String companyKey;

    // 使用者鍵值
    private String userKey;

    // 生日
    private String birthday;

    // 會員類別
    private String loginType;

    // 登入方式
    private String loginMethod;

    // 登入下行
    private String loginData;

    // 目前裝置綁定資訊
    private String deviceBindingInfo;

    // 目前裝置訂閱推播資訊
    private String deviceSubPushCode;

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
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
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the companyKind
     */
    public String getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(String companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the companyKey
     */
    public String getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the loginMethod
     */
    public String getLoginMethod() {
        return loginMethod;
    }

    /**
     * @param loginMethod
     *            the loginMethod to set
     */
    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod;
    }

    /**
     * @return the loginData
     */
    public String getLoginData() {
        return loginData;
    }

    /**
     * @param loginData
     *            the loginData to set
     */
    public void setLoginData(String loginData) {
        this.loginData = loginData;
    }

    /**
     * @return the deviceBindingInfo
     */
    public String getDeviceBindingInfo() {
        return deviceBindingInfo;
    }

    /**
     * @param deviceBindingInfo
     *            the deviceBindingInfo to set
     */
    public void setDeviceBindingInfo(String deviceBindingInfo) {
        this.deviceBindingInfo = deviceBindingInfo;
    }

    /**
     * @return the deviceSubPushCode
     */
    public String getDeviceSubPushCode() {
        return deviceSubPushCode;
    }

    /**
     * @param deviceSubPushCode
     *            the deviceSubPushCode to set
     */
    public void setDeviceSubPushCode(String deviceSubPushCode) {
        this.deviceSubPushCode = deviceSubPushCode;
    }

}
