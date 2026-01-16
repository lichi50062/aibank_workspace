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

import java.util.Arrays;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)InvestProductTypeDisplayName.java
* 
* <p>Description: 投資商品類型</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/31, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum InvestProductTypeDisplayName implements IEnum {

    GOLD("goldTtlAmt", "ngnqu001.invest.gold"), // 黃金

    NANO("nanoTtlAmt", "ngnqu001.invest.nano"), // 奈米投

    OFF_STOCKS("offStocksTtlAmt", "ngnqu001.invest.offstocks"), // "海外ETF/股票"

    OFF_STRUCTURED("offStructuredTtlAmt", "ngnqu001.invest.offstructured"), // "境內外結構型商品"

    OFF_BONDS("offBondsTtlAmt", "ngnqu001.invest.offbonds"), // "海外債"

    FUND("fundTtlAmt", "ngnqu001.invest.fund"), // "基金"

    COMBO("comboTtlAmt", "ngnqu001.invest.combo"), // "組合式商品"

    FOREIGN_BONDS("foreignBondsTtlAmt", "ngnqu001.invest.foreignbonds"); // 外國債券(自營)

    private String memo;
    private String productTypeName;

    InvestProductTypeDisplayName(String memo, String productTypeName) {
        this.memo = memo;
        this.productTypeName = productTypeName;
    }

    @Override
    public String getMemo() {
        return this.memo;
    }

    public String getProductTypeName() {
        return I18NUtils.getMessage(productTypeName);
    }

    public static InvestProductTypeDisplayName findByMemo(String memo) {
        return Arrays.stream(values()).filter(inv -> StringUtils.equals(inv.getMemo(), memo)).findFirst().orElse(null);
    }
}
