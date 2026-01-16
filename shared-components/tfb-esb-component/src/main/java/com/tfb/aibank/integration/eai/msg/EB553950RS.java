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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB553950SvcRqType;
import tw.com.ibm.mf.eb.EB553950SvcRsType;

//@formatter:off
/**
* @(#)EB553950RS.java
* 
* <p>Description:即時-繳他行信用卡費下行電文(EB553950)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
                customConverters = {
                                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxBody/*")
                },
                decimalConverters = {
                                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/AVL_AMT " +
                                                "| eai:Tx/TxBody/AVL_BAL | eai:Tx/TxBody/ACC_AMT | eai:Tx/TxBody/ACC_BAL"),
                                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/FEE ")
                },
                datetimeConverters = {
                                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/ACT_DATE")
                })
//@formatter:on
public class EB553950RS extends EAIResponse<EB553950SvcRqType, EB553950SvcRsType> {

    /**
     * 建構子
     */
    public EB553950RS() {
        super("EB553950");
    }

}
