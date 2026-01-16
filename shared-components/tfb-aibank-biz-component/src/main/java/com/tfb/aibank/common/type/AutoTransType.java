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
 * <p>Description:到期續存方式</p>
 * <p>1:不續存；2:本金續存；3:本利續存；5:不續存，到期轉入活存</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/15, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum AutoTransType implements IEnum, I18NEnum {

    /** 不續存 */
    NO_RENEWAL("1", "不續存", "fixed_deposit.auto_trans_type1"),

    /** 本金續存 */
    PRINCIPAL_RENEWAL("2", "本金續存", "fixed_deposit.auto_trans_type2"),

    /** 本利續存 */
    RENEWAL_OF_PRINCIPAL_AND_INTEREST("3", "本利續存", "fixed_deposit.auto_trans_type3"),

    /** 不續存，到期轉入活存 */
    NO_RENEWAL_TRANSFER_TO_LIVING_DEPOSIT_UPON_EXPIRATION("5", "不續存，到期轉入活存", "fixed_deposit.auto_trans_type5");

    /** 狀態碼* */
    private String code;

    /** 狀態說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    AutoTransType(String code, String memo, String i18nKey) {
        this.code = code;
        this.memo = memo;
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
        return I18NUtils.getMessage(this.memo);
    }

    public static AutoTransType find(String code) {
        for (AutoTransType type : AutoTransType.values()) {
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