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
* @(#)MotpTransDataModel.java
* 
* <p>Description:MOTP交易驗證狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpTxStatus {

    INIT(0, "交易建立MOTP初始狀態"),

    VERIFY_SUCCESS(1, "MOTP驗證成功"),

    VERIFY_FAIL_EXCEED_LIMITTIMES(4, "MOTP驗證失敗次數過多（交易被迫取消）"),

    EXPIRE(5, "授權已超過有效截止時間"),

    CANCEL(6, "交易取消"),

    SYSTEM_ERROR_MOTP(8, "MOTP伺服器發生錯誤"),

    SYSTEM_ERROR(9, "系統發生錯誤");

    private int code;

    private String desc;

    MotpTxStatus(int code, String desc) {
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
