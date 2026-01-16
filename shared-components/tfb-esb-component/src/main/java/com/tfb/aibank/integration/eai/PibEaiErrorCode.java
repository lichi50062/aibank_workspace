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

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;

// @formatter:off
/**
 * @(#)PibEaiErrorCode.java
 * 
 * <p>Description:網銀登入錯誤代碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum PibEaiErrorCode implements IErrorCode {

    STATUS_UNKNOWN("01", "交易不明確"),

    MISMATCH_ERROR("02", "系統回應不明，請查詢當日交易明細確認交易結果或電02-8751-6665,信用卡請撥(02)8751-1313,將有專人為您服務。"),

    TIMEOUT_ERROR("03", "EAI 回應逾時"),

    UNKNOWN_ERROR("99", "未知的EAI錯誤");

    /** 異常資料 */
    private ErrorStatus error = null;

    PibEaiErrorCode(String errorCode, String memo) {

        this(errorCode, memo, SeverityType.ERROR);

    }

    /**
     * 使用於Timeout錯誤 Constructor
     * 
     * @param errorCode
     * @param memo
     * @param severity
     */
    PibEaiErrorCode(String errorCode, String memo, SeverityType severity) {

        String eaiErrorCode = "EAI" + errorCode;

        error = new ErrorStatus("EAI", eaiErrorCode, severity, memo);

    }

    /**
     * 取得系統代碼
     * 
     * @return
     */
    public String getSystemId() {
        return error.getSystemId();
    }

    /**
     * 取得 錯誤代碼
     * 
     * @return
     */
    public String getErrorCode() {
        return error.getErrorCode();
    }

    /**
     * 取得備註說明
     * 
     * @return
     */
    public String getMemo() {
        return error.getErrorDesc();
    }

    @Override
    public ErrorStatus getError() {

        return error;
    }

}
