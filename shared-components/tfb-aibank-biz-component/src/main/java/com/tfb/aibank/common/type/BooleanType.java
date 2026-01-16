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
* @(#)BooleanType.java
* 
* <p>Description:通用 BooleanType</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/15, Benson
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum BooleanType implements IEnum {

    YES("Y"),

    /** NO */
    NO("N"),

    /** TRUE */
    TRUE("1"),

    /** FALSE */
    FALSE("0"),

    /** true */
    TRUE_STR("true"),

    /** false */
    FALSE_STR("false"),

    UNKNOWN(UNKNOWN_STR_CODE);

    /** 識別碼 */
    private String code;

    BooleanType(String code) {
        this.code = code;
    }

    /**
     * @param code
     * @return
     */
    public static BooleanType find(String code) {
        for (BooleanType value : BooleanType.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }

        return BooleanType.UNKNOWN;
    }

    /**
     * @param code
     * @return
     */
    public static BooleanType find(Integer code) {

        if (code == null) {
            return BooleanType.UNKNOWN;
        }

        for (BooleanType value : BooleanType.values()) {
            if (StringUtils.equals(value.getCode(), code.toString())) {
                return value;
            }
        }

        return BooleanType.UNKNOWN;
    }

    /**
     * @param code
     * @return
     */
    public static BooleanType find(boolean code) {
        return code ? TRUE : FALSE;
    }

    /**
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * @return
     */
    public int getIntCode() {
        return toBoolean() ? 1 : 0;
    }

    /**
     * @return
     */
    public String getYNCode() {

        if (isTrue()) {
            return YES.getCode();
        }
        else if (isFalse()) {
            return NO.getCode();
        }

        return UNKNOWN.getCode();
    }

    /**
     * @return
     */
    public boolean isYesOrTrue() {
        return isYes() || isTrue();
    }

    /**
     * @return
     */
    public boolean isYes() {
        return equals(YES);
    }

    /**
     * @return
     */
    public boolean isNo() {
        return equals(NO);
    }

    /**
     * @return
     */
    public boolean isTrue() {
        return equals(YES) || equals(TRUE) || equals(TRUE_STR);
    }

    /**
     * @return
     */
    public boolean isFalse() {
        return equals(NO) || equals(FALSE) || equals(FALSE_STR);
    }

    /**
     * 是否為未知
     * 
     * @return
     */
    public boolean isUnknown() {
        return equals(UNKNOWN);
    }

    /**
     * @return
     */
    public boolean toBoolean() {
        return isTrue();
    }

    @Override
    public String getMemo() {
        return code;
    }

}
