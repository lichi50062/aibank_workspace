package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.AJWEE070SvcRqType;
import tw.com.ibm.mf.eb.AJWEE070SvcRsType;

// @formatter:off
/**
 * @(#)AJWEE070RS.java
 * 
 * <p>Description:OBU債券預約單長效單委託交易查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                               " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
               },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "0cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TxDate | eai:Tx/TxBody/TxRepeat/EXPStartDt | eai:Tx/TxBody/TxRepeat/EXPEndDt |"
                                + " eai:Tx/TxBody/TxRepeat/RefValDate | eai:Tx/TxBody/TxRepeat/NetInterestDate | eai:Tx/TxBody/TxRepeat/EndDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/TxVal | eai:Tx/TxBody/TxRepeat/ProdMinBuyAmt | eai:Tx/TxBody/TxRepeat/TrustVal1"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TxFee1 | eai:Tx/TxBody/TxRepeat/TxFee2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/RefVal | eai:Tx/TxBody/TxRepeat/TrustAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 5, fieldXPath = "eai:Tx/TxBody/TxRepeat/TxFeeRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/BondRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/FinPrice | eai:Tx/TxBody/TxRepeat/TxPrice")
        }
)// @formatter:on
public class AJWEE070RS extends EAIResponse<AJWEE070SvcRqType, AJWEE070SvcRsType> {

    /**
     * 建構子
     */
    public AJWEE070RS() {
        super("AJWEE070");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
