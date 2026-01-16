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

import tw.com.ibm.mf.eb.EB172656SvcRqType;

//@formatter:off
/**
* @(#)EB172656RQ.java
* 
* <p>Description:外幣活存交易明細上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/STA_DATE | eai:Tx/TxBody/END_DATE")
        }
)
//@formatter:on
public class EB172656RQ extends EAIRequest<EB172656SvcRqType> {

    private static final long serialVersionUID = 5768482003524932992L;

    /**
     * 建構子
     */
    public EB172656RQ() {
        super("EB172656");
    }

}