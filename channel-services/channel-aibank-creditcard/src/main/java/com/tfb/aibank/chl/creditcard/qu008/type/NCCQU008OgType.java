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
package com.tfb.aibank.chl.creditcard.qu008.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)NCCQU008OgType.java
 * 
 * <p>Description:分期類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NCCQU008OgType implements IEnum {

    /** 單筆分期 */
    CONS("CONS"),
    /** 學費分期 */
    EDUF("EDUF"),
    /** 學雜費分期 */
    EEDU("EEDU"),
    /** 保費分期 */
    INSU("INSU"),
    /** 綜所稅分期 */
    TAX1("TAX1"),
    /** 帳單分期 */
    BEX1("BEX1"),
    /** 查核定稅分期 */
    OTAX("OTAX"),
    /** 長循餘額分期 */
    LORE("LORE");

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param memo
     */
    NCCQU008OgType(String memo) {
        this.memo = memo;
    }

    @Override
    public String getMemo() {
        return memo;
    }

}
