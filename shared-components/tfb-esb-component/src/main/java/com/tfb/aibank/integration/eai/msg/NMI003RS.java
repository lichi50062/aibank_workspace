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

import tw.com.ibm.mf.eb.NMI003SvcRqType;
import tw.com.ibm.mf.eb.NMI003SvcRsType;

// @formatter:off
/**
 * @param 
 * @(#)NMI003.java
 * 
 * <p>Description:奈米投契約交易紀錄查詢</p>
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
        }
)
public class NMI003RS extends EAIResponse<NMI003SvcRqType, NMI003SvcRsType> {

    /**
     * 建構子
     */
    public NMI003RS() {
        super("NMI003");
    }

}
