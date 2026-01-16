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
package com.tfb.aibank.chl.component.devicebinding.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)UserDeviceBindStatusType.java
 * 
 * <p>Description:使用者與裝置綁定狀態</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/19, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum UserDeviceBindStatusType implements IEnum {

    UNBIND(1, "使用者未綁定任何裝置，可進行綁定"),

    BINDED(2, "使用者已綁定目前使用裝置，可繼續執行"),

    BINDED_DEVICE_NOT_AVAILABLE(3, "使用者已綁定，但綁定裝置與目前使用裝置不同，且此裝置已被其他使用者綁定"),

    BINDED_DEVICE_NOT_MATCH(4, "使用者已綁定，但綁定裝置與目前使用裝置不同，且此裝置未綁定"),

    UNBIND_DEVICE_NOT_AVAILABLE(5, "使用者未綁定任何裝置，且此裝置已被其他使用者綁定");

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
    UserDeviceBindStatusType(Integer type, String memo) {
        this.type = type;
        this.memo = memo;
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

    public boolean isUnbind() {
        return equals(UNBIND);
    }

    public boolean isBind() {
        return equals(BINDED);
    }

    public boolean isBindedDeviceNotAvailable() {
        return equals(BINDED_DEVICE_NOT_AVAILABLE);
    }

    public boolean isBindedDeviceNotMatch() {
        return equals(BINDED_DEVICE_NOT_MATCH);
    }

    public boolean isUnBindedDeviceNotMatch() {
        return equals(UNBIND_DEVICE_NOT_AVAILABLE);
    }
}
