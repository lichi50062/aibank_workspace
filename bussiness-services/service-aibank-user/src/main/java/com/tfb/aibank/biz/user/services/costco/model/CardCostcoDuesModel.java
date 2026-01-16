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
package com.tfb.aibank.biz.user.services.costco.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CardCostcoDuesModel.java
 * 
 * <p>Description:[好市多會費代扣繳申請]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "好市多會費代扣繳申請")
public class CardCostcoDuesModel {

    @Schema(description = "身分證字號")
    private String custId;

    @Schema(description = "公司類型")
    private Integer companyKind;

    @Schema(description = "誤別碼")
    private String uidDup;

    @Schema(description = "使用者代碼")
    private String userId;

    @Schema(description = "交易錯誤訊息")
    private String txErrorDesc;

    @Schema(description = "錯誤系統代碼")
    private String txErrorSystemId;

    @Schema(description = "建立時間")
    private Date createTime;

    @Schema(description = "上送主機交易時間")
    private Date hostTxTime;

    @Schema(description = "用戶代碼")
    private String nameCode;

    @Schema(description = "主檔鍵值")
    private Integer cardCostcoDuesKey;

    /** CEW466R回傳之COSTCO會員代碼 **/
    @Schema(description = "CEW466R回傳之COSTCO會員代碼")
    private String memberNo;

    /** CEW466R回傳之COSTCO卡別 **/
    @Schema(description = " CEW466R回傳之COSTCO卡別")
    private String memberCardType;

    @Schema(description = "交易日期")
    private Date txDate;

    @Schema(description = "交易錯誤代碼")
    private String txErrorCode;

    @Schema(description = "交易狀態 0：交易成功 1：交易失敗 4：交易未明")
    private String txStatus;

    @Schema(description = "更新時間")
    private Date updateTime;

    @Schema(description = "客戶IP")
    private String clientIp;

    @Schema(description = "TRACE_ID")
    private String traceId;

    private String errorCode466r;
    private String errorDesc466r;
    private String errorCode4661r;
    private String errorDesc4661r;

    /**
     * @return the errorCode466r
     */
    public String getErrorCode466r() {
        return errorCode466r;
    }

    /**
     * @param errorCode466r
     *            the errorCode466r to set
     */
    public void setErrorCode466r(String errorCode466r) {
        this.errorCode466r = errorCode466r;
    }

    /**
     * @return the errorDesc466r
     */
    public String getErrorDesc466r() {
        return errorDesc466r;
    }

    /**
     * @param errorDesc466r
     *            the errorDesc466r to set
     */
    public void setErrorDesc466r(String errorDesc466r) {
        this.errorDesc466r = errorDesc466r;
    }

    /**
     * @return the errorCode4661r
     */
    public String getErrorCode4661r() {
        return errorCode4661r;
    }

    /**
     * @param errorCode4661r
     *            the errorCode4661r to set
     */
    public void setErrorCode4661r(String errorCode4661r) {
        this.errorCode4661r = errorCode4661r;
    }

    /**
     * @return the errorDesc4661r
     */
    public String getErrorDesc4661r() {
        return errorDesc4661r;
    }

    /**
     * @param errorDesc4661r
     *            the errorDesc4661r to set
     */
    public void setErrorDesc4661r(String errorDesc4661r) {
        this.errorDesc4661r = errorDesc4661r;
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
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
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
     * @return the cardCostcoDuesKey
     */
    public Integer getCardCostcoDuesKey() {
        return cardCostcoDuesKey;
    }

    /**
     * @param cardCostcoDuesKey
     *            the cardCostcoDuesKey to set
     */
    public void setCardCostcoDuesKey(Integer cardCostcoDuesKey) {
        this.cardCostcoDuesKey = cardCostcoDuesKey;
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
     * @return the memberNo
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo
     *            the memberNo to set
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * @return the memberCardType
     */
    public String getMemberCardType() {
        return memberCardType;
    }

    /**
     * @param memberCardType
     *            the memberCardType to set
     */
    public void setMemberCardType(String memberCardType) {
        this.memberCardType = memberCardType;
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
