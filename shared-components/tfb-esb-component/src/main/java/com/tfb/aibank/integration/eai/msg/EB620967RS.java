package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB620967SvcRqType;
import tw.com.ibm.mf.eb.EB620967SvcRsType;

// @formatter:off
/**
 * @(#)EB620967RS.java
 * 
 * <p>Description:台轉外、外轉台、大額換匯即時下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"
                        + " | eai:Tx/TxBody/TX_SRL | eai:Tx/TxBody/DATA_NO_OUT | eai:Tx/TxBody/DATA_NO_IN | eai:Tx/TxBody/KIND_RC | eai:Tx/TxBody/ERROR"
                        + " | eai:Tx/TxBody/DOCU_NO | eai:Tx/TxBody/USD_AMT | eai:Tx/TxBody/USD_RATE | eai:Tx/TxBody/RCV_IDNO | eai:Tx/TxBody/RCV_NAME"
                        + " | eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAIL_BAL | eai:Tx/TxBody/OD_AMT | eai:Tx/TxBody/REMARK | eai:Tx/TxBody/ACNO_FD_1"
                        + " | eai:Tx/TxBody/SLIP_NO_1 | eai:Tx/TxBody/O_CUR_1 | eai:Tx/TxBody/INT_RATE_1 | eai:Tx/TxBody/CODE_NAME_1 | eai:Tx/TxBody/O_AMT_1"
                        + " | eai:Tx/TxBody/I_AMT_1 | eai:Tx/TxBody/EX_RATE_1 ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,scale = 8, isPostSign = true, fieldXPath = "eai:Tx/TxBody/INT_RATE_1"),
                @DecimalConverter(converter = EAIDecimalConverter.class,scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAIL_BAL | eai:Tx/TxBody/OD_AMT | eai:Tx/TxBody/O_AMT_1 | eai:Tx/TxBody/I_AMT_1 | eai:Tx/TxBody/EX_RATE_1")
        }
)
// @formatter:on
public class EB620967RS extends EAIResponse<EB620967SvcRqType, EB620967SvcRsType> {

    /**
     * 建構子
     */
    public EB620967RS() {
        super("EB620967");
    }
}
