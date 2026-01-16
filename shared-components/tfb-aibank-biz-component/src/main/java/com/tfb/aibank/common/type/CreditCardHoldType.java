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

//@formatter:off
/**
* @(#)HoldCardType.java
* 
* <p>Description:持卡類型</p>
* <p>持卡類別 0: 正卡 1:正卡項下附卡 2:附卡</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/25, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum CreditCardHoldType implements IEnum {

    PRIMARY_CARD("0", "正卡"),

    SECONDARY_UNDER_PRIMARY_CARD("1", "正卡項下附卡"),

    SUPPLEMENTARY_CARD("2", "附卡");

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
    CreditCardHoldType(String code, String memo) {
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
        return memo;
    }

    public static CreditCardHoldType find(String code) {
        for (CreditCardHoldType group : CreditCardHoldType.values()) {
            if (StringUtils.equals(group.getCode(), code)) {
                return group;
            }
        }
        return null;
    }

    public boolean isPrimaryCard() {
        return equals(PRIMARY_CARD);
    }

    public boolean isSecondaryUnderPrimaryCard() {
        return equals(SECONDARY_UNDER_PRIMARY_CARD);
    }

    public boolean isSupplementaryCard() {
        return equals(SUPPLEMENTARY_CARD);
    }

}