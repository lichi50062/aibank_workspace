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
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CE6210RSvcRqType;
import tw.com.ibm.mf.eb.CE6210RSvcRsType;

// @formatter:off
/**
 * @(#)CE6210RRS.java
 * 
 * <p>Description:客戶資料查詢下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*"
                        + " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/Occur"),
                @CustomConverter(converter = EAIReplaceConverter.class, replaceRegex = "", replacement = "○", fieldXPath = "eai:Tx/TxBody/CARD_HOLDER_NAME")
        }
)
// @formatter:on
public class CE6210RRS extends EAIResponse<CE6210RSvcRqType, CE6210RSvcRsType> {

    /**
     * 建構子
     */
    public CE6210RRS() {
        super("CE6210R");
    }

}
