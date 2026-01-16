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
package com.tfb.aibank.chl.component.security.otp.type;

// @formatter:off
/**
 * @(#)OtpTxSeedTransferType.java
 * 
 * <p>Description:OTP共用交易因子 - 轉帳類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/28, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum OtpTxSeedTransferType {

    /** 臺幣轉帳 */
    TW_TRANSFER,

    /** 外幣轉帳 */
    FX_TRANSFER,

    /** 繳卡費(他人) */
    CARD_PAYMENT_OTHERS,

    ;

    public boolean isTwTransfer() {
        return equals(TW_TRANSFER);
    }

    public boolean isFxTransfer() {
        return equals(FX_TRANSFER);
    }

    public boolean isCardPaymentOthers() {
        return equals(CARD_PAYMENT_OTHERS);
    }

}
