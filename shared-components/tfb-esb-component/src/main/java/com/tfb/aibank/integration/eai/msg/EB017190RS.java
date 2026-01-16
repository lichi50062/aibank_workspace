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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB017190SvcRqType;
import tw.com.ibm.mf.eb.EB017190SvcRsType;

// @formatter:off
/**
 * @(#)EB017190RS.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=房貸(61)-02公教/03國宅)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,
                        fieldXPath = " eai:Tx/TxBody/LOAN_BAL | Tx/TxBody/FX_EX_RATE | eai:Tx/TxBody/INS_BAL | eai:Tx/TxBody/LTG_BAL | eai:Tx/TxBody/AR_TOTAL_AMT"),
        }
)
public class EB017190RS extends EAIResponse<EB017190SvcRqType, EB017190SvcRsType> {

    /**
     * 建構子
     */
    public EB017190RS() {
        super("EB017190");
    }

}
