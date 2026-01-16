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
package com.tfb.aibank.chl.general.ag001.model;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)NGNAG001StatusType.java
 * 
 * <p>Description:檢查狀態類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/13, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NGNAG001StatusType implements IEnum {

    UNBIND(1, "未綁定"),

    BINDED(2, "已綁定"),

    UNBIND_DEVICE_NOT_AVAILABLE(3, "未綁定(目前裝置已被其他使用者綁定)"),

    BINDED_DEVICE_NOT_MATCH(4, "已綁定(使用者綁定裝置與目前裝置不同)"),

    UNKNOWN(UNKNOWN_INT_CODE, "UNKNOWN");

    /** 類型 */
    private Integer type;

    /** 說明* */
    private String memo;

    /**
     * Constructor
     * 
     * @param type
     * @param memo
     */
    NGNAG001StatusType(Integer type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    public static NGNAG001StatusType findByType(int type) {
        for (NGNAG001StatusType enumType : NGNAG001StatusType.values()) {
            if (type == enumType.getType()) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    /**
     * @return
     */
    public Integer getType() {
        return this.type;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public boolean isUnknown() {
        return equals(UNKNOWN);
    }

    public boolean isUnbind() {
        return equals(UNBIND);
    }

    public boolean isBind() {
        return equals(BINDED);
    }

    public boolean isUnbindAndDeviceNotAvailable() {
        return equals(UNBIND_DEVICE_NOT_AVAILABLE);
    }

    public boolean isBindAndDeviceNotMatch() {
        return equals(BINDED_DEVICE_NOT_MATCH);
    }

}
