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
import java.util.List;

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
public class CardLossTxnModel {
    /** 執行結果 */
    private String txStatus;

    /** 功能別 */
    private String func;

    /** 持卡人ID */
    private String cardHolderId;

    /** 正卡卡號 */
    private String primaryCardNo;

    /** 附卡卡號 */
    private List<String> supplementaryCardNos;

    /** 上傳主機時間 */
    private Date sendToHostTime;

    /** 錯誤代碼 */
    private String errorCode;

    /** 錯誤描述 */
    private String errorDesc;

    /** 錯誤系統代碼 */
    private String errorSystemId;

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
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the cardHolderId
     */
    public String getCardHolderId() {
        return cardHolderId;
    }

    /**
     * @param cardHolderId
     *            the cardHolderId to set
     */
    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    /**
     * @return the primaryCardNo
     */
    public String getPrimaryCardNo() {
        return primaryCardNo;
    }

    /**
     * @param primaryCardNo
     *            the primaryCardNo to set
     */
    public void setPrimaryCardNo(String primaryCardNo) {
        this.primaryCardNo = primaryCardNo;
    }

    /**
     * @return the supplementaryCardNos
     */
    public List<String> getSupplementaryCardNos() {
        return supplementaryCardNos;
    }

    /**
     * @param supplementaryCardNos
     *            the supplementaryCardNos to set
     */
    public void setSupplementaryCardNos(List<String> supplementaryCardNos) {
        this.supplementaryCardNos = supplementaryCardNos;
    }

    /**
     * @return the sendToHostTime
     */
    public Date getSendToHostTime() {
        return sendToHostTime;
    }

    /**
     * @param sendToHostTime
     *            the sendToHostTime to set
     */
    public void setSendToHostTime(Date sendToHostTime) {
        this.sendToHostTime = sendToHostTime;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorDesc
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * @param errorDesc
     *            the errorDesc to set
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * @return the errorSystemId
     */
    public String getErrorSystemId() {
        return errorSystemId;
    }

    /**
     * @param errorSystemId
     *            the errorSystemId to set
     */
    public void setErrorSystemId(String errorSystemId) {
        this.errorSystemId = errorSystemId;
    }

}
