/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NAWEBP04SvcRqType;
import tw.com.ibm.mf.eb.NAWEBP04SvcRsType;

// @formatter:off
/**
 * @(#)NAWEBP04RS.java
 * 
 * <p>一般型股票查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/DeliveryDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/CashOnDelivery | eai:Tx/TxBody/TxRepeat/ReturnOfCash | eai:Tx/TxBody/TxRepeat/NoReturnOfCash "),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/DeliveryOfShares | eai:Tx/TxBody/TxRepeat/ReturnShares | eai:Tx/TxBody/TxRepeat/NoReturnShares ")
        }
)
//@formatter:on
public class NAWEBP04RS extends EAIResponse<NAWEBP04SvcRqType, NAWEBP04SvcRsType> {

    /**
     * 建構子
     */
    public NAWEBP04RS() {
        super("NAWEBP04");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "EA07".equals(errId);
    }

}
