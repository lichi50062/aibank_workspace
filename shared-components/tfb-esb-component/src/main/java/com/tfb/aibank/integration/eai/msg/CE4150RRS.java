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
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CE4150RSvcRqType;
import tw.com.ibm.mf.eb.CE4150RSvcRsType;

//@formatter:off
/**
* @(#)CE4150RS.java
* 
* <p>Description: 綜所稅分期設定交易 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/03/17, Gang Rong
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
},
datetimeConverters = { 
     @DateAndTimeConverter(converter = EAIDateConverter.class, 
             inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/PCHDAY")
})
//@formatter:on
public class CE4150RRS extends EAIResponse<CE4150RSvcRqType, CE4150RSvcRsType> {

    /**
     * 建構子.
     */
    public CE4150RRS() {
        super("CE4150R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
