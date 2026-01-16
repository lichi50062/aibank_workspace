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
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB172656SvcRqType;
import tw.com.ibm.mf.eb.EB172656SvcRsType;

// @formatter:off
/**
 * @(#)EB552170RS.java
 * 
 * <p>Description:台幣活存交易明細下行電文 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*[name() != 'RMK'] | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_FS | eai:Tx/TxBody/STA_DATE | eai:Tx/TxBody/END_DATE")
        },
        datetimeConverters={
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern ="ddMMyyyy", fieldXPath="eai:Tx/TxBody/STA_DATE | eai:Tx/TxBody/END_DATE | eai:Tx/TxBody/TxRepeat/TX_DATE | eai:Tx/TxBody/TxRepeat/ACT_DATE"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_TIME")
        },  
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_AMT | eai:Tx/TxBody/TxRepeat/FS_BAL")
        }
)
//@formatter:on
public class EB172656RS extends EAIResponse<EB172656SvcRqType, EB172656SvcRsType> {

    /**
     * 建構子
     */
    public EB172656RS() {
        super("EB172656");
    }

}
