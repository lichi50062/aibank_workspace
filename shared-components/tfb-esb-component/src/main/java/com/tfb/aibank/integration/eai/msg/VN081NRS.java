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

import tw.com.ibm.mf.eb.VN081NSvcRqType;
import tw.com.ibm.mf.eb.VN081NSvcRsType;

// @formatter:off
/**
 * @(#)VN081NRS.java
 * 
 * <p>VN081N 下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/02, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
            @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TrscDate | eai:Tx/TxBody/TxRepeat/MarkedDate | " +
                    "eai:Tx/TxBody/TxRepeat/ShortTerm_Date | eai:Tx/TxBody/TxRepeat/ApplyDate | eai:Tx/TxBody/TxRepeat/ReferNetDt")
        },
        decimalConverters = {
            @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur"
            ),
            @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath =
                                        "eai:Tx/TxBody/TxRepeat/TrustAmt"
                                        + " | eai:Tx/TxBody/TxRepeat/Fee"
                                        + " | eai:Tx/TxBody/TxRepeat/Stoploss"
                                        + " | eai:Tx/TxBody/TxRepeat/Satisfied"
                                        + " | eai:Tx/TxBody/TxRepeat/TrustAmt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/TrustAmt_H "
                                        + " | eai:Tx/TxBody/TxRepeat/Fee_M"
                                        + " | eai:Tx/TxBody/TxRepeat/Fee_H"
                                        + " | eai:Tx/TxBody/TxRepeat/EstShortTerm_Fee"
                                        + " | eai:Tx/TxBody/TxRepeat/BackFee"
                                        + " | eai:Tx/TxBody/TxRepeat/EstimateMagtFee"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeFee"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeFee_M"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeFee_H"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeAmt"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeAmt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeAmt_H"
                                        + " | eai:Tx/TxBody/TxRepeat/BeforeExchangeAmt"
                                        + " | eai:Tx/TxBody/TxRepeat/BeforeExchangeAmt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/BeforeExchangeAmt_H"
                                        + " | eai:Tx/TxBody/TxRepeat/BeforeExchangeFee"
                                        + " | eai:Tx/TxBody/TxRepeat/BeforeExchangeAmt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/BeforeExchangeAmt_H"
                                        + " | eai:Tx/TxBody/TxRepeat/Amt"
                                        + " | eai:Tx/TxBody/TxRepeat/Amt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/Amt_H"
                                        + " | eai:Tx/TxBody/TxRepeat/ExchangeAmt"
                                        + " | eai:Tx/TxBody/TxRepeat/ExchangeAmt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/ExchangeAmt_H"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeAmt_M"
                                        + " | eai:Tx/TxBody/TxRepeat/AfterExchangeAmt_H"
            ) ,
            @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, fieldXPath =
                                "eai:Tx/TxBody/TxRepeat/FeeRate"
                                + " | eai:Tx/TxBody/TxRepeat/FeeRate_M"
                                + " | eai:Tx/TxBody/TxRepeat/FeeRate_H"
                                + " | eai:Tx/TxBody/TxRepeat/ShortTerm_Rate"
                                + " | eai:Tx/TxBody/TxRepeat/ReFeeRate"
                                + " | eai:Tx/TxBody/TxRepeat/AdvFeeRate"
                                + " | eai:Tx/TxBody/TxRepeat/DeferreFee"
           ) ,
           @DecimalConverter(converter = EAIDecimalConverter.class, scale=4, fieldXPath =
                               "eai:Tx/TxBody/TxRepeat/TransferUntNum"
                               + " | eai:Tx/TxBody/TxRepeat/LeftUntNum"
                               + " | eai:Tx/TxBody/TxRepeat/OriginalUntNum"
                               + " | eai:Tx/TxBody/TxRepeat/ShortTerm_Unit"
                               + " | eai:Tx/TxBody/TxRepeat/RefNetValue"
                               + " | eai:Tx/TxBody/TxRepeat/RecvUntNum"
          )
        }
    )
//@formatter:on
public class VN081NRS extends EAIResponse<VN081NSvcRqType, VN081NSvcRsType> {
    /**
     * 建構子
     */
    public VN081NRS() {
        super("VN081N");
    }

    /**
     * 檢查是否為無資料
     */
    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }
}
