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
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB555789SvcRqType;
import tw.com.ibm.mf.eb.EB555789SvcRsType;

// @formatter:off
/**
 * @(#)EB555789RS.java
 * 
 * <p>Description:取得貸款帳號 下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/16, John Chang 	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/IDNO | eai:Tx/TxBody/ACT_STS | eai:Tx/TxBody/TxRepeat/*[name()!='SLIP_NO' and name()!='DOC_NO'] | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/DUE_DATE ")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AVAL_AMT | eai:Tx/TxBody/TxRepeat/ACT_BAL | eai:Tx/TxBody/TxRepeat/ACT_BAL_NT | eai:Tx/TxBody/TxRepeat/ORI_LOAN_BAL | eai:Tx/TxBody/ORI_LOAN_BAL | eai:Tx/TxBody/TxRepeat/OD_TOT_LIMIT | eai:Tx/TxBody/ThreshAmt | eai:Tx/TxBody/odavail1"), 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 7, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/RATE"), 
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/INS_AMT | eai:Tx/TxBody/INS_AMT")
                
        
        }
)
// @formatter:on
public class EB555789RS extends EAIResponse<EB555789SvcRqType, EB555789SvcRsType> {

    /**
     * 建構子
     */
    public EB555789RS() {
        super("EB555789");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "0188".equals(errId);
    }
}
