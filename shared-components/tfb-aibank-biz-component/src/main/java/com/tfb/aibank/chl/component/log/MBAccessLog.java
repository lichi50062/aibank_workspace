/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.log;

import java.util.Date;

// @formatter:off
/**
 * @(#)MBAccessLog.java
 * 
 * <p>Description:交易存取記錄 VO</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MBAccessLog {

    /**
     * access log PK, 在 AIBank 使用 traceId
     */
    private String accessLogKey;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 登入IP
     */
    private String clientIp;

    /**
     * client 連線 port
     */
    private String clientPort;

    /**
     * 登入記錄鍵值
     */
    private Integer loginLogKey;

    /**
     * 交易代號
     */
    private String taskId;

    /**
     * 存取時間
     */
    private Date accessTime;

    /**
     * 錯誤系統代碼
     */
    private String errorSystemId;

    /**
     * 錯誤代碼
     */
    private String errorCode;

    /**
     * 錯誤訊息描述
     */
    private String errorDesc;

    /**
     * 交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     */
    private Integer resultCode;

    /**
     * 通路別
     */
    private String channelId;

    /**
     * 裝置名稱
     */
    private String model;

    /**
     * 裝置ID
     */
    private String deviceId;

    /**
     * 裝置作業系統
     */
    private String devicePlatform;

    /**
     * 裝置作業系統版本
     */
    private String devicePlatformVersion;

    /**
     * NATIVE CODE版本
     */
    private String appVersion;

    /**
     * Resource CODE版本
     */
    private String resVersion;

    /**
     * 語系
     */
    private String locale;

    /**
     * 網路, 1:wifi 2:mobile
     */
    private String network;

    /**
     * 國別代碼2碼
     */
    private String nationCode;

    /**
     * 國別說明
     */
    private String nationName;

    /**
     * 用戶代碼
     */
    private String nameCode;

    /**
     * 公司類型
     */
    private Integer companyKind;

    /** 業務別 (交易代碼的第二三碼) */
    private String busType;

    /** 功能類別 (交易代碼的第四五碼) */
    private String funcType;

    /** 交易點擊量版位 */
    private String entryPoint;

    /** 驗證方式 */
    private Integer securityType;

    /** 身分證字號(加密) */
    private String encCustId;

    /** 使用者代碼(隱碼) */
    private String maskUserId;

    /** 備忘錄 */
    private String memo;

    /** 客群 */
    private String massChk;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getResVersion() {
        return resVersion;
    }

    public void setResVersion(String resVersion) {
        this.resVersion = resVersion;
    }

    /** 選單代碼 */
    private String menuId;

    /**
     * 執行所在伺服器
     */
    private String serverName;

    /**
     * 取得交易存取記錄鍵值 (traceId)
     * 
     * @return Integer 交易存取記錄鍵值
     */
    public String getAccessLogKey() {
        return this.accessLogKey;
    }

    /**
     * 設定交易存取記錄鍵值 (traceId)
     * 
     * @param accessLogKey
     */
    public void setAccessLogKey(String accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * 取得存取時間
     * 
     * @return Date 存取時間
     */
    public Date getAccessTime() {
        return this.accessTime;
    }

    /**
     * 設定存取時間
     * 
     * @param accessTime
     *            要設定的存取時間
     */
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    /**
     * 取得登入IP
     * 
     * @return String 登入IP
     */
    public String getClientIp() {
        return this.clientIp;
    }

    /**
     * 設定登入IP
     * 
     * @param clientIp
     *            要設定的登入IP
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    /**
     * 取得公司鍵值
     * 
     * @return Long 公司鍵值
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定公司鍵值
     * 
     * @param companyKey
     *            要設定的公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得錯誤代碼
     * 
     * @return String 錯誤代碼
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 設定錯誤代碼
     * 
     * @param errorCode
     *            要設定的錯誤代碼
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 取得錯誤訊息描述
     * 
     * @return String 錯誤訊息描述
     */
    public String getErrorDesc() {
        return this.errorDesc;
    }

    /**
     * 設定錯誤訊息描述
     * 
     * @param errorDesc
     *            要設定的錯誤訊息描述
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * 取得錯誤系統代碼
     * 
     * @return String 錯誤系統代碼
     */
    public String getErrorSystemId() {
        return this.errorSystemId;
    }

    /**
     * 設定錯誤系統代碼
     * 
     * @param errorSystemId
     *            要設定的錯誤系統代碼
     */
    public void setErrorSystemId(String errorSystemId) {
        this.errorSystemId = errorSystemId;
    }

    /**
     * 取得登入記錄鍵值
     * 
     * @return Integer 登入記錄鍵值
     */
    public Integer getLoginLogKey() {
        return this.loginLogKey;
    }

    /**
     * 設定登入記錄鍵值
     * 
     * @param loginLogKey
     *            要設定的登入記錄鍵值
     */
    public void setLoginLogKey(Integer loginLogKey) {
        this.loginLogKey = loginLogKey;
    }

    /**
     * 取得交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     * 
     * @return Integer 交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     */
    public Integer getResultCode() {
        return this.resultCode;
    }

    /**
     * 設定交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     * 
     * @param resultCode
     *            要設定的交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     */
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 取得交易代號
     * 
     * @return String 交易代號
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 設定交易代號
     * 
     * @param taskId
     *            要設定的交易代號
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 取得使用者鍵值
     * 
     * @return Long 使用者鍵值
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定使用者鍵值
     * 
     * @param userKey
     *            要設定的使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 取得 channelId
     *
     * @return channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 設定 channelId
     *
     * @param channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 取得 locale
     *
     * @return locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * 設定 locale
     *
     * @param locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得 network
     *
     * @return network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * 設定 network
     *
     * @param network
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * 取得 nationCode
     *
     * @return nationCode
     */
    public String getNationCode() {
        return nationCode;
    }

    /**
     * 設定 nationCode
     *
     * @param nationCode
     */
    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    /**
     * 取得 nationName
     *
     * @return nationName
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * 設定 nationName
     *
     * @param nationName
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    /**
     * 取得 nameCode
     *
     * @return nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * 設定 nameCode
     *
     * @param nameCode
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * 取得選單代碼
     * 
     * @return
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 設定選單代碼
     * 
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName
     *            the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public Integer getSecurityType() {
        return securityType;
    }

    public void setSecurityType(Integer securityType) {
        this.securityType = securityType;
    }

    public String getEncCustId() {
        return encCustId;
    }

    public void setEncCustId(String encCustId) {
        this.encCustId = encCustId;
    }

    public String getMaskUserId() {
        return maskUserId;
    }

    public void setMaskUserId(String maskUserId) {
        this.maskUserId = maskUserId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMassChk() {
        return massChk;
    }

    public void setMassChk(String massChk) {
        this.massChk = massChk;
    }

}
