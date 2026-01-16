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

import tw.com.ibm.mf.eb.TMLN003SvcRqType;
import tw.com.ibm.mf.eb.TMLN003SvcRsType;

// @formatter:off
/**
 * @(#)EBLN010RS.java
 * 
 * <p>Description:房信貸留資 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/04 Rick
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
public class TMLN003RS extends EAIResponse<TMLN003SvcRqType, TMLN003SvcRsType> {

    /**
     * 建構子
     */
    public TMLN003RS() {
        super("TMLN003");
    }
}
