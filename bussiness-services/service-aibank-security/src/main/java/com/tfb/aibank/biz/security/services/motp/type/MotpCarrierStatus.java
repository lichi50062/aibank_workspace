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
* @(#)MotpCarrierStatus.java
* 
* <p>Description:MOTP交易推播載具驗證狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/29, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpCarrierStatus {

    INIT(0, "尚未發送"),

    GENERATE_SUCCESS(1, "成功產製離線載具OTP"),

    SEND_SUCCESS(1, "成功發送"),

    SEND_FAIL_WITH_OTP(12, "發送失敗，有回傳OTP"),

    SEND_FAIL_WITHOUT_OTP(13, "發送失敗，沒有回傳OTP"),

    VERIFY_SUCCESS(2, "驗證成功"),

    VERIFY_FAIL(3, "驗證失敗"),

    EXPIRE(4, "驗證逾時"),

    RESEND_EXPIRE(5, "因重新發送而此筆失效"),

    OTHERS(6, "其他驗證不通過"),

    VERIFYING(7, "MOTP驗證中"),

    SYSTEM_ERROR_PUSH_MOTP(8, "其他推播OTP發送失敗"),

    SYSTEM_ERROR_VALIDATE_MOTP(9, "其他推播OTP驗證失敗"),

    SYSTEM_ERROR(10, "系統發生錯誤");

    private int code;

    private String desc;

    MotpCarrierStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
