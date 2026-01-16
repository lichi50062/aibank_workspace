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

import tw.com.ibm.mf.eb.CE4153RSvcRqType;
import tw.com.ibm.mf.eb.CE4153RSvcRsType;

//@formatter:off
/**
* @(#)CE4153RRS.java
* 
* <p>Description: 指定消費分期設定交易 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
},
//TODO 待測資確認
datetimeConverters = { 
     @DateAndTimeConverter(converter = EAIDateConverter.class, 
             inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/PCHDAY | eai:Tx/TxBody/TxRepeat/NCCDAY")
})
//@formatter:on
public class CE4153RRS extends EAIResponse<CE4153RSvcRqType, CE4153RSvcRsType> {

    /**
     * 建構子.
     */
    public CE4153RRS() {
        super("CE4153R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
