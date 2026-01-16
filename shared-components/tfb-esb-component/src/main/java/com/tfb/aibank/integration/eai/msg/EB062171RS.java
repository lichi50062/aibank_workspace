package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB062171SvcRqType;
import tw.com.ibm.mf.eb.EB062171SvcRsType;

// @formatter:off
/**
 * @(#)EB062171RS.java
 *
 * <p>Description:EB062171RS(撥貸紀錄查詢) 下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/IDType | eai:Tx/TxBody/IDNo | eai:Tx/TxBody/DayNumber")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AmtNumber")
        }
)
public class EB062171RS extends EAIResponse<EB062171SvcRqType, EB062171SvcRsType> {

    /**
     * 建構子
     */
    public EB062171RS() {
        super("EB062171");
    }

}