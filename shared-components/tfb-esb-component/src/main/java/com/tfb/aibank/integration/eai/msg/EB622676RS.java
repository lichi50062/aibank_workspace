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

import tw.com.ibm.mf.eb.EB622676SvcRqType;
import tw.com.ibm.mf.eb.EB622676SvcRsType;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/07/30, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = {
    @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/*"),
})
//@formatter:on
public class EB622676RS extends EAIResponse<EB622676SvcRqType, EB622676SvcRsType> {

    /**
     * 建構子
     */
    public EB622676RS() {
        super("EB622676");

    }
}
