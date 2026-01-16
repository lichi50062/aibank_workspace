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
package com.tfb.aibank.biz.user.repository.piblog.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)CardCostcoDuesEntity.java
 * 
 * <p>Description:[COSTCO會費代扣申請紀錄Table]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "CARD_COSTCO_DUES")
public class CardCostcoDuesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARD_COSTCO_DUES_SEQ")
    @SequenceGenerator(name = "CARD_COSTCO_DUES_SEQ", sequenceName = "CARD_COSTCO_DUES_SEQ", allocationSize = 1)
    @Column(name = "COSTCO_DUES_KEY")
    /** 主檔鍵值 **/
    private Integer costcoDuesKey;

    /** 交易存取記錄鍵值 **/
    @Basic
    @Column(name = "ACCESS_LOG_KEY")
    private Integer accessLogKey;

    /** 公司鍵值 **/
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 用戶代碼 **/
    @Basic
    @Column(name = "NAME_CODE")
    private String nameCode;

    /** 使用者鍵值 **/
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 使用者代碼 **/
    @Basic
    @Column(name = "USER_ID")
    private String userId;

    /** 客戶IP **/
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** CEW466R回傳之COSTCO會員代碼 **/
    @Basic
    @Column(name = "MEMBER_NO")
    private String memberNo;

    /** CEW466R回傳之COSTCO卡別 **/
    @Basic
    @Column(name = "MEMBER_CARD_TYPE")
    private String memberCardType;

    /** CEW466R約定代扣(發10)之交易日期 **/
    @Basic
    @Column(name = "CEW466R_TX_DATE")
    private Date cew466rTxDate;

    /** CEW466R約定代扣(發10)之申請時間 **/
    @Basic
    @Column(name = "CEW466R_TX_TIME")
    private Date cew466rTxTime;

    /** CEW466R約定代扣(發10)之交易狀態 **/
    @Basic
    @Column(name = "CEW466R_TX_STATUS")
    private String cew466rTxStatus;

    /** CEW466R約定代扣(發10)之交易錯誤代碼 **/
    @Basic
    @Column(name = "CEW466R_TX_ERROR_CODE")
    private String cew466rTxErrorCode;

    /** CEW466R約定代扣(發10)之交易錯誤訊息 **/
    @Basic
    @Column(name = "CEW466R_TX_ERROR_DESC")
    private String cew466rTxErrorDesc;

    /** CEW4661R之交易日期 **/
    @Basic
    @Column(name = "CEW4661R_TX_DATE")
    private Date cew4661rTxDate;

    /** CEW4661R之申請時間 **/
    @Basic
    @Column(name = "CEW4661R_TX_TIME")
    private Date cew4661rTxTime;

    /** CEW4661R之交易狀態 **/
    @Basic
    @Column(name = "CEW4661R_TX_STATUS")
    private String cew4661rTxStatus;

    /** CEW4661R之交易錯誤代碼 **/
    @Basic
    @Column(name = "CEW4661R_TX_ERROR_CODE")
    private String cew4661rTxErrorCode;

    /** CEW4661R之交易錯誤訊息 **/
    @Basic
    @Column(name = "CEW4661R_TX_ERROR_DESC")
    private String cew4661rTxErrorDesc;

    /** 交易來源:1網銀 2行銀 **/
    @Basic
    @Column(name = "TX_SOURCE")
    private String txSource;

    /** 建立時間 **/
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 更新時間 **/
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 交易存取記錄追蹤編號
     */
    @Basic
    @Column(name = "TRACE_ID")
    private String traceId;

    public Integer getCostcoDuesKey() {
        return costcoDuesKey;
    }

    public void setCostcoDuesKey(Integer costcoDuesKey) {
        this.costcoDuesKey = costcoDuesKey;
    }

    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberCardType() {
        return memberCardType;
    }

    public void setMemberCardType(String memberCardType) {
        this.memberCardType = memberCardType;
    }

    public Date getCew466rTxDate() {
        return cew466rTxDate;
    }

    public void setCew466rTxDate(Date cew466rTxDate) {
        this.cew466rTxDate = cew466rTxDate;
    }

    public Date getCew466rTxTime() {
        return cew466rTxTime;
    }

    public void setCew466rTxTime(Date cew466rTxTime) {
        this.cew466rTxTime = cew466rTxTime;
    }

    public String getCew466rTxStatus() {
        return cew466rTxStatus;
    }

    public void setCew466rTxStatus(String cew466rTxStatus) {
        this.cew466rTxStatus = cew466rTxStatus;
    }

    public String getCew466rTxErrorCode() {
        return cew466rTxErrorCode;
    }

    public void setCew466rTxErrorCode(String cew466rTxErrorCode) {
        this.cew466rTxErrorCode = cew466rTxErrorCode;
    }

    public String getCew466rTxErrorDesc() {
        return cew466rTxErrorDesc;
    }

    public void setCew466rTxErrorDesc(String cew466rTxErrorDesc) {
        this.cew466rTxErrorDesc = cew466rTxErrorDesc;
    }

    public Date getCew4661rTxDate() {
        return cew4661rTxDate;
    }

    public void setCew4661rTxDate(Date cew4661rTxDate) {
        this.cew4661rTxDate = cew4661rTxDate;
    }

    public Date getCew4661rTxTime() {
        return cew4661rTxTime;
    }

    public void setCew4661rTxTime(Date cew4661rTxTime) {
        this.cew4661rTxTime = cew4661rTxTime;
    }

    public String getCew4661rTxStatus() {
        return cew4661rTxStatus;
    }

    public void setCew4661rTxStatus(String cew4661rTxStatus) {
        this.cew4661rTxStatus = cew4661rTxStatus;
    }

    public String getCew4661rTxErrorCode() {
        return cew4661rTxErrorCode;
    }

    public void setCew4661rTxErrorCode(String cew4661rTxErrorCode) {
        this.cew4661rTxErrorCode = cew4661rTxErrorCode;
    }

    public String getCew4661rTxErrorDesc() {
        return cew4661rTxErrorDesc;
    }

    public void setCew4661rTxErrorDesc(String cew4661rTxErrorDesc) {
        this.cew4661rTxErrorDesc = cew4661rTxErrorDesc;
    }

    public String getTxSource() {
        return txSource;
    }

    public void setTxSource(String txSource) {
        this.txSource = txSource;
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
