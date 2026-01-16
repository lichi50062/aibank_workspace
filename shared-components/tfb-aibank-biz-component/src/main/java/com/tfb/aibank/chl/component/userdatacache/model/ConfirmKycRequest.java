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
 * @(#)ConfirmKYC.java
 * 
 * <p>Description: KYC 確認紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/21, Alex
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ConfirmKycRequest implements Serializable {
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
    /**
     * 第幾次確認
     */
    private int confirmStep;

    /**
     * 確認時間
     */
    private Date confirmTime;

    /**
     * 客戶IP
     */
    private String clientIp;

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

    public int getConfirmStep() {
        return confirmStep;
    }

    public void setConfirmStep(int confirmStep) {
        this.confirmStep = confirmStep;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
