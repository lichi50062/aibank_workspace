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

import tw.com.ibm.mf.eb.EBEAGLEEYESvcRqType;
import tw.com.ibm.mf.eb.EBEAGLEEYESvcRsType;

// @formatter:off
/**
 * @(#)EBEAGLEEYERS.java
 * 
 * <p>Description:分期防詐名單收集及檢核 下行電文(EBEAGLEEYE)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/12, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/CODE | eai:Tx/TxBody/TYPE | eai:Tx/TxBody/MSG")
        }
     )
// @formatter:on
public class EBEAGLEEYERS extends EAIResponse<EBEAGLEEYESvcRqType, EBEAGLEEYESvcRsType> {

    /**
     * 建構子
     */
    public EBEAGLEEYERS() {
        super("EBEAGLEEYE");
    }

}
