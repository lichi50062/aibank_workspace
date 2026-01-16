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
 * @(#)AccountClass.java
 * 
 * <p>Description:帳號分類</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum AccountClass implements IEnum {

    /** 390存款帳號 */
    CLASS_390_SV("01", "390存款帳號"),

    /** 390存款帳號168 */
    CLASS_390_SV_168("02", "390存款帳號168"),

    /** 390存款帳號OBU */
    CLASS_390_SV_OBU("03", "390存款帳號OBU"),

    /** 390放款帳號 */
    CLASS_390_LN("11", "390放款帳號"),

    /** CBS存款帳號 */
    CLASS_CBS_SV("21", "CBS存款帳號"),

    /** CBS存款帳號168 */
    CLASS_CBS_SV_168("22", "CBS存款帳號168"),

    /** CBS存款帳號OBU */
    CLASS_CBS_SV_OBU("23", "CBS存款帳號OBU"),

    /** CBS放款帳號 */
    CLASS_CBS_LN("31", "CBS放款帳號"),

    /** CBS放款帳號OBU */
    CLASS_CBS_LN_OBU("32", "CBS放款帳號OBU"),

    /** 未定義 */
    CLASS_UNKNOWN(UNKNOWN_STR_CODE, "未知帳號");

    /** 代碼 */
    private String code;

    /** 說明 */
    private String memo;

    AccountClass(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static AccountClass find(String code) {
        for (AccountClass value : AccountClass.values()) {
            if (StringUtils.equals(value.getCode(), code)) {
                return value;
            }
        }
        return AccountClass.CLASS_UNKNOWN;
    }

    /**
     * 判斷是否為390存款帳號
     * 
     * @return
     */
    public boolean is390SV() {
        return equals(CLASS_390_SV) || equals(CLASS_390_SV_168) || equals(CLASS_390_SV_OBU);
    }

    /**
     * 判斷是否為390放款帳號
     * 
     * @return
     */
    public boolean is390LN() {
        return equals(CLASS_390_LN);
    }

    /**
     * 判斷是否為CBS存款帳號
     * 
     * @return
     */
    public boolean isCBSSV() {
        return equals(CLASS_CBS_SV) || equals(CLASS_CBS_SV_168) || equals(CLASS_CBS_SV_OBU);
    }

    /**
     * 判斷是否為CBS放款帳號
     * 
     * @return
     */
    public boolean isCBSLN() {
        return equals(CLASS_CBS_LN) || equals(CLASS_CBS_LN_OBU);
    }

    /**
     * 判斷是否為CBS放款帳號OBU
     * 
     * @return
     */
    public boolean isCBSLNOBU() {
        return equals(CLASS_CBS_LN_OBU);
    }

    /**
     * 判斷是否為168存款帳號
     * 
     * @return
     */
    public boolean is168() {
        return equals(CLASS_390_SV_168) || equals(CLASS_CBS_SV_168);
    }

    /**
     * 判斷是否為OBU存款帳號
     * 
     * @return
     */
    public boolean isOBU() {
        return equals(CLASS_390_SV_OBU) || equals(CLASS_CBS_SV_OBU) || equals(CLASS_CBS_LN_OBU);
    }

    /**
     * 判斷是否為無效帳號
     * 
     * @return
     */
    public boolean isUnknown() {
        return equals(CLASS_UNKNOWN);
    }

    /**
     * 判斷是否為390(舊核心)帳號
     * 
     * @return
     */
    public boolean is390() {
        return is390SV() || is390LN();
    }

    /**
     * 判斷是否為CBS(新核心)帳號
     * 
     * @return
     */
    public boolean isCBS() {
        return isCBSSV() || isCBSLN();
    }

}
