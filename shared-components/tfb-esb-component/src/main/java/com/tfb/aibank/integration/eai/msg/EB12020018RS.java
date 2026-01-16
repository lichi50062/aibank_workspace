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
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020018SvcRqType;
import tw.com.ibm.mf.eb.EB12020018SvcRsType;

// @formatter:off
/**
 * @(#)EB12020018RS.java
 * 
 * <p>Description: 數位活儲設定的下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/MSG_COD | eai:Tx/TxBody/TxRepeat/MSG_TXT | eai:Tx/TxBody/TxRepeat/TX_DATE | eai:Tx/TxBody/TxRepeat/SEQNO"),
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "MMddyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DATE"), @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "dd/MM/yyyy", fieldXPath = "eai:Tx/TxBody/OPN_DATE")
        }
)
// @formatter:on
public class EB12020018RS extends EAIResponse<EB12020018SvcRqType, EB12020018SvcRsType> {

    /**
     * 建構子
     */
    public EB12020018RS() {
        super("EB12020018");
    }
}