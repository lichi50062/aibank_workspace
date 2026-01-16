package com.tfb.aibank.biz.user.services.log.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;

// @formatter:off
/**
 * @(#)WBPlusAccessLogRequest.java
 * 
 * <p>Description: ACCESS LOG拋檔記錄 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/17, David Huang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class WBPlusAccessLogRequest {
    /** ACCESS TIME */
    @Schema(description = "ACCESS TIME")
    private Date accessTime;
    
    /** 交易存取記錄追踪編號 */
    @Schema(description = "交易存取記錄追踪編號")
    private String traceId;

    /** APP版號 */
    @Schema(description = "APP版號")
    private String appVersion;

    /** DATA APP 版號 */
    @Schema(description = "DATA APP版號")
    private String dataAppVersion;

    /** SEED */
    @Schema(description = "SEED")
    private String seed;

    /** 來源頁面 */
    @Schema(description = "來源頁面")
    private String fromPage;

    /** 語言 */
    @Schema(description = "語言")
    private String languageTag;
    
    /** 登入工作階段編號 */
    @Schema(description = "登入工作階段編號")
    private String sessionId;

    /** 語系 */
    @Schema(description = "語系")
    private String locale;

    /** 頁面代碼 */
    @Schema(description = "頁面代碼")
    private String pageId;

    /** 裝置ID */
    @Schema(description = "裝置ID")
    private String deviceId;

    /** SPAN ID */
    @Schema(description = "SPAN ID")
    private String spanId;

    /** 連線IP */
    @Schema(description = "客戶端IP")
    private String clientIp;

    /** 連線Port */
    @Schema(description = "客戶端Port")
    private String clientPort;

    /** 通路別 */
    @Schema(description = "通路別")
    private String channelId;

    /** TRACKING ID */
    @Schema(description = "TRACKING ID")
    private String trackingId;

    /** Pod */
   @Schema(description = "Pod")
   private String podName;

    /** PROJECT NAME */
   @Schema(description = "PROJECT NAME")
    private String projectName;

    /** PROJECT APPLICATION NAME */
   @Schema(description = "PROJECT APPLICATION NAME")
    private String projectApplicationName;

    /** FLB_KEY */
   @Schema(description = "flb-key")
    private String flbKey;

    /** BUS_TYPE */
    @Schema(description = "服務類別")
     private String busType;

    /** COMPANY_KEY */
    @Schema(description = "公司鍵值")
     private Integer companyKey;

    /** COMPANY_KIND */
    @Schema(description = "公司類別")
     private Integer companyKind;

    /** DEVICE_PLATFORM */
    @Schema(description = "裝置作業系統")
     private String devicePlatform;

    /** DEVICE_PLATFORM_VERSION */
    @Schema(description = "裝置作業系統版本")
     private String devicePlatformVersion;

    /** ENC_CUSTID */
    @Schema(description = "身份證字號(加密)")
     private String encCustId;
    
    /** CUSTID */
    @Schema(description = "身份證字號")
     private String custId;

	/** ERROR_CODE */
    @Schema(description = "錯誤代碼")
     private String errorCode;

    /** ERROR_DESC */
    @Schema(description = "錯誤敘述")
     private String errorDesc;

    /** ERROR_SYSTEM_ID */
    @Schema(description = "錯誤系統別")
     private String errorSystemId;

    /** FUNC_TYPE */
    @Schema(description = "功能類別")
     private String funcType;

    /** MASK_USERID */
    @Schema(description = "使用者代碼(加密)")
     private String maskUserId;

    /** MASS_CHK */
    @Schema(description = "客群代碼")
     private String massChk;

    /** MODEL */
    @Schema(description = "裝置名稱")
     private String model;

    /** NAME_CODE */
    @Schema(description = "用戶代碼")
     private String nameCode;

    /** NETWORK */
    @Schema(description = "網路")
     private String network;

    /** RESULT_CODE */
    @Schema(description = "交易結果")
     private Integer resultCode;

    /** RES_VERSION */
    @Schema(description = "Resource CODE版本")
     private String resVersion;

    /** SERVER_NAME */
    @Schema(description = "SERVER_NAME")
     private String serverName;

    /** TASK_ID */
    @Schema(description = "交易代碼")
     private String taskId;

    /** USER_KEY */
    @Schema(description = "使用者鍵值")
     private Integer userKey;


	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDataAppVersion() {
		return dataAppVersion;
	}

	public void setDataAppVersion(String dataAppVersion) {
		this.dataAppVersion = dataAppVersion;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public String getLanguageTag() {
		return languageTag;
	}

	public void setLanguageTag(String languageTag) {
		this.languageTag = languageTag;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSpanId() {
		return spanId;
	}

	public void setSpanId(String spanId) {
		this.spanId = spanId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getPodName() {
		return podName;
	}

	public void setPodName(String podName) {
		this.podName = podName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectApplicationName() {
		return projectApplicationName;
	}

	public void setProjectApplicationName(String projectApplicationName) {
		this.projectApplicationName = projectApplicationName;
	}

	public String getClientPort() {
		return clientPort;
	}

	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}

	public String getFlbKey() {
		return flbKey;
	}

	public void setFlbKey(String flbKey) {
		this.flbKey = flbKey;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Integer getCompanyKey() {
		return companyKey;
	}

	public void setCompanyKey(Integer companyKey) {
		this.companyKey = companyKey;
	}

	public Integer getCompanyKind() {
		return companyKind;
	}

	public void setCompanyKind(Integer companyKind) {
		this.companyKind = companyKind;
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

	public String getEncCustId() {
		return encCustId;
	}

	public void setEncCustId(String encCustId) {
		this.encCustId = encCustId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorSystemId() {
		return errorSystemId;
	}

	public void setErrorSystemId(String errorSystemId) {
		this.errorSystemId = errorSystemId;
	}

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	public String getMaskUserId() {
		return maskUserId;
	}

	public void setMaskUserId(String maskUserId) {
		this.maskUserId = maskUserId;
	}

	public String getMassChk() {
		return massChk;
	}

	public void setMassChk(String massChk) {
		this.massChk = massChk;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResVersion() {
		return resVersion;
	}

	public void setResVersion(String resVersion) {
		this.resVersion = resVersion;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getUserKey() {
		return userKey;
	}

	public void setUserKey(Integer userKey) {
		this.userKey = userKey;
	}
}
