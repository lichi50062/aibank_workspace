/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai;

// @formatter:off
/**
 * @(#)EAIResponseException.java
 * 
 * <p>Description:下行電文狀態例外</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@SuppressWarnings({ "serial", "rawtypes" })
public class EAIResponseException extends Exception {

    /** 主機下行錯誤碼 */
    private String errorSystemId;

    /** 主機下行錯誤碼 */
    private String errorCode;

    /** 主機下行錯誤訊息 */
    private String errorMessage;

    /** 回覆電文 */
    private EAIResponse response;

    /** 電文服務名稱 */
    private String txId;

    /**
     * EAIResponseException 預設建構子
     */
    public EAIResponseException(String errorSystemId, String errorCode, String errorMessage, EAIResponse response) {
        super(errorCode + "-" + errorMessage);
        this.errorSystemId = errorSystemId;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.response = response;
        if (response != null) {
            this.txId = response.getHeader().getHTXTID();
        }
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EAIResponse getResponse() {
        return response;
    }

    public String getTxId() {
        return txId;
    }

    /**
     * 取得 errorSystemId
     *
     * @return 傳回 errorSystemId。
     */
    public String getErrorSystemId() {
        return errorSystemId;
    }

    /**
     * 設定 errorCode
     *
     * @param errorCode
     *            要設定的 errorCode。
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 設定 errorMessage
     * 
     * @param errorMessage
     *            要設定的 errorMessage。
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
