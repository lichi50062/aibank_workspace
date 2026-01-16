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
package com.tfb.aibank.chl.creditcard.ag007.type;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)NCCAG007BindType.java
 * 
 * <p>Description:NCCAG007 綁定類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NCCAG007BindType implements IEnum {

    LINE_PAY("LinePay", "LINE PAY 綁定"),

    APPLE_PAY("ApplePay", "APPLE PAY 綁定"),

    UNKNOWN(UNKNOWN_STR_CODE, "未定義");

    private String code;

    private String memo;

    NCCAG007BindType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static NCCAG007BindType findByCode(String code) {
        for (NCCAG007BindType type : NCCAG007BindType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public boolean isLinePay() {
        return equals(LINE_PAY);
    }

    public boolean isApplePay() {
        return equals(APPLE_PAY);
    }

    public boolean isUnknown() {
        return equals(UNKNOWN);
    }
}
