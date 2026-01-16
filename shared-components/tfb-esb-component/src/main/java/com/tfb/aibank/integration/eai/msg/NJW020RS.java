package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJW020SvcRqType;
import tw.com.ibm.mf.eb.NJW020SvcRsType;

// @formatter:off
/**
 * @(#)NJW020RS.java
 * 
 * <p>Description:債券營業時間交易</p>
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
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/BackUntNum" +
                        " | eai:Tx/TxBody/TxRepeat/LeftUntNum | eai:Tx/TxBody/TxRepeat/BackAmt | eai:Tx/TxBody/TxRepeat/BalanceAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/BalanceAmt | eai:Tx/TxBody/TxRepeat/TxAmt1 | eai:Tx/TxBody/TxRepeat/TrustAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 5, fieldXPath = "eai:Tx/TxBody/TxRepeat/FeeRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/Fee | eai:Tx/TxBody/TxRepeat/TxFee1")
        }
)
// @formatter:on
public class NJW020RS extends EAIResponse<NJW020SvcRqType, NJW020SvcRsType> {

    public NJW020RS() {
        super("NJW020");
    }

}
