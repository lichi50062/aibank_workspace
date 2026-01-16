package com.tfb.aibank.biz.user.repository.aib.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)WBPlusAccessLogEntity.java
 * 
 * <p>Description:ACCESS LOG 拋檔訊息 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/17, David Huang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "WB_PLUS_ACCESS_LOG")
public class WBPlusAccessLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主鍵值 */
    @Id
    @Column(name = "WB_PLUS_ACCESS_LOG_KEY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger wbPlusAccessLogKey;
    
    /** 存取時間 */
    @Basic
    @Column(name = "ACCESS_DAY")
    private Date accessDay;

    /** 存取時間 */
    @Basic
    @Column(name = "ACCESS_TIME")
    private Date accessTime;

    /** 交易存取記錄追踪編號 */
    @Basic
    @Column(name = "TRACE_ID")
    private String traceId;

    /** APP版號 */
    @Basic
    @Column(name = "APP_VERSION")
    private String appVersion;

    /** DATA APP版號 */
    @Basic
    @Column(name = "DATA_APP_VERSION")
    private String dataAppVersion;

    /** SEED */
    @Basic
    @Column(name = "SEED")
    private String seed;

    /** 來源頁面 */
    @Basic
    @Column(name = "FROM_PAGE")
    private String fromPage;

    /** 語言 */
    @Basic
    @Column(name = "LANGUAGE_TAG")
    private String languageTag;
    
    /** 登入工作階段編號 */
    @Basic
    @Column(name = "SESSION_ID")
    private String sessionId;

    /** 語系 */
    @Basic
    @Column(name = "LOCALE")
    private String locale;

    /** 頁面代碼 */
    @Basic
    @Column(name = "PAGE_ID")
    private String pageId;

    /** 裝置ID */
    @Basic
    @Column(name = "DEVICE_ID")
    private String deviceId;

    /** SPAN ID */
    @Basic
    @Column(name = "SPAN_ID")
    private String spanId;

    /** 連線IP */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** 連線Port */
    @Basic
    @Column(name = "CLIENT_PORT")
    private String clientPort;

    /** 通路別 */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

    /** TRACKING ID */
    @Basic
    @Column(name = "TRACKING_ID")
    private String trackingId;

    /** Pod */
    @Basic
    @Column(name = "POD_NAME")
    private String podName;

    /** PROJECT NAME' */
    @Basic
    @Column(name = "PROJECT_NAME")
    private String projectName;

    /** PROJECT APPLICATION NAME */
    @Basic
    @Column(name = "PROJECT_APPLICATION_NAME")
    private String projectApplicationName;

    /** FLB_KEY */
    @Basic
    @Column(name = "FLB_KEY")
    private String flbKey;

    /** BUS_TYPE */
    @Basic
    @Column(name = "BUS_TYPE")
    private String busType;

    /** FUNC_TYPE */
    @Basic
    @Column(name = "FUNC_TYPE")
    private String funcType;

    /** COMPANY_KEY */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** COMPANY_KIND */
    @Basic
    @Column(name = "COMPANY_KIND")
    private Integer companyKind;

    /** DEVICE_PLATFORM */
    @Basic
    @Column(name = "DEVICE_PLATFORM")
    private String devicePlatform;

    /** DEVICE_PLATFORM_VERSION */
    @Basic
    @Column(name = "DEVICE_PLATFORM_VERSION")
    private String devicePlatformVersion;

    /** ENC_CUSTID */
    @Basic
    @Column(name = "ENC_CUSTID")
    private String encCustid;
    
    /** CUSTID */
    ///@Basic
    //@Column(name = "CUST_ID")
    //private String custId;


    /** ERROR_CODE */
    @Basic
    @Column(name = "ERROR_CODE")
    private String errorCode;

    /** ERROR_DESC */
    @Basic
    @Column(name = "ERROR_DESC")
    private String errorDesc;

    /** ERROR_SYSTEM_ID */
    @Basic
    @Column(name = "ERROR_SYSTEM_ID")
    private String errorSystemId;

    /** MASK_USERID */
    @Basic
    @Column(name = "MASK_USERID")
    private String maskUserId;

    /** MASS_CHK */
    @Basic
    @Column(name = "MASS_CHK")
    private String massChk;

    /** MODEL */
    @Basic
    @Column(name = "MODEL")
    private String model;

    /** NAME_CODE */
    @Basic
    @Column(name = "NAME_CODE")
    private String nameCode;

    /** NETWORK */
    @Basic
    @Column(name = "NETWORK")
    private String network;

    /** RESULT_CODE */
    @Basic
    @Column(name = "RESULT_CODE")
    private Integer resultCode;

    /** RES_VERSION */
    @Basic
    @Column(name = "RES_VERSION")
    private String resVersion;

    /** SERVER_NAME */
    @Basic
    @Column(name = "SERVER_NAME")
    private String serverName;

    /** TASK_ID */
    @Basic
    @Column(name = "TASK_ID")
    private String taskId;

    /** USER_KEY */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

	public BigInteger getWBPlusAccessLogKey() {
		return wbPlusAccessLogKey;
	}

	public void setWBPlusAccessLogKey(BigInteger wbPlusAccessLogKey) {
		this.wbPlusAccessLogKey = wbPlusAccessLogKey;
	}
	
	public void setAccessDay(Date accessDay) {
		this.accessDay = accessDay;
	}
	
	public Date getAccessDay() {
		return accessDay;
	}

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

	public BigInteger getWbPlusAccessLogKey() {
		return wbPlusAccessLogKey;
	}

	public void setWbPlusAccessLogKey(BigInteger wbPlusAccessLogKey) {
		this.wbPlusAccessLogKey = wbPlusAccessLogKey;
	}

	public String getDataAppVersion() {
		return dataAppVersion;
	}

	public void setDataAppVersion(String dataAppVersion) {
		this.dataAppVersion = dataAppVersion;
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

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
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

	public String getEncCustid() {
		return encCustid;
	}

	public void setEncCustid(String encCustId) {
		this.encCustid = encCustId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
