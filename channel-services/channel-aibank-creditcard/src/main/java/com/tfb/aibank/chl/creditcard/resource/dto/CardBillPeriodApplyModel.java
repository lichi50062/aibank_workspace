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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.exception.ActionException;

// @formatter:off
/**
 * @(#)CardBillPeriodApplyModel.java
 * 
 * <p>Description:帳單分期主檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardBillPeriodApplyModel {
    /**
     * 交易存取記錄鍵值
     */
    private Integer accessLogKey;

    /**
     * 主檔鍵值
     */
    private Integer billKey;

    /**
     * 帳單年月
     */
    private String billYyyymm;

    /**
     * 客戶IP
     */
    private String clientIp;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 上送主機交易時間
     */
    private Date hostTxTime;

    /**
     * 手續費率
     */
    private BigDecimal interestRate;

    /**
     * 用戶代碼
     */
    private String nameCode;

    /**
     * 分期期數
     */
    private Integer period;

    /**
     * 交易編號
     */
    private String refNo;

    /**
     * 交易存取記錄追蹤編號
     */
    private String traceId;

    /**
     * 交易日期
     */
    private Date txDate;

    /**
     * 交易錯誤代碼
     */
    private String txErrorCode;

    /**
     * 交易錯誤訊息
     */
    private String txErrorDesc;

    /**
     * 錯誤系統代碼
     */
    private String txErrorSystemId;

    /**
     * 交易來源
     */
    private String txSource;

    /**
     * 交易狀態
     */
    private String txStatus;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 使用者代碼
     */
    private String userId;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 交易存取異常記錄
     */
    private ActionException aex;

    /**
     * @return the accessLogKey
     */
    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    /**
     * @param accessLogKey
     *            the accessLogKey to set
     */
    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * @return the billKey
     */
    public Integer getBillKey() {
        return billKey;
    }

    /**
     * @param billKey
     *            the billKey to set
     */
    public void setBillKey(Integer billKey) {
        this.billKey = billKey;
    }

    /**
     * @return the billYyyymm
     */
    public String getBillYyyymm() {
        return billYyyymm;
    }

    /**
     * @param billYyyymm
     *            the billYyyymm to set
     */
    public void setBillYyyymm(String billYyyymm) {
        this.billYyyymm = billYyyymm;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
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
     * @return the hostTxTime
     */
    public Date getHostTxTime() {
        return hostTxTime;
    }

    /**
     * @param hostTxTime
     *            the hostTxTime to set
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * @return the interestRate
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate
     *            the interestRate to set
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return the refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * @param refNo
     *            the refNo to set
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * @return the txDate
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * @param txDate
     *            the txDate to set
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * @return the txErrorCode
     */
    public String getTxErrorCode() {
        return txErrorCode;
    }

    /**
     * @param txErrorCode
     *            the txErrorCode to set
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * @return the txErrorDesc
     */
    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    /**
     * @param txErrorDesc
     *            the txErrorDesc to set
     */
    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    /**
     * @return the txErrorSystemId
     */
    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    /**
     * @param txErrorSystemId
     *            the txErrorSystemId to set
     */
    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    /**
     * @return the txSource
     */
    public String getTxSource() {
        return txSource;
    }

    /**
     * @param txSource
     *            the txSource to set
     */
    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

    /**
     * @return the txStatus
     */
    public String getTxStatus() {
        return txStatus;
    }

    /**
     * @param txStatus
     *            the txStatus to set
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the aex
     */
    public ActionException getAex() {
        return aex;
    }

    /**
     * @param aex
     *            the aex to set
     */
    public void setAex(ActionException aex) {
        this.aex = aex;
    }

}
