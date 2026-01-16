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
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)IntPayType.java
 * 
 * <p>Description:利息領取方式</p>
 * <p>1:中心每年轉期；2:櫃員每月付息；3:到期單利；4:到期複利；5:中心每年轉息；6:櫃員每年付息；7:每年付息滾入；8:半年複利到期付息；9:本利續存</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/15, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum IntPayType implements IEnum, I18NEnum {

    /** 中心每月轉期 - 每月領息 */
    CENTER_MONTHLY_ROLLOVER("1", "中心每月轉期", "fixed_deposit.interest_pay_desc1", "fixed_deposit.interest_pay_type1"),

    /** 櫃員每月付息 - 每月領息 */
    TELLER_PAYS_INTEREST_MONTHLY("2", "櫃員每月付息", "fixed_deposit.interest_pay_desc1", "fixed_deposit.interest_pay_type2"),

    /** 到期單利 - 到期領息 */
    SIMPLE_INTEREST_AT_MATURITY("3", "到期單利", "fixed_deposit.interest_pay_desc2", "fixed_deposit.interest_pay_type3"),

    /** 到期複利 - 到期領息 */
    COMPOUND_INTEREST_AT_MATURITY("4", "到期複利", "fixed_deposit.interest_pay_desc2", "fixed_deposit.interest_pay_type4"),

    /** 中心每年轉息 - 每年領息 */
    TRANSFERS_INTEREST_EVERY_YEAR("5", "中心每年轉息", "fixed_deposit.interest_pay_desc3", "fixed_deposit.interest_pay_type5"),

    /** 櫃員每年付息 - 每年領息 */
    TELLER_PAYS_INTEREST_EVERY_YEAR("6", "櫃員每年付息", "fixed_deposit.interest_pay_desc3", "fixed_deposit.interest_pay_type6"),

    /** 每年付息滾入 - 每年領息 */
    INTEREST_PAYMENT_ROLLS_EVERY_YEAR("7", "每年付息滾入", "fixed_deposit.interest_pay_desc3", "fixed_deposit.interest_pay_type7"),

    /** 半年複利到期付息 - 到期領息 */
    HALF_YEARLY_COMPOUND_INTEREST_PAID_UPON_MATURITY("8", "半年複利到期付息", "fixed_deposit.interest_pay_desc2", "fixed_deposit.interest_pay_type8");

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /** 狀態描述* */
    private String desc;

    /** i18n 鍵值 */
    private String i18nKey;

    IntPayType(String code, String memo, String desc, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.desc = desc;
        this.i18nKey = i18nKey;
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
        return this.memo;
    }

    public String getDesc() {
        return I18NUtils.getMessage(this.desc);
    }

    public static IntPayType find(String code) {
        for (IntPayType type : IntPayType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }

}