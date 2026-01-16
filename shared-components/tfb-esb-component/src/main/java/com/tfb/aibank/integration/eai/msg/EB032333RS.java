/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032333SvcRqType;
import tw.com.ibm.mf.eb.EB032333SvcRsType;

//@formatter:off
/**
* @(#)EB032333RS.java
* 
* <p>Description:EB032333RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/02, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"),
        }
)
// @formatter:on
public class EB032333RS extends EAIResponse<EB032333SvcRqType, EB032333SvcRsType> {

    /**
     * 建構子
     */
    public EB032333RS() {
        super("EB032333");
    }

}