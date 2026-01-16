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

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB372602SvcRqType;

// @formatter:off
/**
 * @(#)EB372602RQ.java
 * 
 * <p>Description:還本繳息查詢 (分期型房貸、分期型信貸、留貸、企貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/FUNC | eai:Tx/TxBody/ACNO_LN | eai:Tx/TxBody/DOC_NO | eai:Tx/TxBody/DOC_SEQ ")
        },
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/RCV_DATE ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,scale = -3,pattern = "0000000000000000", fieldXPath = "eai:Tx/TxBody/PRN_AMT ")
        }
)
// @formatter:on
public class EB372602RQ extends EAIRequest<EB372602SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB372602RQ() {
        super("EB372602");
    }

}
