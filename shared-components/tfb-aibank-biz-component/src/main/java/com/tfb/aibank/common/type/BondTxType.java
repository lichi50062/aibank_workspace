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

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)BondTxType.java
 * 
 * <p>Description:債券交易類型</p>
 * <p>庫存(0001)、申購在途(1001)、贖回在途(1002)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/15, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum BondTxType implements IEnum {

    /** 庫存(0001) */
    INVENTORY("0001", "bond_tx_type.inventory"),

    /** 申購在途(1001) */
    SUBSCRIPTION_IN_TRANSIT("1001", "bond_tx_type.subscription_in_transit"),

    /** 贖回在途(1002) */
    REDEMPTION_IN_TRANSIT("1002", "bond_tx_type.redemption_in_transit"),

    /** 營業日資訊(000A) */
    BUSINESS_INFO("000A", "bond_tx_type.business_info"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知");

    private String code;

    private String memo;

    /**
     * Constructor
     *
     * @param code
     * @param memo
     */
    BondTxType(String code, String memo) {
        this.code = code;
        this.memo = memo;
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
        return I18NUtils.getMessage(this.memo);
    }

    public static BondTxType find(String code) {
        for (BondTxType type : BondTxType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }

    /** 0001 */
    public boolean isInventory() {
        return equals(INVENTORY);
    }

    /** 1001 , 1002 */
    public boolean isTransit() {
        return equals(SUBSCRIPTION_IN_TRANSIT) || equals(REDEMPTION_IN_TRANSIT);
    }

    /** 申購在途(1001) */
    public boolean isSubscriptionInTransit() {
        return equals(SUBSCRIPTION_IN_TRANSIT);
    }

    /** 贖回在途(1002) */
    public boolean isRedemptionInTransit() {
        return equals(REDEMPTION_IN_TRANSIT);
    }
}