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
import tw.com.ibm.mf.eb.EB610955SvcRqType;
import tw.com.ibm.mf.eb.EB610955SvcRsType;

// @formatter:off
/**
 * @(#)EB610955RS.java
 * 
 * <p>Description:EB610955 即時轉帳 下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/AVAIL_BAL | eai:Tx/TxBody/ACT_BAL "),
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/FEE ")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class,
                        inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE"),
        }
)
public class EB610955RS extends EAIResponse<EB610955SvcRqType, EB610955SvcRsType> {
    /**
     * 建構子
     */
    public EB610955RS() {
        super("EB610955");
    }
}
