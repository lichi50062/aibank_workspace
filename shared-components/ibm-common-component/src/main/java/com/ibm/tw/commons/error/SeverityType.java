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
package com.ibm.tw.commons.error;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)SeverityType.java
 * 
 * <p>Description:Severity Type</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum SeverityType implements IEnum {

    INFO("INFO", "狀態等級-資訊"),

    WARN("WARN", "狀態等級-警告"),

    ERROR("ERROR", "狀態等級-錯誤"),

    TIMEOUT("TIMEOUT", "狀態等級-逾時"),

    FATAL("FATAL", "狀態等級-異常"),

    UNKNOWN("UNKNOWN", "未知");

    /** 代碼 */
    private String value;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param value
     * @param memo
     */
    SeverityType(String value, String memo) {
        this.value = value;
        this.memo = memo;
    }

    /**
     * 依據代碼取得SourceType
     *
     * @return
     */
    public static SeverityType find(String value) {
        for (SeverityType type : SeverityType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return SeverityType.UNKNOWN;
    }

    /**
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * @return
     */
    @Override
    public String getMemo() {
        return memo;
    }
}
