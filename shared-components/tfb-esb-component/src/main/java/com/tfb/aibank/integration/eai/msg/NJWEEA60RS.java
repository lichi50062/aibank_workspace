package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEEA60SvcRqType;
import tw.com.ibm.mf.eb.NJWEEA60SvcRsType;

// @formatter:off
/**
 * @(#)NJWEEA60RS.java
 * 
 * <p>Description: NJWEEA60RS 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/18, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                               " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur "),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/FeeAmt | eai:Tx/TxBody/TxRepeat/PayableFeeAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/TxAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 5, fieldXPath = "eai:Tx/TxBody/TxRepeat/DefaultFeeRate | eai:Tx/TxBody/TxRepeat/BestFeeRate | eai:Tx/TxBody/TxRepeat/TxFeeRate1 | eai:Tx/TxBody/TxRepeat/TxFeeRate2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/PayableFeeRate")       
        }
)
//@formatter:on
public class NJWEEA60RS extends EAIResponse<NJWEEA60SvcRqType, NJWEEA60SvcRsType> {

    /**
     * 建構子
     */
    public NJWEEA60RS() {
        super("NJWEEA60");
    }

}
