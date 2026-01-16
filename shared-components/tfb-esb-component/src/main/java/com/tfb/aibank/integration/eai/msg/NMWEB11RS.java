package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB11SvcRqType;
import tw.com.ibm.mf.eb.NMWEB11SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB11RS.java
 * 
 * <p>Description:申請紀錄查詢電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/PayAmt | eai:Tx/TxBody/TxRepeat/PayAmt01 | eai:Tx/TxBody/TxRepeat/PayAmt02 | eai:Tx/TxBody/TxRepeat/PayAmt03 | eai:Tx/TxBody/TxRepeat/PayAmt04 | eai:Tx/TxBody/TxRepeat/PayAmt05"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/PayPercentage01 | eai:Tx/TxBody/TxRepeat/PayPercentage02 | eai:Tx/TxBody/TxRepeat/PayPercentage03 | eai:Tx/TxBody/TxRepeat/PayPercentage04 | eai:Tx/TxBody/TxRepeat/PayPercentage05")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Apply_Date | eai:Tx/TxBody/TxRepeat/AuthEndDate"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/Apply_Time")
        }
)
// @formatter:on
public class NMWEB11RS extends EAIResponse<NMWEB11SvcRqType, NMWEB11SvcRsType> {

    public NMWEB11RS() {
        super("NMWEB11");
    }

    @Override
    public boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
