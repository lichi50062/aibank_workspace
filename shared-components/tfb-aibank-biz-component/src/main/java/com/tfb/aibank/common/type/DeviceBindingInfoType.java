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

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)DeviceBindingInfoType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/16, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum DeviceBindingInfoType implements IEnum {

    QLOGIN(1, "QLOGIN", "已綁定快速登入"),

    NOTIFY(2, "NOTIFY", "已綁定訊息通知"),

    QQUERY(3, "QQUERY", "已綁定免登速查"),

    NOCARD(4, "NOCARD", "已綁定無卡提款"),

    PUMOTP(5, "PUMOTP", "已綁定推播動態密碼MOTP"),

    PHORCV(6, "PHORCV", "已綁定手機號碼收款"),

    ENTXLI(7, "ENTXLI", "已綁定提高飛約轉額度"),

    UNKNOWN(0, "UNKNOWN", "未知");

    /** 代碼 */
    private int code;

    /** 綁定資訊 */
    private String type;

    /** 說明 */
    private String memo;

    DeviceBindingInfoType(int code, String type, String memo) {
        this.code = code;
        this.type = type;
        this.memo = memo;
    }

    public static DeviceBindingInfoType find(String type) {
        for (DeviceBindingInfoType thistype : DeviceBindingInfoType.values()) {
            if (StringUtils.equals(thistype.getType(), type)) {
                return thistype;
            }
        }
        return null;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return memo;
    }

}
