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

import tw.com.ibm.mf.eb.EB382601SvcRqType;
import tw.com.ibm.mf.eb.EB382601SvcRsType;

// @formatter:off
/**
 * @(#)EB382601RS.java
 * 
 * <p>Description:還本繳息查詢 (就學貸款)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_SL | eai:Tx/TxBody/PRN_PAY_TYP | eai:Tx/TxBody/YR_TERM | eai:Tx/TxBody/CUST_NO | eai:Tx/TxBody/CUST_NAME | eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/CUR_COD | eai:Tx/TxBody/FLAG2")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,
                        fieldXPath = "eai:Tx/TxBody/LOAN_BAL | eai:Tx/TxBody/FX_EX_RATE | eai:Tx/TxBody/INS_BAL | eai:Tx/TxBody/LTG_BAL | eai:Tx/TxBody/AR_TOT_AMT | eai:Tx/TxBody/DAY_CNT | eai:Tx/TxBody/AR_LGL_AMT | eai:Tx/TxBody/AR_INS_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3,isPostSign = true, fieldXPath = " eai:Tx/TxBody/AR_INT_AMT | eai:Tx/TxBody/O_INS_AMT | eai:Tx/TxBody/RCV_AMT | eai:Tx/TxBody/B_LOAN_ABL | eai:Tx/TxBody/INS_AMT | eai:Tx/TxBody/AMT_TOT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, 
                    fieldXPath = "eai:Tx/TxBody/PRN_AMT | eai:Tx/TxBody/INT_RATE | eai:Tx/TxBody/AMT | "
                        + "eai:Tx/TxBody/AR_PRN_AMT |  eai:Tx/TxBody/DLY_AMT | eai:Tx/TxBody/AR_DLY_AMT | "
                        + "eai:Tx[./TxHead/HFMTID='D003']/TxBody/AR_PNT_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/AR_PNT_AMT")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath ="eai:Tx[./TxHead/HFMTID='D003']/TxBody/STR_DATE | eai:Tx[./TxHead/HFMTID='D003']/TxBody/END_DATE | eai:Tx/TxBody/TX_DATE")
        }
)
//@formatter:on
public class EB382601RS extends EAIResponse<EB382601SvcRqType, EB382601SvcRsType> {

    /**
     * 建構子
     */
    public EB382601RS() {
        super("EB382601");
    }

}
