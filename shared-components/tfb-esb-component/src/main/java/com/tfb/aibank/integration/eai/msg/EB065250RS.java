package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB065250SvcRqType;
import tw.com.ibm.mf.eb.EB065250SvcRsType;

// @formatter:off
/**
 * @(#)EB065250RS.java
 *
 * <p>Description:EB065250RS查詢大額換匯申報書下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/10/09,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/totalamt|eai:Tx/TxBody/totalamtUSD|eai:Tx/TxBody/totalCalAmt|eai:Tx/TxBody/TxRepeat/oriamount|eai:Tx/TxBody/TxRepeat/equamount|eai:Tx/TxBody/TxRepeat/equamountTWD")
        },  
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/txndate|eai:Tx/TxBody/TxRepeat/SettlementDate")
        }
)
// @formatter:on

public class EB065250RS extends EAIResponse<EB065250SvcRqType, EB065250SvcRsType> {

    /**
     * 建構子
     */
    public EB065250RS() {
        super("EB065250");
    }

}