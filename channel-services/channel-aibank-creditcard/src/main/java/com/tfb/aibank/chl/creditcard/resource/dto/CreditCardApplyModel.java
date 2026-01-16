package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

/**
 * 信用卡開卡 model
 * 
 * @author Evan Wang
 *
 */
public class CreditCardApplyModel {
    /** 交易結果 0：交易成功 1：交易失敗 4：交易未明 */
    private String txnStatus;

    /** 開卡結果 */
    private String abndCode;

    /** 上傳主機時間 */
    private Date sendToHostTime;

    /** 錯誤代碼 */
    private String errorCode;

    /** 錯誤描述 */
    private String errorDesc;

    /** 錯誤系統代碼 */
    private String errorSystemId;

    /**
     * @return the txnStatus
     */
    public String getTxnStatus() {
        return txnStatus;
    }

    /**
     * @param txnStatus
     *            the txnStatus to set
     */
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    /**
     * @return the abndCode
     */
    public String getAbndCode() {
        return abndCode;
    }

    /**
     * @param abndCode
     *            the abndCode to set
     */
    public void setAbndCode(String abndCode) {
        this.abndCode = abndCode;
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
