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
public class CardActivateModel {

    /** 資料鍵值 */
    private long activateKey;

    /** 公司類型 */
    private Integer companyKind;

    /** 身分證號 */
    private String custId;

    /** 用戶代碼 */
    private String nameCode;

    /** 使用者代碼 */
    private String userId;

    /** 信用卡卡號 */
    private String cardNo;

    /** 信用卡背面簽名條後3碼 */
    private String cardCvv2;

    /** 生日 */
    private Date birthday;

    /** 信用卡有效期限 */
    private String cardNedy;

    /** 交易存取記錄鍵值 */
    private Integer accessLogKey;

    /** 客戶IP */
    private String clientIp;

    /** 交易來源 */
    private String txSource;

    /** 交易狀態 */
    private String txStatus;

    /** 建立時間 */
    private Date createTime;

    /** 交易日期 */
    private Date txDate;

    /** 更新時間 */
    private Date updateTime;

    /** 上送主機交易時間 */
    private Date hostTxTime;

    /** 交易錯誤代碼 */
    private String txErrorCode;

    /** 交易錯誤訊息 */
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    private String txErrorSystemId;

    /**
     * 交易存取記錄追蹤編號
     */
    private String traceId;

    public long getActivateKey() {
        return activateKey;
    }

    public void setActivateKey(long activateKey) {
        this.activateKey = activateKey;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
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

    public String getCardCvv2() {
        return cardCvv2;
    }

    public void setCardCvv2(String cardCvv2) {
        this.cardCvv2 = cardCvv2;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardNedy() {
        return cardNedy;
    }

    public void setCardNedy(String cardNedy) {
        this.cardNedy = cardNedy;
    }

    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
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

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getHostTxTime() {
        return hostTxTime;
    }

    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
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
