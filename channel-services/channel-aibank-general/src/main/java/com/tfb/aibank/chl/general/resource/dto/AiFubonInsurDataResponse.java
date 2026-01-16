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
 * @(#)AiFubonInsurDataResponse.java
 * 
 * <p>Description:富壽保險資料表Res</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/07, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AiFubonInsurDataResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    public AiFubonInsurDataResponse() {
    }

    /** 富壽保單名稱 */
    private String insuranceName;
    /** 富壽保單序號 */
    private String insuranceSeq;
    /** 富壽保單號碼 */
    private String insuranceNo;
    /** 富壽保單種類 */
    private String insuranceType;
    /** 富壽累計保費 */
    private BigDecimal insurancePaid;
    /** 富壽要保人 */
    private String insurancePolicyHolder;
    /** 富壽被保險人 */
    private String insuranceInsured;
    /** 富壽保險始期 */
    private String insuranceStartDate;
    /** 富壽保險到期日 */
    private String insuranceEndDate;
    /** 富壽保單狀態 */
    private String insuranceStatus;
    /** 富壽保額 */
    private BigDecimal insuranceAmount;
    /** 富壽保額單位 */
    private String insuranceUnit;
    /** 富壽幣別 */
    private String insuranceCurrency;
    /** 富壽繳別 */
    private String insuranceMop;
    /** 富壽已繳次數 */
    private int insurancePayTime;
    /** 富壽應繳保費 */
    private String insurancePrem;
    /** 富壽預估保單價值 */
    private String insuranceEstimatedPolicyValue;
    /** 富壽累計實繳保費 */
    private String insurancePaidPrem;
    /** 富壽累計提領金額 */
    private String insuranceDrawAmt;
    /** 富壽參考日期 */
    private String insuranceConfDate;
    /** 主附約 0:主約 1:附約 */
    private Integer insuranceMain;
    /** 對帳單金額 */
    private BigDecimal billNTD;
    /** 應交保費 */
    private BigDecimal accuPrem;

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceSeq() {
        return insuranceSeq;
    }

    public void setInsuranceSeq(String insuranceSeq) {
        this.insuranceSeq = insuranceSeq;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public void setInsurancePayTime(int insurancePayTime) {
        this.insurancePayTime = insurancePayTime;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
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

    public String getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(String insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public String getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public String getInsuranceUnit() {
        return insuranceUnit;
    }

    public void setInsuranceUnit(String insuranceUnit) {
        this.insuranceUnit = insuranceUnit;
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

    public String getInsurancePrem() {
        return insurancePrem;
    }

    public void setInsurancePrem(String insurancePrem) {
        this.insurancePrem = insurancePrem;
    }

    public String getInsuranceEstimatedPolicyValue() {
        return insuranceEstimatedPolicyValue;
    }

    public void setInsuranceEstimatedPolicyValue(String insuranceEstimatedPolicyValue) {
        this.insuranceEstimatedPolicyValue = insuranceEstimatedPolicyValue;
    }

    public String getInsurancePaidPrem() {
        return insurancePaidPrem;
    }

    public void setInsurancePaidPrem(String insurancePaidPrem) {
        this.insurancePaidPrem = insurancePaidPrem;
    }

    public String getInsuranceDrawAmt() {
        return insuranceDrawAmt;
    }

    public void setInsuranceDrawAmt(String insuranceDrawAmt) {
        this.insuranceDrawAmt = insuranceDrawAmt;
    }

    public String getInsuranceConfDate() {
        return insuranceConfDate;
    }

    public void setInsuranceConfDate(String insuranceConfDate) {
        this.insuranceConfDate = insuranceConfDate;
    }

    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public int getInsurancePayTime() {
        return insurancePayTime;
    }

    public Integer getInsuranceMain() {
        return insuranceMain;
    }

    public void setInsuranceMain(Integer insuranceMain) {
        this.insuranceMain = insuranceMain;
    }

    public BigDecimal getBillNTD() {
        return billNTD;
    }

    public void setBillNTD(BigDecimal billNTD) {
        this.billNTD = billNTD;
    }

    public BigDecimal getAccuPrem() {
        return accuPrem;
    }

    public void setAccuPrem(BigDecimal accuPrem) {
        this.accuPrem = accuPrem;
    }
}
