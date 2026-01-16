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
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.EBHD7000SvcRqType;

//@formatter:off
/**
* @(#)EB202674RQ.java
* 
* <p>Description:歷史交易明細上行電文處理</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TXDATES | eai:Tx/TxBody/TXDATEE"),
        }
)
//@formatter:on
public class EBHD7000RQ extends EAIRequest<EBHD7000SvcRqType> {

    private static final long serialVersionUID = 3002651133177112438L;

    /**
     * 建構子
     */
    public EBHD7000RQ() {
        super("EBHD7000");
    }
}
