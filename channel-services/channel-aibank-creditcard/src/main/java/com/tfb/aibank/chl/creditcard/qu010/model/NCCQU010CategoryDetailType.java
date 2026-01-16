package com.tfb.aibank.chl.creditcard.qu010.model;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)NCCQU010CategoryDetailType.java
 *
 * <p>Description:消費明細類別</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024-07-08, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NCCQU010CategoryDetailType implements IEnum {
            
    DOMESTIC("domestic", "國內"),

    OVERSEAS("overseas", "國外");

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
    NCCQU010CategoryDetailType(String type, String memo) {
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
     * @return 是否為國內
     */
    public boolean isDomestic() {
        return equals(DOMESTIC);
    }

    /**
     * @return 是否為國外
     */
    public boolean isOverseas() {
        return equals(OVERSEAS);
    }
}
