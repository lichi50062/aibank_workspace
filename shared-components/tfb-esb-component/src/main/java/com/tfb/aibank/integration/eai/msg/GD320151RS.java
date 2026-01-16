package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.GD320151SvcRqType;
import tw.com.ibm.mf.eb.GD320151SvcRsType;

// @formatter:off
/**
 * @(#)GD320151RS.java
 * 
 * <p>Description:GD320151 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(
                        converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        },
        decimalConverters = {
                @DecimalConverter(
                        converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/SELL | eai:Tx/TxBody/TxRepeat/BUY"),
                @DecimalConverter(
                        converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/SELL_C | eai:Tx/TxBody/TxRepeat/BUY_C | " +
                                "eai:Tx/TxBody/TxRepeat/DIFF_C | " +
                                "eai:Tx/TxBody/TxRepeat/SELL_C_RATE | eai:Tx/TxBody/TxRepeat/BUY_C_RATE | " +
                                "eai:Tx/TxBody/TxRepeat/DIFF_RATE")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/EFF_DATE"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/EFF_TIME")
        }
)
// @formatter:on
public class GD320151RS extends EAIResponse<GD320151SvcRqType, GD320151SvcRsType> {

    /**
     * 建構子
     */
    public GD320151RS() {
        super("GD320151");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "G003".equals(errId);
    }
}
