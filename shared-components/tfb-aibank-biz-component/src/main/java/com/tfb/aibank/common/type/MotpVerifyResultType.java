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
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)MotpTxCarrierType.java
* 
* <p>Description:推播OTP驗證結果</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/15, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpVerifyResultType {

    /**
     * 推播動態密碼驗證成功
     */
    MOTP_VERIFY_SUCCESS("0", "推播動態密碼驗證成功"),

    /**
     * 檢核交易驗證碼錯誤
     */
    MOTP_VERIFY_FAIL("1", "檢核交易驗證碼錯誤"),

    /**
     * 推播動態密碼逾時
     */
    MOTP_VERIFY_EXPIRE("2", "推播動態密碼逾時"),

    /**
     * 檢核交易驗證碼錯誤超過上限
     */
    MOTP_VERIFY_EXCEED_FAIL_TIMES("3", "檢核交易驗證碼錯誤超過上限"),

    /**
     * MOTP驗證失敗(其他)
     */
    MOTP_VERIFY_OTHER_FAIL("4", "檢核交易驗證碼錯誤(其他)"),

    /**
     * 狀態異常
     */
    UNKNOWN("99", "狀態異常"),

    ;

    /**
     * OTP驗證結果 [0:成功 / 1:驗證失敗(可以繼續驗證) / 2:失敗(不能繼續驗證)]
     */
    private String code;

    private String desc;

    MotpVerifyResultType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MotpVerifyResultType find(String code) {
        for (MotpVerifyResultType type : MotpVerifyResultType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return MotpVerifyResultType.UNKNOWN;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
