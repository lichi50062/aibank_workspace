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
package com.tfb.aibank.chl.type;

import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)TxSecurityStepType.java
* 
* <p>Description:交易安控機制 - 執行步驟類別</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum TxSecurityStepType implements IEnum {

    INIT_OK(0, "初始化完成"),

    // OTP
    OTP_OPTION_READY(11, "已設定簡訊OTP驗證初始資料"),
    //
    OTP_SEND(21, "已發送簡訊OTP"),
    //
    OTP_AUTH_SUCCESS(31, "OTP驗證成功"),
    //
    OTP_AUTH_FAIL(32, "OTP驗證失敗"),

    // MOTP
    MOTP_OPTION_READY(61, "已設定MOTP驗證初始資料"),
    //
    MOTP_SEND(71, "已發送推播OTP"),
    //
    MOTP_AUTH_SUCCESS(81, "OTP驗證成功"),
    //
    MOTP_AUTH_FAIL(82, "OTP驗證失敗"),

    UNKNOWN(-1, "UNKNOWN")

    ;

    /** 類型 */
    private Integer type;

    /** 說明* */
    private String memo;

    /**
     * Constructor
     * 
     * @param type
     * @param memo
     */
    TxSecurityStepType(Integer type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    /**
     * @return
     */
    public Integer getType() {
        return this.type;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public boolean isInitOK() {
        return equals(INIT_OK);
    }

    public boolean isOTPOptionReady() {
        return equals(OTP_OPTION_READY);
    }

    public boolean isOTPSend() {
        return equals(OTP_SEND);
    }

    public boolean isOTPAuthSuccess() {
        return equals(OTP_AUTH_SUCCESS);
    }

    public boolean isOTPAuthFail() {
        return equals(OTP_AUTH_FAIL);
    }

    public boolean isMOTPOptionReady() {
        return equals(MOTP_OPTION_READY);
    }

    public boolean isMOTPSend() {
        return equals(MOTP_SEND);
    }

    public boolean isMOTPAuthSuccess() {
        return equals(MOTP_AUTH_SUCCESS);
    }

    public boolean isMOTPAuthFail() {
        return equals(MOTP_AUTH_FAIL);
    }

}
