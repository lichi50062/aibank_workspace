package com.tfb.aibank.chl.creditcard.qu010.model;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)NCCQU010CategoryType.java
 *
 * <p>Description:消費類別</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024-07-08, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NCCQU010CategoryType implements IEnum {
            
    LIFE("life", "消費類別"),
    
    CARD("card", "卡片消費"),
    
    REGION("region", "國內外類別");

    /** 類型 */
    private String type;

    /** 說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param type
     * @param memo
     */
    NCCQU010CategoryType(String type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * @return 是否為消費類別
     */
    public boolean isLife() {
        return equals(LIFE);
    }

    /**
     * @return 是否為卡片消費
     */
    public boolean isCard() {
        return equals(CARD);
    }

    /**
     * @return 是否為國內外類別
     */
    public boolean isRegion() {
        return equals(REGION);
    }
}
