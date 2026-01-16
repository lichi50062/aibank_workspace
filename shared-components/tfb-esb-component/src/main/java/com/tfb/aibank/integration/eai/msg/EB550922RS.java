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

import tw.com.ibm.mf.eb.EB550922SvcRqType;
import tw.com.ibm.mf.eb.EB550922SvcRsType;

//@formatter:off
/**
* @(#)EB550922RS.java
* 
* <p>Description: 外幣定存即時解約下行電文(EB550922)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/11, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:off

@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                 @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/FEE"),
                 @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAIL_BAL | eai:Tx/TxBody/OD_AMT | eai:Tx/TxBody/INT_PAY | eai:Tx/TxBody/FDAMT")
        },
        datetimeConverters = {
                @DateAndTimeConverter(
                converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE")
        }
)
//@formatter:on
public class EB550922RS extends EAIResponse<EB550922SvcRqType, EB550922SvcRsType> {
    /**
     * 建構子
     */
    public EB550922RS() {
        super("EB550922");
    }
}
