/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)StockType.java
* 
* <p>Description: 證券牌卡股票類型enum</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum StockType implements IEnum {
    
    IN_COUNTRY_STOCKS("IN", "111A01", "國內股票(現股)"),
    OUT_COUNTRY_STOCKS("OUT", "211A01", "海外股票"),
    OFF_FUNDS("OUT", "221A01", "境外基金(複委託)"),
    IN_COUNTRY_FUTURES("IN", "3C1A01", "國內期貨"),
    OUT_COUNTRY_FUTURES("OUT", "3F1A01", "國外期貨"),

    UNKNOWN("UNKNOWN", "XXX", "XXX");

    private String type;

    private String assetCode;

    private String memo;

    StockType(String type, String assetCode, String memo) {
        this.type = type;
        this.assetCode = assetCode;
        this.memo = memo;
    }

    public static StockType findByAssetCode(String assetCode) {
        for (StockType stockType : values()) {
            if (StringUtils.equals(stockType.getAssetCode(), assetCode))
                return stockType;
        }
        return UNKNOWN;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public String getType() {
        return type;
    }

    public String getAssetCode() {
        return assetCode;
    }
}
