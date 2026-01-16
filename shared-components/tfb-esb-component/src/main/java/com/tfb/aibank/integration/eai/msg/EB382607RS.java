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

import tw.com.ibm.mf.eb.EB382607SvcRqType;
import tw.com.ibm.mf.eb.EB382607SvcRsType;

// @formatter:off
/**
 * @(#)EB382607RS.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=學貸(63)-01就貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_SL | eai:Tx/TxBody/CUST_NO | eai:Tx/TxBody/CUST_NAME")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_DATE | eai:Tx/TxBody/TxRepeat/PostDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/PRN_AMT" + " | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/INT_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/PNT_AMT" + " | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/DLY_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/TOT_AMT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/LOAN_BAL" ),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, pattern = "00000000000000000", isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D003']/TxBody/TxRepeat/PRN_AMT" + " | eai:Tx[./TxHead/HFMTID='D003']/TxBody/TxRepeat/INT_AMT | eai:Tx[./TxHead/HFMTID='D003']/TxBody/TxRepeat/PNT_AMT" + " | eai:Tx[./TxHead/HFMTID='D003']/TxBody/TxRepeat/DLY_AMT | eai:Tx[./TxHead/HFMTID='D003']/TxBody/TxRepeat/TOT_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, pattern = "00000000000000000", isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/PRN_AMT" + " | eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/INT_AMT | eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/PNT_AMT" + " | eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/DLY_AMT | eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/TOTAL_AMT")
        }
)
//@formatter:on
public class EB382607RS extends EAIResponse<EB382607SvcRqType, EB382607SvcRsType> {

    /**
     * 建構子
     */
    public EB382607RS() {
        super("EB382607");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "0188".equals(errId);
    }

}
