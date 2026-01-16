package com.tfb.aibank.chl.component.overview.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)Source.java
 * 
 * <p>Description:電文 GD320140.SOURCE 來源別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum GD320140Source implements IEnum {

    TYPE_A("A", "臨櫃"),

    TYPE_B("B", "網銀"),

    TYPE_C("C", "客服"),

    TYPE_D("D", "行銀"),

    TYPE_E("E", "系統自動交易"),

    TYPE_F("F", "理專"),

    UNKNOWN(UNKNOWN_STR_CODE, "UNKNOWN");

    private String code;
    private String memo;

    GD320140Source(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

}
