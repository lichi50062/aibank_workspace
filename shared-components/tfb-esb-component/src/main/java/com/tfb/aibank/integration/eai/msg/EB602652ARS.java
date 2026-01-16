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
import tw.com.ibm.mf.eb.EB602652ASvcRqType;
import tw.com.ibm.mf.eb.EB602652ASvcRsType;

import java.util.List;

// @formatter:off
/**
 * @(#)EB602652ARS.java
 * 
 * <p>Description:查詢客戶簽帳卡資料 電文下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/03, Bill Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
public class EB602652ARS extends EAIResponse<EB602652ASvcRqType, EB602652ASvcRsType> {
    /**
     * 建構子
     */
    public EB602652ARS() {
        super("EB602652A");
    }

    @Override
    protected boolean isNoData(String errId) {
        return List.of("ERI4", "ERH2").contains(errId);
    }
}
