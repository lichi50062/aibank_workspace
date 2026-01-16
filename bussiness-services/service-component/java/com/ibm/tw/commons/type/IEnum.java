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
package com.ibm.tw.commons.type;

/**
 * <p>
 * Enum共同介面 (for i18n)
 * </p>
 *
 * @author Leo
 * @version 1.0, 2012/11/26
 * @see
 * @since
 */
public interface IEnum {

    public static String ENUM_I18N_CATE = "EnumType";

    /** 數字型態的未知代碼 */
    public int UNKNOWN_INT_CODE = -9;

    /** 字串型態的未知代碼 */
    public String UNKNOWN_STR_CODE = "";

    /**
     * 僅作為備忘錄、便箋性質使用，勿套用具備 i18n 特性的訊息
     * 
     * @return
     */
    public String getMemo();
}
