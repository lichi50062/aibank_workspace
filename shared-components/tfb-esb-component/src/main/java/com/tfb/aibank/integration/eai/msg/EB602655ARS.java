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

import java.util.List;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB602655ASvcRqType;
import tw.com.ibm.mf.eb.EB602655ASvcRsType;

// @formatter:off
/**
 * @(#)EB5557891RS.java
 * 
 * <p>Description:查詢簽帳金融卡卡號電文下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
public class EB602655ARS extends EAIResponse<EB602655ASvcRqType, EB602655ASvcRsType> {
    /**
     * 建構子
     */
    public EB602655ARS() {
        super("EB602655A");
    }

    @Override
    protected boolean isNoData(String errId) {
        return List.of("E005", "ERH2").contains(errId);
    }
}
