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

import tw.com.ibm.mf.eb.NR051NSvcRqType;
import tw.com.ibm.mf.eb.NR051NSvcRsType;

// @formatter:off
/**
 * @(#)NR051NRS.java
 * 
 * <p>Description: NR051N 海外ETF/股票股務活動查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/17, Yoyo Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/NoteDay | eai:Tx/TxBody/TxRepeat/ReplyDay | eai:Tx/TxBody/TxRepeat/DataDay")
        }
)
// @formatter:on
public class NR051NRS extends EAIResponse<NR051NSvcRqType, NR051NSvcRsType> {

    /**
     * 建構子
     */
    public NR051NRS() {
        super("NR051N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
