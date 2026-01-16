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

import tw.com.ibm.mf.eb.CEW336RSvcRqType;
import tw.com.ibm.mf.eb.CEW336RSvcRsType;

//@formatter:off
/**
* @(#)CEW336RRS.java
* 
* <p>Description:信用卡─虛擬卡片 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/OCCUR")
})
//@formatter:on
public class CEW336RRS extends EAIResponse<CEW336RSvcRqType, CEW336RSvcRsType> {

    /**
     * 建構子.
     */
    public CEW336RRS() {
        super("CEW336R");
    }
}
