package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB620967SvcRqType;

// @formatter:off
/**
 * @(#)EB620967RQ.java
 * 
 * <p>Description:台轉外、外轉台、大額換匯即時上送電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/RMT_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,scale = -3, pattern = "00000000000000", showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TX_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class,scale = -6, pattern = "00000000000", showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/BS_RATE | eai:Tx/TxBody/BASE_RATE")
        }
)
// @formatter:on
public class EB620967RQ extends EAIRequest<EB620967SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB620967RQ() {
        super("EB620967");
    }
}
