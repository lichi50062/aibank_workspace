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
package com.tfb.aibank.chl.general.resource.dto;

import java.io.Serializable;
import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)AiOtherInsurDataResponse.java
 * 
 * <p>Description:非富壽保險資料表 Res</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/07, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AiOtherInsurDataResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 非富壽保單名稱 */
    private String insuranceName;
    /** 非富壽保單種類 */
    private String insuranceType;
    /** 非富壽保單公司 */
    private String insuranceCompany;
    /** 非富壽保單號碼 */
    private String insuranceNo;
    /** 非富壽保單號碼 */
    private int insuranceSeq;
    /** 非富壽累計保費 */
    private BigDecimal insurancePaid;
    /** 非富壽要保人 */
    private String insurancePolicyHolder;
    /** 非富壽被保險人 */
    private String insuranceInsured;
    /** 非富壽保險始期 */
    private String insuranceStartDate;
    /** 非富壽保單狀態 */
    private String insuranceStatus;
    /** 非富壽幣別 */
    private String insuranceCurrency;
    /** 非富壽繳別 */
    private String insuranceMop;
    /** 非富壽預估保單價值 */
    private String insuranceEstimatedPolicyValue;
    /** 非富壽參考日期 */
    private String insuranceConfDate;
    /** 主附約 0:主約 1:附約 */
    private Integer insuranceMain;
    /** 折台累計保費 */
    private BigDecimal twdPolicyFee;

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public BigDecimal getInsurancePaid() {
        return insurancePaid;
    }

    public void setInsurancePaid(BigDecimal insurancePaid) {
        this.insurancePaid = insurancePaid;
    }

    public String getInsurancePolicyHolder() {
        return insurancePolicyHolder;
    }

    public void setInsurancePolicyHolder(String insurancePolicyHolder) {
        this.insurancePolicyHolder = insurancePolicyHolder;
    }

    public String getInsuranceInsured() {
        return insuranceInsured;
    }

    public void setInsuranceInsured(String insuranceInsured) {
        this.insuranceInsured = insuranceInsured;
    }

    public String getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(String insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public String getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public String getInsuranceCurrency() {
        return insuranceCurrency;
    }

    public void setInsuranceCurrency(String insuranceCurrency) {
        this.insuranceCurrency = insuranceCurrency;
    }

    public String getInsuranceMop() {
        return insuranceMop;
    }

    public void setInsuranceMop(String insuranceMop) {
        this.insuranceMop = insuranceMop;
    }

    public String getInsuranceEstimatedPolicyValue() {
        return insuranceEstimatedPolicyValue;
    }

    public void setInsuranceEstimatedPolicyValue(String insuranceEstimatedPolicyValue) {
        this.insuranceEstimatedPolicyValue = insuranceEstimatedPolicyValue;
    }

    public String getInsuranceConfDate() {
        return insuranceConfDate;
    }

    public void setInsuranceConfDate(String insuranceConfDate) {
        this.insuranceConfDate = insuranceConfDate;
    }

    public Integer getInsuranceMain() {
        return insuranceMain;
    }

    public int getInsuranceSeq() {
        return insuranceSeq;
    }

    public void setInsuranceSeq(int insuranceSeq) {
        this.insuranceSeq = insuranceSeq;
    }

    public void setInsuranceMain(Integer insuranceMain) {
        this.insuranceMain = insuranceMain;
    }

    public BigDecimal getTwdPolicyFee() {
        return twdPolicyFee;
    }

    public void setTwdPolicyFee(BigDecimal twdPolicyFee) {
        this.twdPolicyFee = twdPolicyFee;
    }
}
