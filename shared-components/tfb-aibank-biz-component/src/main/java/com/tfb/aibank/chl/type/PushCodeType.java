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

// @formatter:off
/**
 * @(#)PushCodeType.java
 * 
 * <p>Description:個人化通知訊息 - 推播代碼類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/04, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum PushCodeType implements IEnum {

    PERSONAL("1", "個人化訊息"),

    MARKETING("2", "行銷訊息"),

    SYSTEM("3", "系統公告"),

    SMART("4", "智能訊息"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知"),

    ;

    private String code;

    private String memo;

    private PushCodeType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static PushCodeType findByCode(String code) {
        for (PushCodeType type : PushCodeType.values()) {
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
