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

// @formatter:off
/**
 * @(#)SecurityOtpType.java
 * 
 * <p>Description:OTP使用類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum SecurityOtpType implements IEnum {

    ON_SITE(1, "使用臨櫃申請OTP註記進行判斷"),

    CIF(2, "使用CIF留存的行動電話有無值進行判斷"),

    AS400(3, "使用AS400留存的行動電話有無值進行判斷"),

    CIF_AS400(4, "一般會員使用CIF留存，信用卡會員使用AS400留存"),

    UNKNOWN(-1, "UNKNOWN");

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
    SecurityOtpType(Integer type, String memo) {
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

    public static SecurityOtpType findByType(int type) {
        for (SecurityOtpType enumType : SecurityOtpType.values()) {
            if (type == enumType.getType()) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    public boolean isOnSite() {
        return equals(ON_SITE);
    }

    public boolean isCif() {
        return equals(CIF);
    }

    public boolean isAs400() {
        return equals(AS400);
    }

    public boolean isCifAndAs400() {
        return equals(CIF_AS400);
    }

}
