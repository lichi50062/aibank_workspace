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
package com.tfb.aibank.common.type;

import java.util.Arrays;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)FundCancelTxType.java
* 
* <p>Description: 基金在途交易管理 取消交易類型</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/26, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum FundCancelTxType implements IEnum {

    /** 申購在途取消 */
    PURCHASE_CANCELED("10", "fund_tx_type.purchase_canceled"),

    /** 轉換在途取消 */
    CONVERSION_CANCELED("20", "fund_tx_type.conversion_canceled"),

    /** 贖回在途取消 */
    REDEMPTION_CANCELED("30", "fund_tx_type.redemption_canceled"),;

    private String code;

    private String memo;

    private FundCancelTxType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return I18NUtils.getMessage(this.memo);
    }

    public static FundCancelTxType findByCode(String code) {
        return Arrays.stream(values()).filter(f -> StringUtils.equals(f.getCode(), code)).findFirst().orElse(null);
    }
}
