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

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB362601SvcRqType;

// @formatter:off
/**
 * @(#)EB362601RQ.java
 * 
 * <p>Description:還本繳息查詢 (公教房貸) RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TX_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3,pattern = "0000000000000000",fieldXPath = "eai:Tx/TxBody/PRN_AMT")
        }
)
// @formatter:on
public class EB362601RQ extends EAIRequest<EB362601SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB362601RQ() {
        super("EB362601");
    }

}
