/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW315RSvcRqType;
import tw.com.ibm.mf.eb.CEW315RSvcRsType;

//@formatter:off
/**
* @(#)CEW315RRS.java
* 
* <p>Description:信用卡帳單分期查詢下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = { 
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*  | eai:Tx/TxBody/CARD_HOLDER_ID ")
    }, 
    datetimeConverters = { 
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/BILL_CYCLE_DAY | eai:Tx/TxBody/PAYMENT_DEADLINE | eai:Tx/TxBody/APPLY_DAY | eai:Tx/TxBody/PAYMENT_DEADLINE | eai:Tx/TxBody/TxRepeat/APPLY_DAY | eai:Tx/TxBody/TxRepeat/FIRST_BILLED_DAY")
    }, 
    decimalConverters = { 
        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/PAYMENT_BALANCE | eai:Tx/TxBody/MINIMUM_PAY | eai:Tx/TxBody/INTEREST_RATE | eai:Tx/TxBody/PURCHASE_AMOUNT | eai:Tx/TxBody/TxRepeat/APPROVE_AMOUNT | eai:Tx/TxBody/TxRepeat/UNBILLED_AMOUNT | eai:Tx/TxBody/TxRepeat/INTEREST_RATE | eai:Tx/TxBody/TxRepeat/PERIOD |eai:Tx/TxBody/TxRepeat/BILLED_PREIOD"),
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/PERIOD | eai:Tx/TxBody/PERIOD_OCCUR")
    }
)
//@formatter:on
public class CEW315RRS extends EAIResponse<CEW315RSvcRqType, CEW315RSvcRsType> {

    /**
     * 建構子
     */
    public CEW315RRS() {
        super("CEW315R");
    }

    private String getHfmTidNoSpace() {
        return StringUtils.trimToEmptyEx(getHeader().getHFMTID());
    }

}
