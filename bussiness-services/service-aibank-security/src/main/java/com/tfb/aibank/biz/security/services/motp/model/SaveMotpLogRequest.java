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
package com.tfb.aibank.biz.security.services.motp.model;

import com.tfb.aibank.component.motplog.MotpLogActionType;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)SaveMotpLogRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SaveMotpLogRequest {

    /** Log Type */
    @Schema(description = "Log Type")
    MotpLogActionType type;

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

    /** Service Name */
    @Schema(description = "Service Name")
    String serviceName;

    /** Service Method */
    @Schema(description = "Service Method")
    String serviceMethod;

    /** Status */
    @Schema(description = "Status")
    String Status;

    /** RqData */
    @Schema(description = "RqData")
    String rqData;

    /** RsData */
    @Schema(description = "RsData")
    String rsData;

    /**
     * @return the type
     */
    public MotpLogActionType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(MotpLogActionType type) {
        this.type = type;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the companyKind
     */
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
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
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     *            the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceMethod
     */
    public String getServiceMethod() {
        return serviceMethod;
    }

    /**
     * @param serviceMethod
     *            the serviceMethod to set
     */
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        Status = status;
    }

    /**
     * @return the rqData
     */
    public String getRqData() {
        return rqData;
    }

    /**
     * @param rqData
     *            the rqData to set
     */
    public void setRqData(String rqData) {
        this.rqData = rqData;
    }

    /**
     * @return the rsData
     */
    public String getRsData() {
        return rsData;
    }

    /**
     * @param rsData
     *            the rsData to set
     */
    public void setRsData(String rsData) {
        this.rsData = rsData;
    }

}
