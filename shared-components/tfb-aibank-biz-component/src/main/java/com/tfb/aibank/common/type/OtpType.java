/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2010.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)OtpType.java
 * 
 * <p>Description:OTP安控類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum OtpType implements IEnum {

    NONE(0, "無"), TOKEN(1, "OTP載具"), SMS(2, "手機簡訊"), UNKNOWN(-1, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    OtpType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static OtpType find(int code) {

        for (OtpType group : OtpType.values()) {

            if (group.getCode() == code) {
                return group;
            }
        }

        return OtpType.UNKNOWN;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 是否無OTP安控
     * 
     * @return
     */
    public boolean isNone() {

        return equals(OtpType.NONE);
    }

    /**
     * 是否為OTP載具
     * 
     * @return
     */
    public boolean isToken() {
        return equals(OtpType.TOKEN);
    }

    /**
     * 是否為手機簡訊
     * 
     * @return
     */
    public boolean isSMS() {
        return equals(OtpType.SMS);
    }
}
