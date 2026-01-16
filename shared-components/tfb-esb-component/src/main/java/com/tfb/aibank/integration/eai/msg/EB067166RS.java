package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB067166SvcRqType;
import tw.com.ibm.mf.eb.EB067166SvcRsType;

// @formatter:off
/**
 * @(#)EB067166RS.java
 * 
 * <p>Description:EB067166 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/AUD_BAL_B | eai:Tx/TxBody/AUD_BAL_C | eai:Tx/TxBody/CNY_LIMIT | eai:Tx/TxBody/USD_LIMIT_PE" +
                        " | eai:Tx/TxBody/USD_LIMIT_CO | eai:Tx/TxBody/USD_LIMIT_UR")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, showPlusSign = true, isPostSign = true, scale=3, fieldXPath = "eai:Tx/TxBody/AUD_BAL_B | eai:Tx/TxBody/AUD_BAL_C | eai:Tx/TxBody/CNY_LIMIT | eai:Tx/TxBody/USD_LIMIT_PE | eai:Tx/TxBody/USD_LIMIT_CO | eai:Tx/TxBody/USD_LIMIT_UR")
    }
)
// @formatter:on
public class EB067166RS extends EAIResponse<EB067166SvcRqType, EB067166SvcRsType> {
    /**
     * 建構子
     */
    public EB067166RS() {
        super("EB067166");
    }

}
