package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB242651SvcRqType;

// @formatter:off
/**
 * @(#)EB242651RQ.java
 * 
 * <p>Description:EB242651 大額換匯查詢/預佔電文(台轉外、外轉台)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/Tx_Date")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3, pattern = "00000000000000", showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/Debit_Amt | eai:Tx/TxBody/Credit_Amt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "#.000", fieldXPath = "eai:Tx/TxBody/EquivalentTWD | eai:Tx/TxBody/EquivalentUSD")
        }
)
// @formatter:on
public class EB242651RQ extends EAIRequest<EB242651SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB242651RQ() {
        super("EB242651");
    }
}
