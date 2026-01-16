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
package com.tfb.aibank.biz.security.services.motp.type;

//@formatter:off
/**
* @(#)MotpTxCarrierType.java
* 
* <p>Description:推播OTP發送結果</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/15, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpSendResult {

    /**
     * 成功發送
     */
    MOTP_SEND_SUCCESS("0", "成功發送"),

    /**
     * 發送失敗，有回傳OTP
     */
    MOTP_SEND_FAIL_WITH_OTP("1", "發送失敗，有回傳OTP"),

    /**
     * 發送失敗，沒有回傳OTP
     */
    MOTP_SEND_FAIL_WITHOUT_OTP("2", "發送失敗，沒有回傳OTP"),

    /**
     * 其他推播OTP發送失敗
     */
    SYSTEM_ERROR_PUSH_MOTP("2", "其他推播OTP發送失敗"),

    ;

    /**
     * OTP發送結果 [0:成功 / 1:發送失敗可繼續驗證 / 2:發送失敗不可繼續驗證]
     */
    private String code;

    private String desc;

    MotpSendResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isValidStatus() {
        return equals(MOTP_SEND_SUCCESS) || equals(MOTP_SEND_FAIL_WITH_OTP);
    }

}
