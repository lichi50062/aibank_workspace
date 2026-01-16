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

import tw.com.ibm.mf.eb.EB553910ASvcRqType;
import tw.com.ibm.mf.eb.EB553910ASvcRsType;

//@formatter:off
/**
* @(#)EB553910ARS.java
* 
* <p>Description:預約-繳自行信用卡費下行電文(EB553910A)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/FEE")
        },
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE")
        }
)
//@formatter:on
public class EB553910ARS extends EAIResponse<EB553910ASvcRqType, EB553910ASvcRsType> {
    /**
     * 建構子
     */
    public EB553910ARS() {
        super("EB553910A");
    }

}
