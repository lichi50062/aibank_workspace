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

import tw.com.ibm.mf.eb.EB362611SvcRqType;
import tw.com.ibm.mf.eb.EB362611SvcRsType;

// @formatter:off
/**
 * @(#)EB362611RS.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=房貸(61)-02公教/03國宅)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/CUST_NO | eai:Tx/TxBody/CUST_NAME | eai:Tx/TxBody/GRP_OT_ACNO | eai:Tx/TxBody/GRP_OT_NAME | eai:Tx/TxBody/RMK_ACNO_OT")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, pattern = "00000000000000000", showPlusSign = true,
                        fieldXPath = "eai:Tx/TxBody/TxRepeat/AMT | eai:Tx/TxBody/TxRepeat/B_LOAN_ABL | eai:Tx/TxBody/TxRepeat/PRN_AMT | eai:Tx/TxBody/TxRepeat/INT_AMT | eai:Tx/TxBody/TxRepeat/PNT_AMT | eai:Tx/TxBody/TxRepeat/DLY_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/RATE")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccc/MM/dd", fieldXPath = "eai:Tx/TxBody/TxRepeat/RVS_MTN_DATE | eai:Tx/TxBody/TxRepeat/RCV_DATE"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_DATE | eai:Tx/TxBody/TxRepeat/DTL_SDATE | eai:Tx/TxBody/TxRepeat/DTL_EDATE")
        }
)
//@formatter:on
public class EB362611RS extends EAIResponse<EB362611SvcRqType, EB362611SvcRsType>{
    
    /**
     * 建構子
     */
    public EB362611RS() {
        super("EB362611");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "0188".equals(errId);
    }

}
