package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.FPAPPLY003SvcRqType;
import tw.com.ibm.mf.eb.FPAPPLY003SvcRsType;

// @formatter:off
/**
 * @(#)FPAPPLY003RS.java
 * 
 * <p>Description: FPAPPLY003RS 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
                               " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
        },
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateTimeConverter.class, inputPattern = "yyyy-MM-dd'T'HH:mm:ssZZ", fieldXPath = "eai:Tx/TxBody/TxRepeat/REF_VAL_DATE")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "#", fieldXPath = "eai:Tx/TxBody/TxRepeat/PURCHASE_AMT | eai:Tx/TxBody/TxRepeat/ENTRUST_AMT | eai:Tx/TxBody/TxRepeat/TRUST_AMT | eai:Tx/TxBody/TxRepeat/FEE_RATE | eai:Tx/TxBody/TxRepeat/PAYABLE_FEE | eai:Tx/TxBody/TxRepeat/FEE | eai:Tx/TxBody/TxRepeat/TOT_AMT")
        }
)
//@formatter:on
public class FPAPPLY003RS extends EAIResponse<FPAPPLY003SvcRqType, FPAPPLY003SvcRsType> {

    /**
     * 建構子
     */
    public FPAPPLY003RS() {
        super("FPAPPLY003");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E005".equals(errId) || "EBB2".equals(errId);
    }
}
