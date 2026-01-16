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

import com.ibm.tw.commons.error.IErrorCode;

// @formatter:off
/**
 * @(#)EAIException.java
 * 
 * <p>Description:電文例外處理</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@SuppressWarnings("serial")
public class EAIException extends Exception {

    private IErrorCode errorCode = PibEaiErrorCode.UNKNOWN_ERROR;

    /** 額外的代碼 ex:電文X開頭的錯誤代碼 */
    private String extraCode = "";

    /**
     * EAIException 預設建構子
     */
    public EAIException() {
        super();
    }

    /**
     * EAIException 建構子
     * 
     * @param message
     *            例外訊息
     */
    public EAIException(String message) {
        super(message);
    }

    /**
     * EAIException 建構子
     * 
     * @param message
     *            例外訊息
     * @param cause
     *            根源例外元素
     */
    public EAIException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * EAIException 建構子
     * 
     * @param cause
     *            根源例外元素
     */
    public EAIException(Throwable cause) {
        super(cause);
    }

    public EAIException(PibEaiErrorCode errorCode, String message, String extraCode) {
        super(message);
        this.errorCode = errorCode;
        this.extraCode = extraCode;
    }

    public EAIException(PibEaiErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 取得 errorCode
     * 
     * @return 傳回 errorCode。
     */
    public IErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * 取得 extraCode
     * 
     * @return 傳回 extraCode。
     */
    public String getExtraCode() {
        return extraCode;
    }

    /**
     * 設定 extraCode
     * 
     * @param extraCode
     *            要設定的 extraCode。
     */
    public void setExtraCode(String extraCode) {
        this.extraCode = extraCode;
    }
}
