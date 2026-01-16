/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)KycCustomerRecordRequest.java
 * 
 * <p>Description: KYC測試紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/21, Alex
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycCustomerRecordRequest implements Serializable {
    /** 身分證 */
    private String custId;
    /** 誤別碼 */
    private String uidDup;
    /** 使用者代碼 */
    private String userId;
    /** Company Kind */
    private Integer companyKind;
    /** 用戶代碼 */
    private String nameCode;

    /** 送理規資料 */
    private String txData;

    /** 風險等級 */
    private String riskCode;

    /** 客戶IP */
    private String clientIp;

    /** 安控機制 */
    private String securityType;

    /** 交易來源 */
    private String txSource;

    private String txStatus;

    private String txErrorCode;

    private String txErrorDesc;

    private String txErrorSystemId;

    private String traceId;

    private Date createTime;

    private Date hostTxTime;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getTxData() {
        return txData;
    }

    public void setTxData(String txData) {
        this.txData = txData;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getTxSource() {
        return txSource;
    }

    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

    public String getTxErrorCode() {
        return txErrorCode;
    }

    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }
}
