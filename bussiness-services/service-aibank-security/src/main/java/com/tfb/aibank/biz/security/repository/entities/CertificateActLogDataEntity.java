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
package com.tfb.aibank.biz.security.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)CertificateActLogDataEntity.java
 * 
 * <p>CERTIFICATE LOG DATA</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/12/02, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "C3_CERT_ACT_LOG")
public class CertificateActLogDataEntity implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    /**
     * C3 ACT LOG PK鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CERT_LOG_DATA_SEQ")
    @SequenceGenerator(name = "CERT_LOG_DATA_SEQ", sequenceName = "CERT_LOG_DATA_SEQ", allocationSize = 20)
    @Column(name = "C3_ACT_LOG_KEY")
    private Integer certLogKey;

    /**
     * CUST_ID
     */
    @Basic
    @Column(name = "CUST_ID")
    private String custId;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     * 憑證作業日 YYYYMMDD
     */
    @Basic
    @Column(name = "C3_ACT_DATE")
    private String actDate;

    /**
     * C3憑證作業流水號
     */
    @Basic
    @Column(name = "C3_VERIFYNO")
    private String verifyNo;

    /**
     * API token值
     */
    @Basic
    @Column(name = "C3_TOKEN")
    private String token;

    /**
     * C3執行結果 S：執行成功 F：執行失敗
     */
    @Basic
    @Column(name = "C3_RESULT_CODE")
    private String resultCode;

    /**
     * 動作 1：申請 2：驗證 3：變更密碼
     */
    @Basic
    @Column(name = "ACT")
    private String act;

    /**
     * 業務別 1. EBANK 2. MBANK
     */
    @Basic
    @Column(name = "ACT_BIZ_TYPE")
    private String actBizType;

    /**
     * 申請/驗證 C3憑證功能項 1：大額換匯
     */
    @Basic
    @Column(name = "ACT_USE_FUNC")
    private String actUseFunc;

    /**
     * accessLogKey
     */
    @Basic
    @Column(name = "ACCESS_LOG_KEY")
    private Integer accessLogKey;

    /**
     * 客戶裝置 (網銀)
     */
    @Basic
    @Column(name = "USER_AGENT")
    private String userAgent;

    /**
     * 手機型號 (行銀)
     */
    @Basic
    @Column(name = "DEVICE_MODEL")
    private String deviceModel;

    /**
     * 手機作業系統版本 (行銀)
     */
    @Basic
    @Column(name = "DEVICE_OS")
    private String deviceOS;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CRT_TIME")
    private Date createTime;

    /**
     * 申請憑證 閱讀條款版本
     */
    @Basic
    @Column(name = "TERM_VER")
    private String termVer;

    /**
     * 使用者 Ip
     */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /**
     * 使用者 Port
     */
    @Basic
    @Column(name = "CLIENT_PORT")
    private String clientPort;

    /**
     * 安控類型(1:晶片金融卡 / 2:MOTP)
     */
    @Basic
    @Column(name = "SECURITY_TYPE")
    private String securityType;

    /**
     * 交易存取記錄追蹤編號
     */
    @Basic
    @Column(name = "TRACE_ID")
    private String traceId;

    public Integer getCertLogKey() {
        return certLogKey;
    }

    public void setCertLogKey(Integer certLogKey) {
        this.certLogKey = certLogKey;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public String getVerifyNo() {
        return verifyNo;
    }

    public void setVerifyNo(String verifyNo) {
        this.verifyNo = verifyNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getActBizType() {
        return actBizType;
    }

    public void setActBizType(String actBizType) {
        this.actBizType = actBizType;
    }

    public String getActUseFunc() {
        return actUseFunc;
    }

    public void setActUseFunc(String actUseFunc) {
        this.actUseFunc = actUseFunc;
    }

    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTermVer() {
        return termVer;
    }

    public void setTermVer(String termVer) {
        this.termVer = termVer;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
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
