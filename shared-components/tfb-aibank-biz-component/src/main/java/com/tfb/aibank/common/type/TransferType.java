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

// @formatter:off
/**
 * @(#)TransferType.java
 * 
 * <p>Description:轉帳類型 for WebATM</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum TransferType {

    /**
     * 繳富邦信用卡
     */
    TFB_CREDIT_CARD("1", "富邦信用卡"),

    /**
     * 繳水費
     */
    WATER("2", "水費"),

    /**
     * 繳電費
     */
    ELEC_POWER("3", "電費"),

    /**
     * 繳電費
     */
    GAS("4", "瓦斯費"),

    /**
     * 繳中華電信費
     */
    CHT("5", "中華電信費"),

    /**
     * 繳台灣大哥大電信費
     */
    TWN_MOBILE("6", "台灣大哥大電信費"),

    /**
     * 繳遠傳電信費
     */
    FETNET("7", "遠傳電信費"),

    /**
     * 繳學費
     */
    TUITIOM_FEE("8", "學費"),

    /**
     * 繳富邦人壽
     */
    FUBON_LIFE("9", "富邦人壽"),

    /**
     * 繳富邦產險
     */
    FUBON_INS("10", "富邦產險"),

    /**
     * 繳勞工保險費
     */
    LABOR_PREMIUN("11", "勞工保險費"),

    /**
     * 繳勞工退休金 勞工退休金
     */
    LABOR_PENSION("12", "勞工退休金"),

    /**
     * 繳貸款
     */
    LOAN("13", "貸款"),

    /**
     * 繳有線電視費
     */
    CABLE_TV_BILL("14", "有線電視費"),

    /**
     * 繳繳稅
     */
    TAX("15", "繳稅"),

    /**
     * 繳 國民年金 (新WEBATM 目前沒用到)
     */
    NATION_PENSION("16", "國民年金");

    /** 繳費分類* */
    private String code;

    /** 繳費分類說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    TransferType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
