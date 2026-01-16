/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12010026SvcRqType;
import tw.com.ibm.mf.eb.EB12010026SvcRsType;

//@formatter:off
/**
* @(#)EB12010026RS.java
* 
* <p>Description: EB12010026 電文下行</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/03/25, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"),
    }
)
//@formatter:on
public class EB12010026RS extends EAIResponse<EB12010026SvcRqType, EB12010026SvcRsType> {

    /**
     * 建構子
     */
    public EB12010026RS() {
        super("EB12010026");
    }
    
}
