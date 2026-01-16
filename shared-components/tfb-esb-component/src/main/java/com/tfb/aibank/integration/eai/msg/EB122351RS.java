/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB122351SvcRqType;
import tw.com.ibm.mf.eb.EB122351SvcRsType;

//@formatter:off
/**
* @(#)EB122351RS.java
* 
* <p>Description: EB122351 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/03/24, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
            " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_OUT" +
            " | eai:Tx/TxBody/COMPA_COD | eai:Tx/TxBody/OFIC | eai:Tx/TxBody/COMPA_NAME" +
            " | eai:Tx/TxBody/ACNO_FEE | eai:Tx/TxBody/ACNO | eai:Tx/TxBody/STS | eai:Tx/TxBody/CRE_DATE" +
            " | eai:Tx/TxBody/MTN_DATE | eai:Tx/TxBody/TLR_NO | eai:Tx/TxBody/AUT_NO"),
    },
    datetimeConverters = {
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccc/MM/dd", fieldXPath = "eai:Tx/TxBody/CRE_DATE | eai:Tx/TxBody/MTN_DATE" +
            " | eai:Tx/TxBody/TxRepeat/CRE_DATE | eai:Tx/TxBody/TxRepeat/MTN_DATE"),
    }
)
//@formatter:on
public class EB122351RS extends EAIResponse<EB122351SvcRqType, EB122351SvcRsType> {

    /**
     * 建構子
     */
    public EB122351RS() {
        super("EB122351");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E005".equals(errId) || "EBB2".equals(errId);
    }

}
