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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)MbDeviceInfoModel.java
* 
* <p>Description:行動裝置設定檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChans
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MbDeviceInfo {

    /**
     * 行動裝置設定檔鍵值
     */
    private Integer deviceInfoKey;

    /**
     * 行動裝置UUID
     */
    private String deviceUuId;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 裝置資訊
     */
    private String model;

    /**
     * 裝置作業系統
     */
    private String devicePlatform;

    /**
     * 裝置作業系統版本
     */
    private String devicePlatformVersion;

    /**
     * 裝置暱稱
     */
    private String deviceAlias;

    /**
     * 是否授權簡易登入，1:已授權；0或空白:未授權
     */
    private Integer loginFlag;

    /**
     * 簡易登入授權日期
     */
    private Date loginAuthDate;

    /**
     * 是否授權訊息通知，1:已授權；0或空白:未授權
     */
    private Integer notifyFlag;

    /**
     * 訊息通知授權日期
     */
    private Date notifyAuthDate;

    /**
     * 通知設定，0:未設定(全天可發送)；1:全天不發送；2:21:00~09:00不發送
     */
    private Integer notifyPass;

    /**
     * 來源IP
     */
    private String clientIp;

    /**
     * 是否授權訊息密碼設定，1:已授權；0或空白:未授權
     */
    private Integer msgFlag;

    /**
     * 重要訊息設定密碼
     */
    private String msgPassword;

    /**
     * 是否啟用，0:不啟用；1:啟用
     */
    private Integer enable;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 登入身份別，0:一般會員登入；1:信用卡會員登入
     */
    private Integer loginType;

    /**
     * 訂閱電文異常註記，1:EB552157，2:CEW323:2；0:無
     */
    private Integer errorFlag;

    /**
     * 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識
     */
    private Integer loginPasswdType;

    /**
     * 免登速查註記，0:不啟用；1:啟用
     */
    private Integer qsearchFlag;

    /**
     * 是否變更過密碼/代碼 1:是 0:否
     */
    private Integer chgPwdUseridFlag;

    /**
     * 變更密碼時間
     */
    private Date chgPwdUseridDate;

    /**
     * 申請MOTP註記，0:未授權；1:已授權
     */
    private Integer motpFlag;

    /**
     * MOTP註記申請日期
     */
    private Date motpFlagDate;

    /**
     * 推播Token
     */
    private String pushToken;

    /**
     * 有設定快登時，開啟APP是否直接登入，0:不直接登入；1:直接登入
     */
    private Integer directEzLoginFlag;

    /**
     * 富邦錢包註記 0/1
     */
    private Integer walletFlag;

    /**
     * @return the deviceInfoKey
     */
    public Integer getDeviceInfoKey() {
        return deviceInfoKey;
    }

    /**
     * @param deviceInfoKey
     *            the deviceInfoKey to set
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * @return the deviceUuId
     */
    public String getDeviceUuId() {
        return deviceUuId;
    }

    /**
     * @param deviceUuId
     *            the deviceUuId to set
     */
    public void setDeviceUuId(String deviceUuId) {
        this.deviceUuId = deviceUuId;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the devicePlatform
     */
    public String getDevicePlatform() {
        return devicePlatform;
    }

    /**
     * @param devicePlatform
     *            the devicePlatform to set
     */
    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    /**
     * @return the devicePlatformVersion
     */
    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    /**
     * @param devicePlatformVersion
     *            the devicePlatformVersion to set
     */
    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

    /**
     * @return the deviceAlias
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * @param deviceAlias
     *            the deviceAlias to set
     */
    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    /**
     * @return the loginFlag
     */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     *            the loginFlag to set
     */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return the loginAuthDate
     */
    public Date getLoginAuthDate() {
        return loginAuthDate;
    }

    /**
     * @param loginAuthDate
     *            the loginAuthDate to set
     */
    public void setLoginAuthDate(Date loginAuthDate) {
        this.loginAuthDate = loginAuthDate;
    }

    /**
     * @return the notifyFlag
     */
    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    /**
     * @param notifyFlag
     *            the notifyFlag to set
     */
    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    /**
     * @return the notifyAuthDate
     */
    public Date getNotifyAuthDate() {
        return notifyAuthDate;
    }

    /**
     * @param notifyAuthDate
     *            the notifyAuthDate to set
     */
    public void setNotifyAuthDate(Date notifyAuthDate) {
        this.notifyAuthDate = notifyAuthDate;
    }

    /**
     * @return the notifyPass
     */
    public Integer getNotifyPass() {
        return notifyPass;
    }

    /**
     * @param notifyPass
     *            the notifyPass to set
     */
    public void setNotifyPass(Integer notifyPass) {
        this.notifyPass = notifyPass;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the msgFlag
     */
    public Integer getMsgFlag() {
        return msgFlag;
    }

    /**
     * @param msgFlag
     *            the msgFlag to set
     */
    public void setMsgFlag(Integer msgFlag) {
        this.msgFlag = msgFlag;
    }

    /**
     * @return the msgPassword
     */
    public String getMsgPassword() {
        return msgPassword;
    }

    /**
     * @param msgPassword
     *            the msgPassword to set
     */
    public void setMsgPassword(String msgPassword) {
        this.msgPassword = msgPassword;
    }

    /**
     * @return the enable
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the loginType
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the errorFlag
     */
    public Integer getErrorFlag() {
        return errorFlag;
    }

    /**
     * @param errorFlag
     *            the errorFlag to set
     */
    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }

    /**
     * @return the loginPasswdType
     */
    public Integer getLoginPasswdType() {
        return loginPasswdType;
    }

    /**
     * @param loginPasswdType
     *            the loginPasswdType to set
     */
    public void setLoginPasswdType(Integer loginPasswdType) {
        this.loginPasswdType = loginPasswdType;
    }

    /**
     * @return the qsearchFlag
     */
    public Integer getQsearchFlag() {
        return qsearchFlag;
    }

    /**
     * @param qsearchFlag
     *            the qsearchFlag to set
     */
    public void setQsearchFlag(Integer qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }

    /**
     * @return the chgPwdUseridFlag
     */
    public Integer getChgPwdUseridFlag() {
        return chgPwdUseridFlag;
    }

    /**
     * @param chgPwdUseridFlag
     *            the chgPwdUseridFlag to set
     */
    public void setChgPwdUseridFlag(Integer chgPwdUseridFlag) {
        this.chgPwdUseridFlag = chgPwdUseridFlag;
    }

    /**
     * @return the chgPwdUseridDate
     */
    public Date getChgPwdUseridDate() {
        return chgPwdUseridDate;
    }

    /**
     * @param chgPwdUseridDate
     *            the chgPwdUseridDate to set
     */
    public void setChgPwdUseridDate(Date chgPwdUseridDate) {
        this.chgPwdUseridDate = chgPwdUseridDate;
    }

    /**
     * @return the motpFlag
     */
    public Integer getMotpFlag() {
        return motpFlag;
    }

    /**
     * @param motpFlag
     *            the motpFlag to set
     */
    public void setMotpFlag(Integer motpFlag) {
        this.motpFlag = motpFlag;
    }

    /**
     * @return the motpFlagDate
     */
    public Date getMotpFlagDate() {
        return motpFlagDate;
    }

    /**
     * @param motpFlagDate
     *            the motpFlagDate to set
     */
    public void setMotpFlagDate(Date motpFlagDate) {
        this.motpFlagDate = motpFlagDate;
    }

    /**
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken
     *            the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    /**
     * @return the directEzLoginFlag
     */
    public Integer getDirectEzLoginFlag() {
        return directEzLoginFlag;
    }

    /**
     * @param directEzLoginFlag
     *            the directEzLoginFlag to set
     */
    public void setDirectEzLoginFlag(Integer directEzLoginFlag) {
        this.directEzLoginFlag = directEzLoginFlag;
    }

    public Integer getWalletFlag() {
        return walletFlag;
    }

    public void setWalletFlag(Integer walletFlag) {
        this.walletFlag = walletFlag;
    }
}
