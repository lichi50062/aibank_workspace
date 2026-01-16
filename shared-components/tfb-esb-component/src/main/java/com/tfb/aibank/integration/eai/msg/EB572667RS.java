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

import tw.com.ibm.mf.eb.EB572667SvcRqType;
import tw.com.ibm.mf.eb.EB572667SvcRsType;

// @formatter:off
/**
 * @(#)EB572667RS.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=存單質借)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }, 
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/DTL_AMT | eai:Tx/TxBody/TxRepeat/LOAN_BAL" + " | eai:Tx/TxBody/TxRepeat/PRN_AMT | eai:Tx/TxBody/TxRepeat/INT_AMT | eai:Tx/TxBody/TxRepeat/PNT_AMT | eai:Tx/TxBody/TxRepeat/DLY_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D002']/TxBody/TxRepeat/amount"),
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_DATE | eai:Tx/TxBody/TxRepeat/DTL_SDATE" + " | eai:Tx/TxBody/TxRepeat/DTL_EDATE | eai:Tx/TxBody/TxRepeat/PostDate")
        })
//@formatter:on
public class EB572667RS extends EAIResponse<EB572667SvcRqType, EB572667SvcRsType> {
    /**
     * 建構子
     */
    public EB572667RS() {
        super("EB572667");
    }

    // #86135 user說明錯誤碼3486為正常
    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "0188".equals(errId);
    }

}
