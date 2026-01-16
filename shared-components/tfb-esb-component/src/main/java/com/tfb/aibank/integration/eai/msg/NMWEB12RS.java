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
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB12SvcRqType;
import tw.com.ibm.mf.eb.NMWEB12SvcRsType;

//@formatter:off
/**
* @(#)NMWEB12RS.java
* 
* <p>Description: NMWEB12 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/08, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
    },
    decimalConverters = {
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/PayAmt | eai:Tx/TxBody/PayAmt01 | eai:Tx/TxBody/PayAmt02 | eai:Tx/TxBody/PayAmt03 | eai:Tx/TxBody/PayAmt04 | eai:Tx/TxBody/PayAmt05"),
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/PayPercentage01 | eai:Tx/TxBody/PayPercentage02 | eai:Tx/TxBody/PayPercentage03 | eai:Tx/TxBody/PayPercentage04 | eai:Tx/TxBody/PayPercentage05")
    },
    datetimeConverters = {
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/Apply_Date | eai:Tx/TxBody/AuthEndDate")
    }
)
//@formatter:on
public class NMWEB12RS extends EAIResponse<NMWEB12SvcRqType, NMWEB12SvcRsType> {

    public NMWEB12RS() {
        super("NMWEB12");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

    
}
