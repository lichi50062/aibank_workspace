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
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)PushCodeType.java
* 
* <p>Description:預驗證狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/12, JOhn Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum PreAuthType implements IEnum {
    /*
     * 預先驗證 0-否
     * 
     * 1-是
     * 
     * 2-驗證成功
     * 
     * 3-延續交易初始化
     * 
     * 4-
     */

    NOT_PRE_AUTH_0("0", "否"),

    PRE_AUTH_1("1", "是"),

    AUTH_SUCC_2("2", "預先驗證成功"),

    SEQ_TXN_INIT_3("3", "延續交易初始化"),

    SEQ_AUTHING_4("4", "延續交易驗證中"),

    SEQ_AUTH_FINISH_5("5", "延續驗證完成"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知"),

    ;

    private String code;

    private String memo;

    private PreAuthType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static PreAuthType findByCode(String code) {
        for (PreAuthType type : PreAuthType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}