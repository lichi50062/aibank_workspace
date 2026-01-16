package com.tfb.aibank.biz.component.videoauthapi.model;

import java.util.Date;

// @formatter:off
/**
 * @(#)VideoAuthResult.java
 * 
 * <p>Description:[視訊驗證結果]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VideoAuthResult {
    // 執行電文交易回傳 start
    private String txStatus;

    /** 錯誤系統代號 */
    private String errorSystemId;

    /** 錯誤系統代碼 */
    private String errorCode;

    /** 錯誤系統說明 */
    private String errorMessage;;

    /** 主機交易時間 */
    private Date hostTxTime;

    /** 是否成功 */
    private boolean isSuccess;

    /** 是否失敗 */
    private boolean isError;

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * @return the isError
     */
    public boolean isError() {
        return isError;
    }

    /**
     * @param isError
     *            the isError to set
     */
    public void setError(boolean isError) {
        this.isError = isError;
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
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
}
