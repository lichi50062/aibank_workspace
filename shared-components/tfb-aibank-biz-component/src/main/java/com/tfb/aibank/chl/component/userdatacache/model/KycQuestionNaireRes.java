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

// @formatter:off
import jakarta.xml.bind.annotation.XmlElement; /**
 * @(#)KycQuestionNaireRes.java
 * 
 * <p>Description:KYC 填答資訊->業管 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/20, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KycQuestionNaireRes {
    /** KYC名稱 */
    private String riskName;
    /** 狀態 */
    private String state;
    /** KYC ID */
    private String riskID;
    /** 是否popup N / Y */
    private String popup;
    /** 有效期限 YYYYMMDD */
    private String expiryDate;
    /** 冷靜期間KYC名稱 */
    private String coolingRiskName;
    /** 冷靜期間KYC ID */
    private String coolingRiskID;
    /** 冷靜期期限 YYYYMMDD */
    private String coolingPeriod;

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRiskID() {
        return riskID;
    }

    public void setRiskID(String riskID) {
        this.riskID = riskID;
    }

    public String getPopup() {
        return popup;
    }

    public void setPopup(String popup) {
        this.popup = popup;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCoolingRiskName() {
        return coolingRiskName;
    }

    public void setCoolingRiskName(String coolingRiskName) {
        this.coolingRiskName = coolingRiskName;
    }

    public String getCoolingRiskID() {
        return coolingRiskID;
    }

    public void setCoolingRiskID(String coolingRiskID) {
        this.coolingRiskID = coolingRiskID;
    }

    public String getCoolingPeriod() {
        return coolingPeriod;
    }

    public void setCoolingPeriod(String coolingPeriod) {
        this.coolingPeriod = coolingPeriod;
    }
}
