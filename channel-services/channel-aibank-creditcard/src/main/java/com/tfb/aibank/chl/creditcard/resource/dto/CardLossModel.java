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

import java.util.Date;

// @formatter:off
/**
 * @(#)CardLossModel.java
 * 
 * <p>Description:信用卡掛失Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardLossModel {
    /** 資料鍵值 */
    private Long lossKey;

    /** 用戶代碼 */
    private String nameCode;

    /** 身分證號 */
    private String custId;

    /** 公司類型 */
    private Integer companyKind;

    /** 使用者代碼 */
    private String userId;

    /** 信用卡卡號 */
    private String cardNo;

    /** 上送主機交易時間 */
    private Date hostTxTime;

    /** 交易狀態 */
    private String txStatus;

    /** 交易錯誤代碼 */
    private String txErrorCode;

    /** 交易日期 */
    private Date txDate;

    /** 建立時間 */
    private Date createTime;

    /** 更新時間 */
    private Date updateTime;

    /** 交易存取記錄鍵值 */
    private Integer accessLogKey;

    /** 交易錯誤訊息 */
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    private String txErrorSystemId;

    /** 客戶IP */
    private String clientIp;

    /** 交易來源 */
    private String txSource;

    /**
     * 交易存取記錄追蹤編號
     */
    private String traceId;

    public Long getLossKey() {
        return lossKey;
    }

    public void setLossKey(Long lossKey) {
        this.lossKey = lossKey;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    public String getTxStatus() {
        return txStatus;
    }

    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    public String getTxErrorCode() {
        return txErrorCode;
    }

    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
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

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTxSource() {
        return txSource;
    }

    public void setTxSource(String txSource) {
        this.txSource = txSource;
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

}
