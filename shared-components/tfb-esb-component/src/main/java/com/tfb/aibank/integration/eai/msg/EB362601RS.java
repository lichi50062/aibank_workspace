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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB362601SvcRqType;
import tw.com.ibm.mf.eb.EB362601SvcRsType;

// @formatter:off
/**
 * @(#)EB362601RS.java
 * 
 * <p>Description:還本繳息查詢 (公教房貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/ACNO_PH | eai:Tx/TxBody/CUST_NO | eai:Tx/TxBody/CUST_NAME | eai:Tx/TxBody/CORR_ACNO_PH | eai:Tx/TxBody/FLAG2 ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = " eai:Tx/TxBody/PRN_AMT | eai:Tx/TxBody/PRN_AMT2 | eai:Tx/TxBody/INT_AMT | eai:Tx/TxBody/PNT_AMT | eai:Tx/TxBody/DLY_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3,isPostSign = true, fieldXPath = "eai:Tx/TxBody/LOAN_BAL | eai:Tx/TxBody/B_INS_AMT | eai:Tx/TxBody/AR_TOT_AMT | eai:Tx/TxBody/INS_AMT | eai:Tx/TxBody/AR_IRN_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, fieldXPath = "eai:Tx/TxBody/AR_PRN_AMT | eai:Tx/TxBody/AR_PNT_AMT | eai:Tx/TxBody/INT_RATE | eai:Tx/TxBody/AR_PRN_AMT | eai:Tx/TxBody/LOAN_BAL | eai:Tx/TxBody/AR_DLY_AMT ")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath ="eai:Tx/TxBody/DTL_SDATE | eai:Tx/TxBody/DTL_EDATE | eai:Tx/TxBody/TX_DATE")
        }
)
//@formatter:on
public class EB362601RS extends EAIResponse<EB362601SvcRqType, EB362601SvcRsType> {

    /**
     * 建構子
     */
    public EB362601RS() {
        super("EB362601");
    }

}
