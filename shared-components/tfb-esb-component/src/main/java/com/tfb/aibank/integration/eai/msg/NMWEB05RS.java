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

import tw.com.ibm.mf.eb.NMWEB05SvcRqType;
import tw.com.ibm.mf.eb.NMWEB05SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB05RS.java
 * 
 * <p>金錢信託-特金股票台幣現值查詢(NMWEB05)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/SettleDate")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/SettleDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AcctBalNT")
        }
)
// @formatter:on
public class NMWEB05RS extends EAIResponse<NMWEB05SvcRqType, NMWEB05SvcRsType> {

    public NMWEB05RS() {
        super("NMWEB05");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }

}
