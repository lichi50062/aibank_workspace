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

import tw.com.ibm.mf.eb.EB202674SvcRqType;

//@formatter:off
/**
* @(#)EB202674RQ.java
* 
* <p>Description:一本萬利交易明細上行電文處理</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/StartDt | eai:Tx/TxBody/EndDt")
        }
)
//@formatter:on
public class EB202674RQ extends EAIRequest<EB202674SvcRqType> {

    private static final long serialVersionUID = -2073376657694983961L;

    /**
     * 建構子
     */
    public EB202674RQ() {
        super("EB202674");
    }

}
