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
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EBHD7000SvcRqType;
import tw.com.ibm.mf.eb.EBHD7000SvcRsType;

// @formatter:off
/**
 * @(#)EB202674RS.java
 * 
 * <p>Description:歷史交易明細 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
                @CustomConverter(converter = EAIReplaceConverter.class, replaceRegex = "[\\$,]", fieldXPath = "eai:Tx/TxBody/TxRepeat/DEBIT_AMT | eai:Tx/TxBody/TxRepeat/CREDIT_AMT | eai:Tx/TxBody/TxRepeat/ACT_BAL")
                        
        },
        datetimeConverters = {
                @DateAndTimeConverter(
                        converter = EAIDateConverter.class,
                        inputPattern="yyyyMMdd", 
                        fieldXPath="eai:Tx/TxBody/TxRepeat/TXDATE | eai:Tx/TxBody/TxRepeat/ACT_DATE | eai:Tx/TxBody/TxRepeat/BK_VALUE | eai:Tx/TxBody/TxRepeat/TXDATE_2 | eai:Tx/TxBody/TxRepeat/BK_VALUE_2 | eai:Tx/TxBody/TxRepeat/DUE_DTE_2 | eai:Tx/TxBody/TxRepeat/PAY_DATE"),
                @DateAndTimeConverter(
                        converter = EAITimeConverter.class, 
                        inputPattern = "HHmmss", 
                        fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_TIME")
        }
)
//@formatter:on
public class EBHD7000RS extends EAIResponse<EBHD7000SvcRqType, EBHD7000SvcRsType> {

    /**
     * 建構子
     */
    public EBHD7000RS() {
        super("EBHD7000");
    }

}
