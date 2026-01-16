/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.FEP512161SvcRqType;
import tw.com.ibm.mf.eb.FEP512161SvcRsType;

/**
 * 自動化手續費優惠查詢 下行電文
 *
 * @author Edward Tien
 */
// @formatter:off
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "0", fieldXPath = "eai:Tx/TxBody/CsCnt" +
                                                " | eai:Tx/TxBody/CsCntR | eai:Tx/TxBody/TrCnt" +
                                                " | eai:Tx/TxBody/TrCntR | eai:Tx/TxBody/TrCnt2" +
                                                " | eai:Tx/TxBody/TrCntR2 ")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/StaDateSA" +
                                                " | eai:Tx/TxBody/StaDateVB | eai:Tx/TxBody/EndDateSA" +
                                                " | eai:Tx/TxBody/EndDateVB | eai:Tx/TxBody/StaDateOT" +
                                                " | eai:Tx/TxBody/StaDateVP | eai:Tx/TxBody/EndDateOT" +
                                                " | eai:Tx/TxBody/EndDateVP | eai:Tx/TxBody/StaDateEB" +
                                                " | eai:Tx/TxBody/EndDateEB")
        }
)
//@formatter:on
public class FEP512161RS extends EAIResponse<FEP512161SvcRqType, FEP512161SvcRsType> {

    /**
     * 建構子
     */
    public FEP512161RS() {
        super("FEP512161");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || StringUtils.equalsAny(errId, "ERH2", "E003", "EBA9");
    }
}
