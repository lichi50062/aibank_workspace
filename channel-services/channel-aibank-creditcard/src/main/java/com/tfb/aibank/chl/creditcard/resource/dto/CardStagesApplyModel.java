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
 * @(#)CardStagesApplyModel.java
 * 
 * <p>Description:單筆分期主檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardStagesApplyModel {
    /**
     * 刷卡原始授權碼
     */
    private String authCode;

    /**
     * 信用卡背面簽名條後3碼
     */
    private String cardCvv2;

    /**
     * 信用卡卡號
     */
    private String cardNo;

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
     * 手續費率
     */
    private BigDecimal feeRate;

    /**
     * 上送主機交易時間
     */
    private Date hostTxTime;

    /**
     * 用戶代碼
     */
    private String nameCode;

    /**
     * 入帳日期
     */
    private Date nccDate;

    /**
     * 產生組別
     */
    private String ncgrop;

    /**
     * 扣帳順序
     */
    private String ncsbsq;

    /**
     * 交易序號
     */
    private Integer ncseqn;

    /**
     * 主檔鍵值
     */
    private Integer stagesKey;

    /**
     * 分期期數
     */
    private Integer stagesNumber;

    /**
     * 分期種類
     */
    private String stagesType;

    /**
     * 臺幣金額
     */
    private Long twdAmt;

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
     * 交易存取記錄追蹤編號
     */
    private String traceId;

    /**
     * 交易存取異常記錄
     */
    private ActionException aex;

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode
     *            the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the cardCvv2
     */
    public String getCardCvv2() {
        return cardCvv2;
    }

    /**
     * @param cardCvv2
     *            the cardCvv2 to set
     */
    public void setCardCvv2(String cardCvv2) {
        this.cardCvv2 = cardCvv2;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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
     * @return the feeRate
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * @param feeRate
     *            the feeRate to set
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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
     * @return the nccDate
     */
    public Date getNccDate() {
        return nccDate;
    }

    /**
     * @param nccDate
     *            the nccDate to set
     */
    public void setNccDate(Date nccDate) {
        this.nccDate = nccDate;
    }

    /**
     * @return the ncgrop
     */
    public String getNcgrop() {
        return ncgrop;
    }

    /**
     * @param ncgrop
     *            the ncgrop to set
     */
    public void setNcgrop(String ncgrop) {
        this.ncgrop = ncgrop;
    }

    /**
     * @return the ncsbsq
     */
    public String getNcsbsq() {
        return ncsbsq;
    }

    /**
     * @param ncsbsq
     *            the ncsbsq to set
     */
    public void setNcsbsq(String ncsbsq) {
        this.ncsbsq = ncsbsq;
    }

    /**
     * @return the ncseqn
     */
    public Integer getNcseqn() {
        return ncseqn;
    }

    /**
     * @param ncseqn
     *            the ncseqn to set
     */
    public void setNcseqn(Integer ncseqn) {
        this.ncseqn = ncseqn;
    }

    /**
     * @return the stagesKey
     */
    public Integer getStagesKey() {
        return stagesKey;
    }

    /**
     * @param stagesKey
     *            the stagesKey to set
     */
    public void setStagesKey(Integer stagesKey) {
        this.stagesKey = stagesKey;
    }

    /**
     * @return the stagesNumber
     */
    public Integer getStagesNumber() {
        return stagesNumber;
    }

    /**
     * @param stagesNumber
     *            the stagesNumber to set
     */
    public void setStagesNumber(Integer stagesNumber) {
        this.stagesNumber = stagesNumber;
    }

    /**
     * @return the stagesType
     */
    public String getStagesType() {
        return stagesType;
    }

    /**
     * @param stagesType
     *            the stagesType to set
     */
    public void setStagesType(String stagesType) {
        this.stagesType = stagesType;
    }

    /**
     * @return the twdAmt
     */
    public Long getTwdAmt() {
        return twdAmt;
    }

    /**
     * @param twdAmt
     *            the twdAmt to set
     */
    public void setTwdAmt(Long twdAmt) {
        this.twdAmt = twdAmt;
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
