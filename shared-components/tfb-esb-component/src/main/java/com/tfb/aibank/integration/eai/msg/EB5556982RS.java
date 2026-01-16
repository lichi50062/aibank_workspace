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

import tw.com.ibm.mf.eb.EB5556982SvcRqType;
import tw.com.ibm.mf.eb.EB5556982SvcRsType;

// @formatter:off
/**
 * @(#)EB5556982RS.java
 * 
 * <p>Description:檢核單一戶名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/NAMCOD_CHK | eai:Tx/TxBody/INVEST_COD | eai:Tx/TxBody/IDERR_FLG")
        }
)
// @formatter:on
public class EB5556982RS extends EAIResponse<EB5556982SvcRqType, EB5556982SvcRsType> {

    /**
     * 建構子
     */
    public EB5556982RS() {
        super("EB5556982");
    }
}