package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB142652SvcRqType;
import tw.com.ibm.mf.eb.EB142652SvcRsType;

// @formatter:off
/**
 * @(#)EB142652RS.java
 * 
 * <p>Description:自動轉期歷史查詢 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(
                    converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT | eai:Tx/TxBody/TxRepeat/* ")
        },
        decimalConverters = {
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000", scale = 8, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/INT_RATE "),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DPR_AMT")
        },
        datetimeConverters = {
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/BK_VALUE | eai:Tx/TxBody/TxRepeat/DUE_DTE"),
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "dd/MM/yyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DTE")
        }
)
// @formatter:on
public class EB142652RS extends EAIResponse<EB142652SvcRqType, EB142652SvcRsType> {

    /**
     * 建構子
     */
    public EB142652RS() {
        super("EB142652");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "EK63".equals(errId);
    }
}
