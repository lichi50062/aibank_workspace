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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.FEP512166SvcRqType;
import tw.com.ibm.mf.eb.FEP512166SvcRsType;

/**
 * 客戶分群跨行手續費優惠查詢 下行電文
 * 
 * @author Edward Tien
 */
// @formatter:off
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "0", fieldXPath = "eai:Tx/TxBody/SpecCsCnt | eai:Tx/TxBody/RemainCsCnt | eai:Tx/TxBody/SpecTrCnt | eai:Tx/TxBody/RemainTrCnt | eai:Tx/TxBody/SpecTrCnt2 | eai:Tx/TxBody/RemainTrCnt2"+
                                " | eai:Tx/TxBody/SPEC_CSCNT" +
                                " | eai:Tx/TxBody/CSCNT_REMAIN | eai:Tx/TxBody/SPEC_TRCNT" +
                                " | eai:Tx/TxBody/TRCNT_REMAIN")
        }
)
//@formatter:on
public class FEP512166RS extends EAIResponse<FEP512166SvcRqType, FEP512166SvcRsType> {

    /**
     * 建構子
     */
    public FEP512166RS() {
        super("FEP512166");
    }

    @Override
    public boolean isNoData(String errId) {
        return super.isNoData(errId) || "ERH2".equals(errId);
    }
}
