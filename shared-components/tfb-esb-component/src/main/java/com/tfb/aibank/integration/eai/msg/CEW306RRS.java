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
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW306RSvcRqType;
import tw.com.ibm.mf.eb.CEW306RSvcRsType;

//@formatter:off
/**
* @(#)CEW306RRS.java
* 
* <p>Description:CEW211R(紅利績點點數查詢)下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Converter(customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
})
public class CEW306RRS extends EAIResponse<CEW306RSvcRqType, CEW306RSvcRsType> {

    /**
     * 建構子
     */
    public CEW306RRS() {
        super("CEW306R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }
}
