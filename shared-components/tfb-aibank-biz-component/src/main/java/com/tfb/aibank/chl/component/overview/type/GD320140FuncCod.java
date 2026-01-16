package com.tfb.aibank.chl.component.overview.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)FuncCod.java
 * 
 * <p>Description:電文 GD320140.FuncCod 功能別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum GD320140FuncCod implements IEnum {

    COD_01("01", "黃金存摺內容明細查詢"),

    COD_03("03", "查詢曾經申請黃金存摺的帳號"),

    COD_04("04", "折合台幣總金額"),

    COD_05("05", "黃金存摺內容明細查詢(上行沒有戶名代碼，提供ID全部可交易帳號資料，含0庫存)"),

    COD_06("06", "黃金存摺商品內容查詢"),

    UNKNOWN(UNKNOWN_STR_CODE, "UNKNOWN");

    private String code;
    private String memo;

    GD320140FuncCod(String code, String memo) {
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
