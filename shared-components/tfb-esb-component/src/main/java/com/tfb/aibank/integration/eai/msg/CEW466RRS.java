/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW466RSvcRqType;
import tw.com.ibm.mf.eb.CEW466RSvcRsType;

// @formatter:off
/**
 * @(#)CEW466RRS.java
 * 
 * <p>Description:[會員自動續約]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
//@formatter:on
public class CEW466RRS extends EAIResponse<CEW466RSvcRqType, CEW466RSvcRsType> {
    /**
     * 建構子
     */
    public CEW466RRS() {
        super("CEW466R");
    }
}
