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

import tw.com.ibm.mf.eb.EB613956SvcRqType;
import tw.com.ibm.mf.eb.EB613956SvcRsType;

//@formatter:off
/**
* @(#)EB613956RS.java
* 
* <p>Description:網路還本繳息 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/05, Jackie Chien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/FLAG2 | eai:Tx/TxBody/FORMAT_ID ")
        },
     datetimeConverters = { 
             @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/RCV_DATE | eai:Tx/TxBody/DTL_SDATE | eai:Tx/TxBody/DTL_EDATE | eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE"),
     },
     decimalConverters = { 
             @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/OVERDUE | eai:Tx/TxBody/A_LOAN_BAL "),
             @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true,pattern = "00000000000000000", fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/AR_PRN_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/AR_INT_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/AR_PNT_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/AR_DLY_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/AR_TOTAL_AMT |"
                     +" eai:Tx[./TxHead/HFMTID='D002']/TxBody/AR_INT_AMT |"
                     +" eai:Tx[./TxHead/HFMTID='D003']/TxBody/ACT_BAL | eai:Tx[./TxHead/HFMTID='D003']/TxBody/AVAIL_BAL | eai:Tx[./TxHead/HFMTID='D004']/TxBody/ACT_BAL | eai:Tx[./TxHead/HFMTID='D004']/TxBody/AVAIL_BAL | eai:Tx/TxBody/DEFERINT"),
             @DecimalConverter(converter = EAIDecimalConverter.class,scale = 3,pattern = "00000000000000000", fieldXPath = "eai:Tx[./TxHead/HFMTID='D002']/TxBody/AR_PNT_AMT | " 
                     + "eai:Tx[./TxHead/HFMTID='D002']/TxBody/AR_TOTAL_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/AR_PRN_AMT | " 
                     + "eai:Tx[./TxHead/HFMTID='D002']/TxBody/A_INS_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/A_INS_PRN | eai:Tx[./TxHead/HFMTID='D002']/TxBody/AR_DLY_AMT | "
                     + "eai:Tx[./TxHead/HFMTID='D004']/TxBody/AR_PRN_AMT | eai:Tx[./TxHead/HFMTID='D004']/TxBody/AR_INT_AMT | eai:Tx[./TxHead/HFMTID='D004']/TxBody/AR_PNT_AMT | " 
                     + "eai:Tx[./TxHead/HFMTID='D004']/TxBody/AR_TOTAL_AMT | eai:Tx[./TxHead/HFMTID='D004']/TxBody/A_INS_AMT | eai:Tx[./TxHead/HFMTID='D004']/TxBody/A_INS_PRN | "
                     + "eai:Tx[./TxHead/HFMTID='D004']/TxBody/AR_DLY_AMT "),
     }
)
//@formatter:on
public class EB613956RS extends EAIResponse<EB613956SvcRqType, EB613956SvcRsType> {

    /**
     * 建構子
     */
    public EB613956RS() {
        super("EB613956");
    }

}
