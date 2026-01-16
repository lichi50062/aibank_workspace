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

import tw.com.ibm.mf.eb.EB352601SvcRqType;
import tw.com.ibm.mf.eb.EB352601SvcRsType;

// @formatter:off
/**
 * @(#)EB352601RS.java
 * 
 * <p>Description:還本繳息查詢 (國宅房貸) RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/ACNO_PH | eai:Tx/TxBody/CUST_NO | eai:Tx/TxBody/CUST_NAME | eai:Tx/TxBody/FLAG2 | eai:Tx/TxBody/CORR_ACNO_PH")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath ="eai:Tx/TxBody/DTL_SDATE | eai:Tx/TxBody/DTL_EDATE | eai:Tx/TxBody/TX_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/PRN_AMT | eai:Tx/TxBody/B_LOAN_ABL   | eai:Tx/TxBody/AR_PNT_AMT | eai:Tx/TxBody/AR_DLY_AMT | eai:Tx/TxBody/DAYS_1 | eai:Tx/TxBody/PRN_AMT2 |  eai:Tx/TxBody/DLY_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3,isPostSign = true, fieldXPath =
                                 "eai:Tx/TxBody/B_INS_AMT| "
                                + "eai:Tx/TxBody/INS_AMT | eai:Tx/TxBody/AR_IRN_AMT | eai:Tx/TxBody/LOAN_BAL | "
                                + "eai:Tx/TxBody/AR_TOT_AMT  "
                ),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, fieldXPath = "eai:Tx/TxBody/INT_RATE | eai:Tx/TxBody/AR_PRN_AMT")
        }
)
//@formatter:on
public class EB352601RS extends EAIResponse<EB352601SvcRqType, EB352601SvcRsType> {

    /**
     * 建構子
     */
    public EB352601RS() {
        super("EB352601");
    }

}
