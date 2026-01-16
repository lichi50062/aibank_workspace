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
package com.ibm.tw.commons.id;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)IBSystemId.java
 * 
 * <p>Description:系統代碼表</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum IBSystemId implements IEnum {

    /** 一般訊息 */
    SVC("Channel Service 一般訊息"),

    /** FIDO SDK */
    FIDO("FIDO SDK"),

    /** 前端 APP */
    APP("前端 APP"),

    /** 前端 ADT */
    ADT("前端 ADT"),

    /** HSM */
    HSM("HSM"),

    /** AIBANK */
    AIBANK("AIBANK"),

    /** PIB */
    PIB("PIB"),

    /** IMP */
    IMP("native IMP"),

    CPC("網路ATM&E化繳費網"),

    /** 電文主機 */
    AI("電文主機"),

    /** 行銀 */
    MB("行銀"),

    /** LINEBC綁定推薦系統 */
    LINEBC("LINEBC"),

    /** 繳費平台 */
    PAYMENT("PAYMENT"),

    /** 業管系統 */
    BMS("BMS"),

    /** 票交所 */
    PAYMENT_EFCS("PAYMENT_EFCS"),

    /** 全繳網 */
    PAYMENT_EBILL("PAYMENT_EBILL"),

    /** Pay.Taipei */
    PAYMENT_PAY("PAYMENT_PAY"),

    /** 財金(載具相關驗證使用) */
    PAYMENT_FISC("PAYMENT_FISC"),

    /** 財金(富邦錢包用) */
    WALLET_FISC("WALLET_FISC"),

    /** 電支反綁 */
    EPAY("EPAY"),

    /** 奈米投網站 */
    NMPLUS("nmplusinvestment"),

    /** 台網 */
    TWID("TWID"),

    /** 刷卡身分驗證綁定 */
    FIDO2("FIDO2"),

    /** 未明 */
    UNKNOWN("未明");

    private String memo;

    IBSystemId(String memo) {
        this.memo = memo;
    }

    public String getSystemId() {
        return name();
    }

    /**
     * 依據狀態碼取得狀態
     *
     * @param systemId
     * @return
     */
    public static IBSystemId bySystemId(String systemId) {
        for (IBSystemId type : IBSystemId.values()) {
            if (StringUtils.equalsIgnoreCase(type.getSystemId(), systemId)) {
                return type;
            }
        }
        return IBSystemId.UNKNOWN;
    }

    /**
     * @return the memo
     */
    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * 忽略不將訊息替換成系統預設
     * 
     * @return
     */
    public boolean ignoreMsgReplacement() {
        return equals(AI) || equals(PAYMENT) || equals(BMS) || equals(PAYMENT_EBILL) || equals(PAYMENT_EFCS) || equals(PAYMENT_FISC) || equals(PAYMENT_PAY) || equals(WALLET_FISC) || equals(EPAY) || equals(FIDO2);
    }
}
