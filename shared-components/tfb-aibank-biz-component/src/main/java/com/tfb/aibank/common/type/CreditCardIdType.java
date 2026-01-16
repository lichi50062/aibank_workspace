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
* @(#)CreditCardIdentityType.java
* 
* <p>Description:信用卡身分別</p>
* <p>持卡類別 1:正卡人 2:附卡人</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/25, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum CreditCardIdType implements IEnum {

    PRIMARY_CARD("1", "正卡人"),

    SUPPLEMENTARY_CARD("2", "附卡人");

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
    CreditCardIdType(String code, String memo) {
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

    public static CreditCardIdType find(String code) {
        for (CreditCardIdType group : CreditCardIdType.values()) {
            if (StringUtils.equals(group.getCode(), code)) {
                return group;
            }
        }
        return null;
    }

    public boolean isPrimaryCard() {
        return equals(PRIMARY_CARD);
    }

    public boolean isSupplementaryCard() {
        return equals(SUPPLEMENTARY_CARD);
    }
}