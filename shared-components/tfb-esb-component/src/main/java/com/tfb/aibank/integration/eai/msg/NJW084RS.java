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

import tw.com.ibm.mf.eb.NJW084SvcRqType;
import tw.com.ibm.mf.eb.NJW084SvcRsType;

// @formatter:off
/**
 * @(#)NJW084RS.java
 * 
 * <p>Description:NJW084 債券資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*  | eai:Tx/TxBody/Occur")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=0, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/BondAmt1 | eai:Tx/TxBody/TxRepeat/BondAmt2"),
        }
)
// @formatter:on
public class NJW084RS extends EAIResponse<NJW084SvcRqType, NJW084SvcRsType> {

    /**
     * 建構子
     */
    public NJW084RS() {
        super("NJW084");
    }
}
