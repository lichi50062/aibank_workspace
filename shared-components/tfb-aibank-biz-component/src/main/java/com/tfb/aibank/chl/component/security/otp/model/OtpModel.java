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
package com.tfb.aibank.chl.component.security.otp.model;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)OtpModel.java
 * 
 * <p>Description:OTP發送紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpModel implements Serializable {

    private static final long serialVersionUID = 1290648599856451265L;

    /**
     * OTP log key
     */
    private Integer otpKey;

    /**
     * Access log key
     */
    private Integer acessLogKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 有效時間
     */
    private Date expireTime;

    /**
     * 產生密碼時間
     */
    private Date genPassTime;

    /**
     * 端末交易序號
     */
    private String jrnNo;

    /**
     * 手機號碼
     */
    private String mobile;

    /**
     * 操作當下的戶名代碼
     */
    private String nameCode;

    /**
     * OTP密碼
     */
    private String otpPass;

    /** 
     * 
     */
    private String picVerify = "0";

    /**
     * 交易狀態
     */
    private String status;

    /**
     * 狀態時間1
     */
    private Date statusTime;

    /**
     * 重試次數
     */
    private Integer tryCount;

    /**
     * 交易編號
     */
    private String txCode;

    /**
     * 交易資料
     */
    private String txData;

    /**
     * 程式代號
     */
    private String txId;

    /**
     * 身分證字號
     */
    private String custId;

    /**
     * 使用者代號
     */
    private String userId;

    /**
     * 公司類型
     */
    private int companyKind;

    /**
     * 誤別碼
     */
    private String uidDup;

    /**
     * @return the otpKey
     */
    public Integer getOtpKey() {
        return otpKey;
    }

    /**
     * @param otpKey
     *            the otpKey to set
     */
    public void setOtpKey(Integer otpKey) {
        this.otpKey = otpKey;
    }

    /**
     * @return the acessLogKey
     */
    public Integer getAcessLogKey() {
        return acessLogKey;
    }

    /**
     * @param acessLogKey
     *            the acessLogKey to set
     */
    public void setAcessLogKey(Integer acessLogKey) {
        this.acessLogKey = acessLogKey;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the expireTime
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return the genPassTime
     */
    public Date getGenPassTime() {
        return genPassTime;
    }

    /**
     * @param genPassTime
     *            the genPassTime to set
     */
    public void setGenPassTime(Date genPassTime) {
        this.genPassTime = genPassTime;
    }

    /**
     * @return the jrnNo
     */
    public String getJrnNo() {
        return jrnNo;
    }

    /**
     * @param jrnNo
     *            the jrnNo to set
     */
    public void setJrnNo(String jrnNo) {
        this.jrnNo = jrnNo;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the otpPass
     */
    public String getOtpPass() {
        return otpPass;
    }

    /**
     * @param otpPass
     *            the otpPass to set
     */
    public void setOtpPass(String otpPass) {
        this.otpPass = otpPass;
    }

    /**
     * @return the picVerify
     */
    public String getPicVerify() {
        return picVerify;
    }

    /**
     * @param picVerify
     *            the picVerify to set
     */
    public void setPicVerify(String picVerify) {
        this.picVerify = picVerify;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the statusTime
     */
    public Date getStatusTime() {
        return statusTime;
    }

    /**
     * @param statusTime
     *            the statusTime to set
     */
    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    /**
     * @return the tryCount
     */
    public Integer getTryCount() {
        return tryCount;
    }

    /**
     * @param tryCount
     *            the tryCount to set
     */
    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    /**
     * @return the txCode
     */
    public String getTxCode() {
        return txCode;
    }

    /**
     * @param txCode
     *            the txCode to set
     */
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    /**
     * @return the txData
     */
    public String getTxData() {
        return txData;
    }

    /**
     * @param txData
     *            the txData to set
     */
    public void setTxData(String txData) {
        this.txData = txData;
    }

    /**
     * @return the txId
     */
    public String getTxId() {
        return txId;
    }

    /**
     * @param txId
     *            the txId to set
     */
    public void setTxId(String txId) {
        this.txId = txId;
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

}
