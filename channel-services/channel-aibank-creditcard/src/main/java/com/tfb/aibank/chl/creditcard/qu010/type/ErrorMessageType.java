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
package com.tfb.aibank.chl.creditcard.qu010.type;

import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.type.IEnum;

//@formatter:off
/**
* @(#)ErrorMessageType.java
* 
* <p>Description: 錯誤訊息</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/11/12, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum ErrorMessageType implements IEnum {

    NO_CREDIT_CARD("nccqu010.010.error.title.no-credit-card", "nccqu010.010.error.message.no-credit-card", "無信用卡"),

    NO_DATA("nccqu010.010.error.title.no-data", "nccqu010.010.error.message.no-data", "無資料"),

    SPECIAL_CARD("nccqu010.010.error.title.special-card", "nccqu010.010.error.message.special-card.no-agrs", "特殊卡戶");

    private String title;

    private String desc;

    /** 說明* */
    private String memo;

    @Override
    public String getMemo() {
        return this.memo;
    }

    public String getTitle() {
        return I18NUtils.getMessage(this.title);
    }

    public String getDesc() {
        return I18NUtils.getMessage(this.desc);
    }

    private ErrorMessageType(String title, String desc, String memo) {
        this.title = title;
        this.desc = desc;
        this.memo = memo;
    }

}
