package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE088SvcRqType;
import tw.com.ibm.mf.eb.NFEE088SvcRsType;

//@formatter:off
/**
* @(#)NFEE088RS.java
* 
* <p>Description: NFEE088 基金網行銀在途交易取消紀錄查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/13, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/CancelDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/Unit"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TrustAmt | eai:Tx/TxBody/TxRepeat/FeeAmt")
        }
)
//@formatter:on
public class NFEE088RS extends EAIResponse<NFEE088SvcRqType, NFEE088SvcRsType> {

    /**
     * 建構子
     */
    public NFEE088RS() {
        super("NFEE088");
    }

    @Override
    public boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
