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
package com.tfb.aibank.biz.user.services.email.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)SaveChangeCustDataRequest.java
 * 
 * <p>Description:Email變更後儲存客戶資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/28, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "Email變更後儲存客戶資料")
public class SaveChangeCustDataRequest {
    /** 公司鍵值 */
    @Schema(description = "公司鍵值")
    private Integer companyKey;
    /** 用戶代碼 */
    @Schema(description = "用戶代碼")
    private String nameCode;
    /** 使用者鍵值 */
    @Schema(description = "使用者鍵值")
    private Integer userKey;
    /** 變更原因代碼 */
    @Schema(description = "變更原因代碼")
    private String reasonCode;
    /** 變更原因 */
    @Schema(description = "變更原因")
    private String reason;
    /** 交易狀態 */
    @Schema(description = "交易狀態")
    private String txStatus;
    /** 重新發送 */
    @Schema(description = "重新發送")
    private String resentMVC110001;
    /** 交易錯誤訊息 */
    @Schema(description = "交易錯誤訊息")
    private String txErrorDesc;
    /** 錯誤系統代碼 */
    @Schema(description = "錯誤系統代碼")
    private String txErrorSystemId;
    /** 交易錯誤代碼 */
    @Schema(description = "交易錯誤代碼")
    private String txErrorCode;
    /** 上送主機交易時間 */
    @Schema(description = "上送主機交易時間")
    private Date hostTxTime;
    /** 交易來源 */
    @Schema(description = "交易來源")
    private String txSource;

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
     * @return the resentMVC110001
     */
    public String getResentMVC110001() {
        return resentMVC110001;
    }

    /**
     * @param resentMVC110001
     *            the resentMVC110001 to set
     */
    public void setResentMVC110001(String resentMVC110001) {
        this.resentMVC110001 = resentMVC110001;
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

}
