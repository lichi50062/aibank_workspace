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

import tw.com.ibm.mf.eb.EB613956SvcRqType;

// @formatter:off
/**
 * @(#)EB613956RQ.java
 *
 * <p>Description:網路還本繳息 RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/PAY_DATE"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3, pattern = "00000000000000000", showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/RCV_AMT_NT | eai:Tx[./TxHead/HFUNC='1']/TxBody/AR_INT_AMT | eai:Tx/TxBody/AR_PNT_AMT"),
        }
)
//@formatter:on
public class EB613956RQ extends EAIRequest<EB613956SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB613956RQ() {
        super("EB613956");
    }

}
