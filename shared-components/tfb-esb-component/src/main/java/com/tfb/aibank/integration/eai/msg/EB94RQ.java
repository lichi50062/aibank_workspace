package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;

import tw.com.ibm.mf.eb.EB94SvcRqType;

// @formatter:off
/**
 * @(#)EB94RQ.java
 * 
 * <p>Description:EB94 EB網路銀行外匯扣帳電文 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/07, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/PAY_IDNO"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 1, fieldXPath = "eai:Tx/TxBody/PAPER1 | eai:Tx/TxBody/PAPER2")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern="yyyyMMdd", fieldXPath="eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/BDATE | eai:Tx/TxBody/VALUE_DAY | eai:Tx/TxBody/TxRepeat/PREREQ_DATE"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, outputPattern="HHmmss", fieldXPath="eai:Tx/TxBody/TIME")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -6, fieldXPath="eai:Tx/TxBody/RATE"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3, pattern = "00000000000000000", showPlusSign = true, isPostSign = true, fieldXPath="eai:Tx/TxBody/TX_AMT | eai:Tx/TxBody/ORFEE_DBU | eai:Tx/TxBody/ORFEE_OBU | eai:Tx/TxBody/OTHER_FEE")
        }
)
// @formatter:on
public class EB94RQ extends EAIRequest<EB94SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB94RQ() {
        super("EB94");
    }
}
