package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB620966SvcRqType;
import tw.com.ibm.mf.eb.EB620966SvcRsType;

// @formatter:off
/**
 * @(#)EB242651RS.java
 *
 * <p>Description EB620966 大額換匯含查扣</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/10/24,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter: on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"
                        + " | eai:Tx/TxBody/TX_SRL | eai:Tx/TxBody/DATA_NO_OUT | eai:Tx/TxBody/DATA_NO_IN | eai:Tx/TxBody/KIND_RC | eai:Tx/TxBody/ERROR"
                        + " | eai:Tx/TxBody/DOCU_NO | eai:Tx/TxBody/USD_AMT | eai:Tx/TxBody/USD_RATE | eai:Tx/TxBody/RCV_IDNO | eai:Tx/TxBody/RCV_NAME"
                        + " | eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/EX_RATE | eai:Tx/TxBody/EX_AMT | eai:Tx/TxBody/O_CUR | eai:Tx/TxBody/O_ACC_BAL"
                        + " | eai:Tx/TxBody/O_AVL_BAL | eai:Tx/TxBody/FEE | eai:Tx/TxBody/TX_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=4, fieldXPath = "eai:Tx/TxBody/FEE"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/EX_AMT | eai:Tx/TxBody/O_ACC_BAL | eai:Tx/TxBody/O_AVL_BAL"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=6, isPostSign=true, fieldXPath = "eai:Tx/TxBody/EX_RATE")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/ACT_DATE"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE")
        }
)
public class EB620966RS extends EAIResponse<EB620966SvcRqType, EB620966SvcRsType> {

    /**
     * 建構子
     */
    public EB620966RS() {
        super("EB620966");
    }

}
