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
 * @(#)TwTransVirtualAccountType.java
 * 
 * <p>Description:臺幣轉帳虛擬帳號類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum TwTransVirtualAccountType implements IEnum {

    NONE("0", "非虛擬帳號"),

    FUTURES_IN("1", "期貨保證金-國內"),

    FUTURES_OUT("2", "期貨保證金-國外"),

    FUTURES_LEVERAGE("3", "期貨保證金-期貨槓桿保證金"),

    FUTURES_IN_JI("4", "期貨保證金-國內(原日盛)"),

    FUTURES_OUT_JI("5", "期貨保證金-國外(原日盛)"),

    TDCC_ACCOUNT("6", "台灣集保"),

    VIRTUAL_ACCOUNT_TRANSFER("7", "轉帳(虛擬帳號)"),

    CONSTRUCTION_PRESALE_ACCOUNT("8", "富盛營建預售案虛擬帳號"),

    FUBON_CREDIT_CARD_ACCOUNT("9", "富邦繳信用卡款"),

    JIHSUN_CREDIT_CARD_ACCOUNT("10", "日盛信用卡虛擬帳號"),

    FUBON_INSURANCE_ACCOUNT("11", "台幣富邦保費"),
    ;

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    TwTransVirtualAccountType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static TwTransVirtualAccountType find(String code) {
        for (TwTransVirtualAccountType type : TwTransVirtualAccountType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return TwTransVirtualAccountType.NONE;
    }

    public String getCode() {
        return code;
    }

    /**
     * 取得 memo
     *
     * @return 傳回 memo。
     */
    @Override
    public String getMemo() {
        return memo;
    }

    public boolean isVirtualAccount() {
        return !equals(NONE);
    }

    public boolean isFuturesAccount() {
        return equals(FUTURES_IN) || equals(FUTURES_OUT) || equals(FUTURES_LEVERAGE) || equals(FUTURES_IN_JI) || equals(FUTURES_OUT_JI);
    }

    public boolean isTdccAccount() {
        return equals(TDCC_ACCOUNT);
    }

    public boolean isConstructionPresaleAccount() {
        return equals(CONSTRUCTION_PRESALE_ACCOUNT);
    }

    public boolean isFubonCreditCardAccount() {
        return equals(FUBON_CREDIT_CARD_ACCOUNT);
    }

    public boolean isJihsunCreditCardAccount() {
        return equals(JIHSUN_CREDIT_CARD_ACCOUNT);
    }

    public boolean isVirtualAccountTransfer() {
        return equals(VIRTUAL_ACCOUNT_TRANSFER);
    }

    public boolean isFubonInsuranceAccount() {
        return equals(FUBON_INSURANCE_ACCOUNT);
    }

}
