package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB620966SvcRqType;

// @formatter:off
/**
 * @(#)EB242651RQ.java
 *
 * <p>Description EB620966RQ 大額換匯含查扣</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/10/24,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter: on
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/RMT_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3, pattern = "00000000000000", showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TX_AMT | eai:Tx/TxBody/DAY_LIMIT_01")
        }
)
public class EB620966RQ extends EAIRequest<EB620966SvcRqType> {

    /**
     * 建構子
     */
    public EB620966RQ() {
        super("EB620966");
    }
}
