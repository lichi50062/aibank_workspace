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

import tw.com.ibm.mf.eb.EB372602SvcRqType;
import tw.com.ibm.mf.eb.EB372602SvcRsType;

// @formatter:off
/**
 * @(#)EB372602RS.java
 * 
 * <p>Description:還本繳息查詢 (分期型房貸、分期型信貸、留貸、企貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_LN | eai:Tx/TxBody/PROD_STAG | eai:Tx/TxBody/DOC_NO | eai:Tx/TxBody/DOC_SEQ | eai:Tx/TxBody/GL_BRH | eai:Tx/TxBody/GL_GLCD4 | eai:Tx/TxBody/GL_GLCD5 | eai:Tx/TxBody/GL_GLCD6 | eai:Tx/TxBody/CUST_NO | eai:Tx/TxBody/CUST_NAME | eai:Tx/TxBody/CUR_COD | eai:Tx/TxBody/FLAG2 | eai:Tx/TxBody/TYPE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/B_LOAN_ABL | eai:Tx/TxBody/AR_PRN_AMT | eai:Tx/TxBody/AR_IRN_AMT | eai:Tx/TxBody/AR_PNT_AMT | eai:Tx/TxBody/AR_DLY_AMT | eai:Tx/TxBody/DAYS_1 | eai:Tx/TxBody/PRN_AMT2 |   eai:Tx/TxBody/FX_EX_RATE | eai:Tx/TxBody/INS_BAL | eai:Tx/TxBody/LTG_BAL | eai:Tx/TxBody/AR_TOTAL_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3,isPostSign = true, fieldXPath = " eai:Tx[./TxHead/HFMTID='D002']/TxBody/INS_AMT | eai:Tx/TxBody/O_INS_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/PNT_AMT | eai:Tx/TxBody/LOAN_BAL | eai:Tx/TxBody/INT_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, fieldXPath = "eai:Tx/TxBody/INT_RATE | eai:Tx/TxBody/PRN_AMT | eai:Tx[./TxHead/HFMTID='D003']/TxBody/PNT_AMT | eai:Tx/TxBody/DLY_AMT | eai:Tx[./TxHead/HFMTID='D003']/TxBody/INS_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/PRN_AMT_1")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath ="eai:Tx/TxBody/DTL_SDATE | eai:Tx/TxBody/DTL_EDATE | eai:Tx/TxBody/TX_DATE")
        }
)
//@formatter:on
public class EB372602RS extends EAIResponse<EB372602SvcRqType, EB372602SvcRsType> {

    /**
     * 建構子
     */
    public EB372602RS() {
        super("EB372602");
    }

}
