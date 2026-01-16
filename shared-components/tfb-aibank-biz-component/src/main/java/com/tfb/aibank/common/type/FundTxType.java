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
 * @(#)FundTxType.java
 * 
 * <p>Description:基金交易類型</p>
 * <p>單筆(0001)、定期定額(0002)、定期不定額(0003)、定存轉基金(0004)、基金套餐(0005) 申購在途(1001)、轉換在途(1002)、贖回在途(1003)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum FundTxType implements IEnum {

    /** 單筆 */
    SINGLE_PURCHASE("0001", "fund_tx_type.single_purchase"),

    /** 定期定額 */
    REGULAR_FIXED_AMOUNT("0002", "fund_tx_type.regular_fixed_amount"),

    /** 定期不定額 */
    REGULAR_VARIABLE_AMOUNT("0003", "fund_tx_type.regular_variable_amount"),

    /** 定存轉基金 */
    TIME_DEPOSIT_TO_FUND("0004", "fund_tx_type.time_deposit_to_fund"),

    /** 基金套餐 */
    FUND_PACKAGE("0005", "fund_tx_type.fund_package"),

    /** 申購在途 */
    PURCHASE_IN_TRANSIT("1001", "fund_tx_type.purchase_in_transit"),

    /** 轉換在途 */
    CONVERSION_IN_TRANSIT("1002", "fund_tx_type.conversion_in_transit"),

    /** 贖回在途 */
    REDEMPTION_IN_TRANSIT("1003", "fund_tx_type.redemption_in_transit"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知");

    private String code;

    private String memo;

    /**
     * Constructor
     *
     * @param code
     * @param memo
     */
    FundTxType(String code, String memo) {
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

    public static FundTxType find(String code) {
        for (FundTxType type : FundTxType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 是否為「庫存」: 0001、0002、0003、0004、0005
     * 
     * @return
     */
    public boolean isInventory() {
        return equals(SINGLE_PURCHASE) || equals(REGULAR_FIXED_AMOUNT) || equals(REGULAR_VARIABLE_AMOUNT) || equals(TIME_DEPOSIT_TO_FUND) || equals(FUND_PACKAGE);
    }

    /**
     * 是否為「在途」: 1001、1002、1003
     * 
     * @return
     */
    public boolean isTransit() {
        return equals(PURCHASE_IN_TRANSIT) || equals(CONVERSION_IN_TRANSIT) || equals(REDEMPTION_IN_TRANSIT);
    }

    public boolean isSinglePurchase() {
        return equals(SINGLE_PURCHASE);
    }

    public boolean isRegularFixedAmount() {
        return equals(REGULAR_FIXED_AMOUNT);
    }

    public boolean isRegularVariableAmount() {
        return equals(REGULAR_VARIABLE_AMOUNT);
    }

    public boolean isTimeDepositToFund() {
        return equals(TIME_DEPOSIT_TO_FUND);
    }

    public boolean isFundPackage() {
        return equals(FUND_PACKAGE);
    }

    /** PURCHASE_IN_TRANSIT 1001 */
    public boolean isPurchaseInTransit() {
        return equals(PURCHASE_IN_TRANSIT);
    }

    /** CONVERSION_IN_TRANSIT 1002 */
    public boolean isConversionInTransit() {
        return equals(CONVERSION_IN_TRANSIT);

    }

    /** REDEMPTION_IN_TRANSIT 1003 */
    public boolean isRedemptionInTransit() {
        return equals(REDEMPTION_IN_TRANSIT);
    }
}
