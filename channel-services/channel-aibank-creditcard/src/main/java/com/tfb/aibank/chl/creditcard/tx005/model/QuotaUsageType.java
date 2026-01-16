package com.tfb.aibank.chl.creditcard.tx005.model;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)NCCTX005QuotaUsageType.java
 * 
 * <p>Description:額度用途</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum QuotaUsageType implements IEnum, I18NEnum {

    /** 人壽保費扣繳(分期) */
    LIFE_INSURANCE_FEE_INSTALLMENT("7", "人壽保費扣繳(分期)", "ncctx005.quota_usage.life_insurance.installment"),

    /** 人壽保費扣繳 */
    LIFE_INSURANCE_FEE("2", "人壽保費扣繳", "ncctx005.quota_usage.life_insurance"),

    /** 一般消費需求 */
    GENERAL_CONSUMER_DEMAND("3", "一般消費需求", "ncctx005.quota_usage.general_consumer_demand"),

    /** 出國需求 */
    ABROAD_DEMAND("4", "出國需求", "ncctx005.quota_usage.abroad_demand"),

    /** 其他 */
    OTHER("6", "其他", "ncctx005.quota_usage.other");

    /** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    private QuotaUsageType(String code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
        this.i18nKey = i18nKey;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public String getCode() {
        return code;
    }

    public static QuotaUsageType findByCode(String code) {
        for (QuotaUsageType type : QuotaUsageType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("傳入的 code(%s) 不合理。", code));
    }

    public boolean isLifeInsuranceFeeInstallment() {
        return equals(LIFE_INSURANCE_FEE_INSTALLMENT);
    }

    public boolean isOther() {
        return equals(OTHER);
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }
}
