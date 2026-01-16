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

import tw.com.ibm.mf.eb.EB141153SvcRqType;
import tw.com.ibm.mf.eb.EB141153SvcRsType;

//@formatter:off
/**
 * @(#)EB141153RS.java
 * 
 * <p>Description: 定存到期預約設定╱變更下行電文(EB141153)</p>
 * 
* <p>Modify History:</p>
* v1.0, 2023/07/10, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
//@formatter:on
public class EB141153RS extends EAIResponse<EB141153SvcRqType, EB141153SvcRsType> {

    /**
     * 建構子
     */
    public EB141153RS() {
        super("EB141153");
    }
}
