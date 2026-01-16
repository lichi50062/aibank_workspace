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

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.EB553910ASvcRqType;

// @formatter:off
/**
* @(#)EB553910ARQ.java
* 
* <p>Description:預約-繳自行信用卡費上送電文(EB553910A)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 16, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO_OUT | eai:Tx/TxBody/ACNO_IN")
        }, 
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, 
                                outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/PREREQ_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=-2, pattern="000000000000000", fieldXPath = "eai:Tx/TxBody/TX_AMT")
        }
)
//@formatter:on
public class EB553910ARQ extends EAIRequest<EB553910ASvcRqType> {

    private static final long serialVersionUID = -3307638022055576425L;

    /**
     * 建構子
     */
    public EB553910ARQ() {
        super("EB553910A");
    }

}
