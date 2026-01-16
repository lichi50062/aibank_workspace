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
package com.tfb.aibank.biz.type;

import java.util.Arrays;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)ProductCategory.java
* 
* <p>Description: 投資商品種類</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/18, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum ProductCategory implements IEnum {

    NANO("nano", "奈米投"),

    FUND("fund", "基金"),

    INTL_ETF_STOCK("intl_etf_stock", "海外ETF / 股票"),

    INTL_BOND("intl_bond", "海外債"),

    SI("si", "組合式商品(SI)"),

    SN("sn", "境內外結構型商品(SN)"),

    OFFSHORE_PE("offshore_pe", "境外私募基金"),

    SI_FM("si_fm", "組合式商品(SI)-金市"),

    INTL_BOND_FM("intl_bond_fm", "外國債券(自營)"),

    GOLD("gold", "黃金存摺"),

    DCI("dci", "外匯雙享利(DCI)");

    private String code;

    private String memo;

    private ProductCategory(String code, String memo) {
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
    
    public static ProductCategory findByCode(String code) {
        return Arrays.stream(values()).filter(pc -> StringUtils.equals(pc.getCode(), code)).findFirst().orElse(null);
    }
}
