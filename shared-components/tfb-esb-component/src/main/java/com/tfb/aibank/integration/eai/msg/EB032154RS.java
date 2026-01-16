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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032154SvcRqType;
import tw.com.ibm.mf.eb.EB032154SvcRsType;

// @formatter:off
/**
 * @(#)EB032154RS.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, isPostSign = true, fieldXPath = "eai:Tx/TxBody/Amount ")
        }
)
// @formatter:on
public class EB032154RS extends EAIResponse<EB032154SvcRqType, EB032154SvcRsType> {

    /**
     * 建構子
     */
    public EB032154RS() {
        super("EB032154");
    }
}
