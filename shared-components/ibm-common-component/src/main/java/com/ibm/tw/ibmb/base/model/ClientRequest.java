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
package com.ibm.tw.ibmb.base.model;

/**
 * <p>
 * 由MobileFirst上送的Request
 * </p>
 *
 * @author Alex LS Chen
 * @version 1.0, 2016/04/25
 * @see
 * @since
 */
public class ClientRequest {

    private String resource; // 資源名稱
    private String deviceIxd; // 裝置Id, 無法取得時為"none"
    private String trackingIxd; // 交易追蹤Id, 每個Rq請求都是唯一值
    private String txnIxd; // 交易Id, 每隻交易都為唯一值, 同交易每次也會不同
    private String model; // 裝置Model, "Passion" means Nexus One, PC版值會類似"Mozilla, Netscape"
    private String platform; // 裝置平台, android或ios, PC版值類似"Windows NT 6.1; Win64; x64"
    private String version; // 裝置平台版本, PC版值類似"5.0 (Windows)"
    private String runtime; // 裝置執行平台, ex:"chrome", "firefox", "safari"
    private String runtimeVer; // 裝置執行平台版本, ex: 61, 11
    private String network; // 當下網路型態, 1:WIFI 2:3G/4G, 0:PC版
    private String appVer; // 應用程式版本
    private String clientTime; // Client端發送請求時的時間戳記
    private String token; // 交易重覆檢核用Token
    private String locale; // Client端的語系
    private String fromSys; // 來源系統 , 0:個網APP 1:個人網銀 2:卡APP
    private String fromPage; // 請求當下的頁面代號
    private String clientIp; // Client無法正確取得,由Adapter補上,要注意client透過proxy時會帶多個. 另有ipv6格式也要注意ipv6:0:0:0:0:0:0:0:1
    private Object rqData; // 上行訊息, 由交易自行定義內容
    private String sessionId; // 每次連線時產生Session的Id
    private String seed; // 會記錄在裝置上的唯一值，登入成功會重新產生並覆蓋
    private String deviceToken; // appId
    private String buildDisplay; // Android使用之version，供生物辨識黑名單查詢用
    private String entryPoint; // 交易點擊量版位 0:所有服務 1:常用功能 2:訊息通知 3:首頁牌卡 4:其他(關聯交易)

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDeviceIxd() {
        return deviceIxd;
    }

    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    public String getTrackingIxd() {
        return trackingIxd;
    }

    public void setTrackingIxd(String trackingIxd) {
        this.trackingIxd = trackingIxd;
    }

    public String getTxnIxd() {
        return txnIxd;
    }

    public void setTxnIxd(String txnIxd) {
        this.txnIxd = txnIxd;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getClientTime() {
        return clientTime;
    }

    public void setClientTime(String clientTime) {
        this.clientTime = clientTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFromSys() {
        return fromSys;
    }

    public void setFromSys(String fromSys) {
        this.fromSys = fromSys;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Object getRqData() {
        return rqData;
    }

    public void setRqData(Object rqData) {
        this.rqData = rqData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRuntimeVer() {
        return runtimeVer;
    }

    public void setRuntimeVer(String runtimeVer) {
        this.runtimeVer = runtimeVer;
    }

    public String getBuildDisplay() {
        return buildDisplay;
    }

    public void setBuildDisplay(String buildDisplay) {
        this.buildDisplay = buildDisplay;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }
}
