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
package com.tfb.aibank.biz.security.services.twcaapi.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CertificateActLogModel.java
 * 
 * <p>Description:註銷憑證紀錄LOG</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/12/03, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CertificateActLogModel {

    /** C3 ACT LOG PK鍵值 */
    private Integer certLogKey;

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private int companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 憑證作業日 YYYYMMDD */
    @Schema(description = "憑證作業日 YYYYMMDD")
    private String actDate;

    /** C3憑證作業流水號 */
    @Schema(description = "C3憑證作業流水號")
    private String verifyNo;

    /** API token值 */
    @Schema(description = "API token值")
    private String token;

    /** C3執行結果 S：執行成功 F：執行失敗 */
    @Schema(description = "C3執行結果 S：執行成功 F：執行失敗")
    private String resultCode;

    /** 動作 1：申請 2：驗證 3：變更密碼 */
    @Schema(description = "動作 1：申請 2：驗證 3：變更密碼")
    private String act;

    /** 業務別 1. EBANK 2. MBANK */
    @Schema(description = "業務別 1. EBANK 2. MBANK")
    private String actBizType;

    /** 申請/驗證 C3憑證功能項 1：大額換匯 */
    @Schema(description = "申請/驗證 C3憑證功能項 1：大額換匯")
    private String actUseFunc;

    /** accessLogKey */
    @Schema(description = "accessLogKey")
    private Integer accessLogKey;

    /** 客戶裝置 (網銀) */
    @Schema(description = "客戶裝置 (網銀)")
    private String userAgent;

    /** 手機型號 (行銀) */
    @Schema(description = "手機型號 (行銀)")
    private String deviceModel;

    /** 手機作業系統版本 (行銀) */
    @Schema(description = "手機作業系統版本 (行銀)")
    private String deviceOS;

    /** 建立時間 */
    @Schema(description = "建立時間")
    private Date createTime;

    /** 申請憑證 閱讀條款版本 */
    @Schema(description = "申請憑證 閱讀條款版本")
    private String termVer;

    /** 使用者 Ip */
    @Schema(description = "使用者 Ip")
    private String clientIp;

    /** 使用者 Port */
    @Schema(description = "使用者 Port")
    private String clientPort;

    /** 安控類型(1:晶片金融卡 / 2:MOTP) */
    @Schema(description = "安控類型(1:晶片金融卡 / 2:MOTP)")
    private String securityType;

    /** 交易存取記錄追蹤編號 */
    @Schema(description = "交易存取記錄追蹤編號")
    private String traceId;

    /**
     * @return {@link #custId}
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            {@link #custId}
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return {@link #userId}
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            {@link #userId}
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return {@link #companyKind}
     */
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            {@link #companyKind}
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return {@link #uidDup}
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            {@link #uidDup}
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return {@link #actDate}
     */
    public String getActDate() {
        return actDate;
    }

    /**
     * @param actDate
     *            {@link #actDate}
     */
    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    /**
     * @return {@link #verifyNo}
     */
    public String getVerifyNo() {
        return verifyNo;
    }

    /**
     * @param verifyNo
     *            {@link #verifyNo}
     */
    public void setVerifyNo(String verifyNo) {
        this.verifyNo = verifyNo;
    }

    /**
     * @return {@link #token}
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            {@link #token}
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return {@link #resultCode}
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode
     *            {@link #resultCode}
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return {@link #act}
     */
    public String getAct() {
        return act;
    }

    /**
     * @param act
     *            {@link #act}
     */
    public void setAct(String act) {
        this.act = act;
    }

    /**
     * @return {@link #actBizType}
     */
    public String getActBizType() {
        return actBizType;
    }

    /**
     * @param actBizType
     *            {@link #actBizType}
     */
    public void setActBizType(String actBizType) {
        this.actBizType = actBizType;
    }

    /**
     * @return {@link #actUseFunc}
     */
    public String getActUseFunc() {
        return actUseFunc;
    }

    /**
     * @param actUseFunc
     *            {@link #actUseFunc}
     */
    public void setActUseFunc(String actUseFunc) {
        this.actUseFunc = actUseFunc;
    }

    /**
     * @return {@link #accessLogKey}
     */
    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    /**
     * @param accessLogKey
     *            {@link #accessLogKey}
     */
    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * @return {@link #userAgent}
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent
     *            {@link #userAgent}
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return {@link #deviceModel}
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     *            {@link #deviceModel}
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * @return {@link #deviceOS}
     */
    public String getDeviceOS() {
        return deviceOS;
    }

    /**
     * @param deviceOS
     *            {@link #deviceOS}
     */
    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    /**
     * @return {@link #createTime}
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            {@link #createTime}
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return {@link #termVer}
     */
    public String getTermVer() {
        return termVer;
    }

    /**
     * @param termVer
     *            {@link #termVer}
     */
    public void setTermVer(String termVer) {
        this.termVer = termVer;
    }

    /**
     * @return {@link #clientIp}
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            {@link #clientIp}
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return {@link #clientPort}
     */
    public String getClientPort() {
        return clientPort;
    }

    /**
     * @param clientPort
     *            {@link #clientPort}
     */
    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    /**
     * @return {@link #securityType}
     */
    public String getSecurityType() {
        return securityType;
    }

    /**
     * @param securityType
     *            {@link #securityType}
     */
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public Integer getCertLogKey() {
        return certLogKey;
    }

    public void setCertLogKey(Integer certLogKey) {
        this.certLogKey = certLogKey;
    }

    /**
     * @return {@link #traceId}
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            {@link #traceId}
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
