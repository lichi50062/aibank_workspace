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
package com.tfb.aibank.biz.security.services.mid.type;

//@formatter:off
/**
* @(#)MidDataStatusType.java
* 
* <p>Description:台網MID驗證 - 驗證狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MidDataStatusType {

    INIT(0, "準備驗證SIM卡資訊"),

    LOGIN_ERROR(1, "MID登入錯誤"),

    LOGIN_SUCCESS(2, "MID登入-成功取得認證資訊"),

    LOGIN_FAIL(3, "MID登入失敗"),

    LOGIN_INDENTITY_NO_INVALID(4, "MID登入-identityNo驗證失敗"),

    MID_AUTH_SUCCESS(5, "SDK_驗證成功"),

    MID_AUTH_FAIL(6, "SDK驗證失敗"),

    VERIFY_ERROR(7, "MID驗證錯誤"),

    VERIFY_SUCCESS(8, "MID驗證成功"),

    VERIFY_FAIL(9, "MID驗證失敗"),

    VERIFY_INDENTITY_NO_INVALID(10, "MID驗證-identityNo驗證失敗");

    private int code;

    private String desc;

    MidDataStatusType(int code, String desc) {
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
