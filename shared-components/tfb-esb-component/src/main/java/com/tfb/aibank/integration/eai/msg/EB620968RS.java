package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB620968SvcRqType;
import tw.com.ibm.mf.eb.EB620968SvcRsType;

// @formatter:off
/**
 * @(#)EB620968RS.java
 * 
 * <p>Description:匯款歷程查詢 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/08/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/UETR | eai:Tx/TxBody/TX_STATUS | eai:Tx/TxBody/TX_STATUS_RSN") 
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/FCA_OCCUR ") ,
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_AMT | eai:Tx/TxBody/TxRepeat/TX_AMT_IN "
                                 + "| eai:Tx/TxBody/TxRepeat/TX_AMT_REMAIN | eai:Tx/TxBody/TxRepeat/TX_AMT_PREVIOUS | eai:Tx/TxBody/TxRepeat/FCA_AMT1 | eai:Tx/TxBody/TxRepeat/FCA_AMT2 "
                                 + "| eai:Tx/TxBody/TxRepeat/FCA_AMT3 | eai:Tx/TxBody/TxRepeat/FCA_AMT4 | eai:Tx/TxBody/TxRepeat/FCA_AMT5 | eai:Tx/TxBody/TxRepeat/FCA_AMT6 "
                                 + "| eai:Tx/TxBody/TxRepeat/FCA_AMT7 | eai:Tx/TxBody/TxRepeat/FCA_AMT8 | eai:Tx/TxBody/TxRepeat/FCA_AMT9 | eai:Tx/TxBody/TxRepeat/FCA_AMT10 "
                                 + "| eai:Tx/TxBody/TxRepeat/FCA_AMT11 | eai:Tx/TxBody/TxRepeat/FCA_AMT12 | eai:Tx/TxBody/TxRepeat/FCA_AMT13 | eai:Tx/TxBody/TxRepeat/FCA_AMT14 | eai:Tx/TxBody/TxRepeat/FCA_AMT15")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateTimeConverter.class, inputPattern = "yyyyMMddHHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/CREATION_DATE_TIME | eai:Tx/TxBody/TxRepeat/SENDER_DATE_TIME")
        }
)
// @formatter:on
public class EB620968RS extends EAIResponse<EB620968SvcRqType, EB620968SvcRsType> {

    /**
     * 建構子
     */
    public EB620968RS() {
        super("EB620968");
    }

}
