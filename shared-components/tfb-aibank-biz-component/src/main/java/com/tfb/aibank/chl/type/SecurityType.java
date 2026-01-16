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
 * @(#)SecurityType.java
 * 
 * <p>Description:交易放行安控機制</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum SecurityType implements IEnum {

    NONE(0, "不用安控"),

    OTP(1, "簡訊OTP"),

    MOTP(2, "MOTP"),

    OTP_MOTP(3, "簡訊OTP+MOTP"),

    // 以下進交易先不驗，由交易決定驗證時機

    OTP_PASS(11, "簡訊OTP"),

    MOTP_PASS(12, "MOTP"),

    OTP_MOTP_PASS(13, "簡訊OTP+MOTP"),

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
    SecurityType(Integer type, String memo) {
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

    public static SecurityType findByType(int type) {
        for (SecurityType enumType : SecurityType.values()) {
            if (type == enumType.getType()) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    public boolean isNone() {
        return equals(NONE);
    }

    public boolean isOtp() {
        return equals(OTP);
    }

    public boolean isMotp() {
        return equals(MOTP);
    }

    public boolean isOtpAndMotp() {
        return equals(OTP_MOTP);
    }

    public boolean isOtpPass() {
        return equals(OTP_PASS);
    }

    public boolean isMotpPass() {
        return equals(MOTP_PASS);
    }

    public boolean isOtpAndMotpPass() {
        return equals(OTP_MOTP_PASS);
    }

    /**
     * 是否忽略初始檢查
     * 
     * @return
     */
    public boolean isPassInitCheck() {
        return isOtpPass() || isMotpPass() || isOtpAndMotpPass();
    }

}
