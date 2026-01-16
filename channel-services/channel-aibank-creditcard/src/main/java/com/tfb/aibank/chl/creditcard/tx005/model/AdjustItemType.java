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
package com.tfb.aibank.chl.creditcard.tx005.model;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)NCCTX005AdjustItemType.java
 * 
 * <p>Description:調整項目類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum AdjustItemType implements IEnum, I18NEnum {

    /** 調高 */
    TURN_UP("1", "調高", "ncctx005.adjust_item.turn_up"),

    /** 調降 */
    DOWNGRADE("2", "調降", "ncctx005.adjust_item.downgrade"),

    /** 附卡額度 */
    ADDITIONAL_CARD_LIMIT("3", "附卡額度", "ncctx005.adjust_item.additional_card_limit");

    /** 代碼 **/
    private String code;

    /** 說明* */
    private String memo;

    /** i18n 鍵值 */
    private String i18nKey;

    private AdjustItemType(String code, String memo, String i18nKey) {
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

    public boolean isTurnUp() {
        return equals(TURN_UP);
    }

    public boolean isDowngrade() {
        return equals(DOWNGRADE);
    }

    public boolean isAdditionalCardLimit() {
        return equals(ADDITIONAL_CARD_LIMIT);
    }

    public static AdjustItemType findByCode(String code) {
        for (AdjustItemType type : AdjustItemType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("傳入的 code(%s) 不合理。", code));
    }

    @Override
    public String getI18NMemo() {
        return I18NUtils.getMessage(this.i18nKey);
    }
}
