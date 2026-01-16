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

import tw.com.ibm.mf.eb.VN084NSvcRqType;
import tw.com.ibm.mf.eb.VN084NSvcRsType;

// @formatter:off
/**
 * @(#)VN084NRS.java
 * 
 * <p>Description:UK084N 股票資產總覽</p>
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
public class VN084NRS extends EAIResponse<VN084NSvcRqType, VN084NSvcRsType> {

    /**
     * 建構子
     */
    public VN084NRS() {
        super("VN084N");
    }
}
