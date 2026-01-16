package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.FPAPPLY009SvcRqType;
import tw.com.ibm.mf.eb.FPAPPLY009SvcRsType;

// @formatter:off
/**
 * @(#)FPAPPLY009RS.java
 * 
 * <p>Description: FPAPPLY09RS 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/12, Leiley
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
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/PURCHASE_AMT | eai:Tx/TxBody/TxRepeat/ENTRUST_AMT | eai:Tx/TxBody/TxRepeat/TRUST_AMT | eai:Tx/TxBody/TxRepeat/FEE_RATE | eai:Tx/TxBody/TxRepeat/PAYABLE_FEE | eai:Tx/TxBody/TxRepeat/FEE | eai:Tx/TxBody/TxRepeat/TOT_AMT | eai:Tx/TxBody/TxRepeat/GRNT_RATE")
        }
)
//@formatter:on
public class FPAPPLY009RS extends EAIResponse<FPAPPLY009SvcRqType, FPAPPLY009SvcRsType> {

    /**
     * 建構子
     */
    public FPAPPLY009RS() {
        super("FPAPPLY009");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E005".equals(errId) || "EBB2".equals(errId);
    }
}
