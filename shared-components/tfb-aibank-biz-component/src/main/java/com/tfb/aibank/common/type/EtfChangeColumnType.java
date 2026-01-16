/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)EtfChangeColumnType.java
 * 
 * <p>Description:[海外股票ETF約定條件變更]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/25, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum EtfChangeColumnType {

    TYPE1("EntrustAmt", "申購金額"),

    TYPE2("PayDate01", "約定扣款日1"),

    TYPE3("PayDate02", "約定扣款日2"),

    TYPE4("PayDate03", "約定扣款日3"),

    TYPE5("PayDate04", "約定扣款日4"),

    TYPE6("PayDate05", "約定扣款日5"),

    TYPE7("PayDate06", "約定扣款日6"),

    TYPE8("PayAcct", "扣款帳號"),

    TYPE9("Status", "狀態"),

    UNKNOWN("unknown", "unknown");

    private String code;

    private String desc;

    EtfChangeColumnType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EtfChangeColumnType findByCode(String code) {
        for (EtfChangeColumnType type : values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    public String getMemo() {
        return name();
    }
}
