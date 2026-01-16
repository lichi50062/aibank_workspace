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

// @formatter:off
/**
 * @(#)IDType.java
 * 
 * <p>Description:證件類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum IDType implements IEnum {

    ID_CARD_NUMBER_11("11", "身分證統一編號"),

    ID_CARD_NUMBER_12("12", "身分證統一編號"),

    OVERSEAS_NATURAL_ID_NUMBER("13", "境外自然人證號"),

    CERTIFICATE_NUMBER("19", "內政部配賦統一證號"),

    LEGAL_ENTITY_CARD_NUMBER("21", "本國法人統一編號"),

    OVERSEAS_VIRTUAL_NUMBER("22", "境外法人虛擬統編/聯徵中心編配境外法人虛擬統編"),

    SWITF_CODE("23", "SWIFT Code"),

    EXAMINATION_ID("31", "徵審ID"),

    GROUP_ID("32", "集團ID"),

    VIRTUAL_ID("33", "虛擬ID"),

    NONE_OF_ABOVE("39", "非屬上述規則");

    /** 前綴值 */
    private String code;

    /** 狀態說明 */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    IDType(String code, String memo) {
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

}