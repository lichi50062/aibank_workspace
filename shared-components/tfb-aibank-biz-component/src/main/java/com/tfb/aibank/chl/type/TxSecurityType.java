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
 * @(#)TxSecurityType.java
 * 
 * <p>Description:交易安控機制類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum TxSecurityType implements IEnum {

    NONE(0, "不用安控"),

    OTP(1, "簡訊OTP"),

    MOTP(2, "MOTP"),

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
    TxSecurityType(Integer type, String memo) {
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

    public boolean isNone() {
        return equals(NONE);
    }

    public boolean isOtp() {
        return equals(OTP);
    }

    public boolean isMotp() {
        return equals(MOTP);
    }

    public boolean isUnknown() {
        return equals(UNKNOWN);
    }

}
