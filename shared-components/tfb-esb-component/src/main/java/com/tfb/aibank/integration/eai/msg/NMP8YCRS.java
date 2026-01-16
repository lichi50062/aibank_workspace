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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMP8YCSvcRqType;
import tw.com.ibm.mf.eb.NMP8YCSvcRsType;

// @formatter:off
/**
 * @param 
 * @(#)NMP8YC.java
 * 
 * <p>Description:奈米投契約庫存查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/Unit|eai:Tx/TxBody/TxRepeat/CostPrice|eai:Tx/TxBody/TxRepeat/ClosePrice"), @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/Rate|eai:Tx/TxBody/TxRepeat/RefMarketVal"),
        }
)
public class NMP8YCRS extends EAIResponse<NMP8YCSvcRqType, NMP8YCSvcRsType> {

    /**
     * 建構子
     */
    public NMP8YCRS() {
        super("NMP8YC");
    }

}
