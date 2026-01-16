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

// @formatter:off
/**
 * @(#)BankOperationOffer.java
 * 
 * <p>Description:每月銀行作業優惠</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class BankOperationOffer {

    /**
     * 功能
     */
    private int function;
    /**
     * 證件類型/號碼
     */
    private String certType;
    /**
     * 證件類型/號碼
     */
    private String certIdNo;
    /**
     * SOC
     */
    private int socNo;
    /**
     * 帳號
     */
    private String acctNo;
    /**
     * 客戶VIPCode
     */
    private String vipCode;
    /**
     * 費用類型
     */
    private String feeType;
    /**
     * 累計器編號
     */
    private int accumNo;
    /**
     * 累計次數
     */
    private int accrCount;
    /**
     * 剩餘次數
     */
    private int resCount;
    /**
     * 增加/減少次數
     */
    private int addSubCount;

    /**
     * 總次數
     */
    private int resCountTotal;

    public void organizeData() {
        resCountTotal = resCount + accrCount;
    }

    public int getAccrCount() {
        return accrCount;
    }

    public void setAccrCount(int accrCount) {
        this.accrCount = accrCount;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public int getAccumNo() {
        return accumNo;
    }

    public void setAccumNo(int accumNo) {
        this.accumNo = accumNo;
    }

    public int getAddSubCount() {
        return addSubCount;
    }

    public void setAddSubCount(int addSubCount) {
        this.addSubCount = addSubCount;
    }

    public String getCertIdNo() {
        return certIdNo;
    }

    public void setCertIdNo(String certIdNo) {
        this.certIdNo = certIdNo;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public int getResCount() {
        return resCount;
    }

    public void setResCount(int resCount) {
        this.resCount = resCount;
    }

    public int getSocNo() {
        return socNo;
    }

    public void setSocNo(int socNo) {
        this.socNo = socNo;
    }

    public String getVipCode() {
        return vipCode;
    }

    public void setVipCode(String vipCode) {
        this.vipCode = vipCode;
    }

    public int getResCountTotal() {
        return resCountTotal;
    }

    public void setResCountTotal(int resCountTotal) {
        this.resCountTotal = resCountTotal;
    }
}
