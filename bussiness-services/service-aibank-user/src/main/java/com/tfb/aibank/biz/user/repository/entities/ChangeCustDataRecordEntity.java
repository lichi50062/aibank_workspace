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
package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.ChangeCustDataRecordEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)ChangeCustDataRecordEntity.java
 * 
 * <p>Description:變更個人資料紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "CHANGE_CUST_DATA_RECORD")
@EntityListeners(ChangeCustDataRecordEntityListener.class)
public class ChangeCustDataRecordEntity {
    /**
     * 資料鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHANGE_CUST_DATA_RECORD_SEQ")
    @SequenceGenerator(name = "CHANGE_CUST_DATA_RECORD_SEQ", sequenceName = "CHANGE_CUST_DATA_RECORD_SEQ", allocationSize = 1)
    @Column(name = "RECORD_KEY")
    private Integer recordKey;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 用戶代碼
     */
    @Basic
    @Column(name = "NAME_CODE")
    private String nameCode;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 使用者代碼 */
    @Basic
    @Column(name = "USER_ID")
    private String userId;

    /** 變更項目 */
    @Basic
    @Column(name = "CHANGE_ITEM")
    private String changeItem;

    /** 客戶email */
    @Basic
    @Column(name = "CUST_EMAIL")
    private String custEmail;

    /** 交易日期 */
    @Basic
    @Column(name = "TX_DATE")
    private Date txDate;

    /** 交易狀態 */
    @Basic
    @Column(name = "TX_STATUS")
    private String txStatus;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 建立時間 */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /** 客戶IP */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** 交易來源 */
    @Basic
    @Column(name = "TX_SOURCE")
    private String txSource;

    /** 上送主機交易時間 */
    @Basic
    @Column(name = "HOST_TX_TIME")
    private Date hostTxTime;

    /** 交易錯誤訊息 */
    @Basic
    @Column(name = "TX_ERROR_DESC")
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    @Basic
    @Column(name = "TX_ERROR_SYSTEM_ID")
    private String txErrorSystemId;

    /** 交易錯誤代碼 */
    @Basic
    @Column(name = "TX_ERROR_CODE")
    private String txErrorCode;

    /** 變更原因代碼 */
    @Basic
    @Column(name = "REASON_CODE")
    private String reasonCode;

    /** 變更前Email */
    @Basic
    @Column(name = "OLD_CUST_EMAIL")
    private String oldCustEmail;

    /** 變更原因 */
    @Basic
    @Column(name = "REASON")
    private String reason;

    /** 重新發送MVC110001 */
    @Basic
    @Column(name = "RESEND_MVC110001")
    private String resendMvc110001;

    /**
     * @return the recordKey
     */
    public Integer getRecordKey() {
        return recordKey;
    }

    /**
     * @param recordKey
     *            the recordKey to set
     */
    public void setRecordKey(Integer recordKey) {
        this.recordKey = recordKey;
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
     * @return the changeItem
     */
    public String getChangeItem() {
        return changeItem;
    }

    /**
     * @param changeItem
     *            the changeItem to set
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    /**
     * @return the custEmail
     */
    public String getCustEmail() {
        return custEmail;
    }

    /**
     * @param custEmail
     *            the custEmail to set
     */
    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
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
     * @return the reasonCode
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * @param reasonCode
     *            the reasonCode to set
     */
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    /**
     * @return the oldCustEmail
     */
    public String getOldCustEmail() {
        return oldCustEmail;
    }

    /**
     * @param oldCustEmail
     *            the oldCustEmail to set
     */
    public void setOldCustEmail(String oldCustEmail) {
        this.oldCustEmail = oldCustEmail;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the resendMvc110001
     */
    public String getResendMvc110001() {
        return resendMvc110001;
    }

    /**
     * @param resendMvc110001
     *            the resendMvc110001 to set
     */
    public void setResendMvc110001(String resendMvc110001) {
        this.resendMvc110001 = resendMvc110001;
    }

}
