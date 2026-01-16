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

import tw.com.ibm.mf.eb.EB000400SvcRqType;
import tw.com.ibm.mf.eb.EB000400SvcRsType;

// @formatter:off
/**
 * @(#)EB032159RS.java
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
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/accntNumber | eai:Tx/TxBody/Status | eai:Tx/TxBody/AcctTypDesc | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/CurBal"),
        }
)
// @formatter:on
public class EB000400RS extends EAIResponse<EB000400SvcRqType, EB000400SvcRsType> {

    /**
     * 建構子
     */
    public EB000400RS() {
        super("EB000400");
    }
}