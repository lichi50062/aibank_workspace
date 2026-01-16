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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.SPWEBQ1SvcRqType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRsType;

// @formatter:off
/**
 * @(#)SPWEBQ1RS.java
 * 
 * <p>Description:SPWEBQ1 組合式商品資產總覽(AS400 DCI+SI)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=0, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/Amount "),
        }
)
// @formatter:on
public class SPWEBQ1RS extends EAIResponse<SPWEBQ1SvcRqType, SPWEBQ1SvcRsType> {

    /**
     * 建構子
     */
    public SPWEBQ1RS() {
        super("SPWEBQ1");
    }
}
