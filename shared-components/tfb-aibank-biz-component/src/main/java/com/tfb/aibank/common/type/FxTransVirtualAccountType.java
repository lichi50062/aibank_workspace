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
 * @(#)FxTransVirtualAccountType.java
 * 
 * <p>Description:外幣轉帳虛擬帳號類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/25, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum FxTransVirtualAccountType implements IEnum {

    NONE("0", "非虛擬帳號"),

    FX_FUBON_FUTURES_T("1", "富邦期貨保證金-國內"),

    FX_FUBON_FUTURES_F("2", "富邦期貨保證金-國外"),

    FX_FUBON_SEC_FUTURES("3", "富邦期貨槓桿保證金"),

    FX_JIHSUN_FUTURES_T("4", "富邦期貨保證金(原日盛)-國內"),

    FX_JIHSUN_FUTURES_F("5", "富邦期貨保證金(原日盛)-國外"),

    FX_TDCC_ACCOUNT("6", "臺灣集保境外基金"),

    FX_VIRTUAL_ACCOUNT_TRANSFER("7", "虛擬帳號"),

    FX_INSURANCE("8", "外幣富邦保費");

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
    FxTransVirtualAccountType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static FxTransVirtualAccountType find(String code) {
        for (FxTransVirtualAccountType type : FxTransVirtualAccountType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return FxTransVirtualAccountType.NONE;
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
        return !equals(NONE) || equals(FX_VIRTUAL_ACCOUNT_TRANSFER);
    }

    public boolean isFuturesAccount() {
        return equals(FX_FUBON_FUTURES_T) || equals(FX_FUBON_FUTURES_F) || equals(FX_FUBON_SEC_FUTURES) || equals(FX_JIHSUN_FUTURES_T) || equals(FX_JIHSUN_FUTURES_F);
    }

    public boolean isTdccAccount() {
        return equals(FX_TDCC_ACCOUNT);
    }

    public boolean isFxInsuranceAccount() {
        return equals(FX_INSURANCE);
    }
}
