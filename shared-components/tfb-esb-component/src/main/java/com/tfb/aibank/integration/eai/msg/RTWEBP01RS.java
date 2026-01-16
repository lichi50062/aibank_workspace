package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.RTWEBP01SvcRqType;
import tw.com.ibm.mf.eb.RTWEBP01SvcRsType;

//@formatter:off
/**
* @(#)RTWEBP01RS.java
* 
* <p>Description: 儲蓄信託明細 下行</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
    },
    decimalConverters = {
        @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = "eai:Tx/TxBody/TxRepeat/Detamt1 " +
            "| eai:Tx/TxBody/TxRepeat/Detamt2 " +
            "| eai:Tx/TxBody/TxRepeat/Fee " +
            "| eai:Tx/TxBody/TxRepeat/CashDiv " +
            "| eai:Tx/TxBody/TxRepeat/StockDiv " +
            "| eai:Tx/TxBody/TxRepeat/Interest " +
            "| eai:Tx/TxBody/TxRepeat/Sumamt1 " +
            "| eai:Tx/TxBody/TxRepeat/Sumamt2" +
            "| eai:Tx/TxBody/TxRepeat/NetAum"),
        @DecimalConverter(converter = EAIDecimalConverter.class, scale=4, fieldXPath = "eai:Tx/TxBody/TxRepeat/Unit | eai:Tx/TxBody/TxRepeat/Sumunit | eai:Tx/TxBody/TxRepeat/NavVul")
    },
    datetimeConverters = {
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMM", fieldXPath = "eai:Tx/TxBody/TxRepeat/DetYM | eai:Tx/TxBody/TxRepeat/SumYM"),
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/NavDay")
    }
)
//@formatter:on
public class RTWEBP01RS extends EAIResponse<RTWEBP01SvcRqType, RTWEBP01SvcRsType> {

    /**
     * 建構子
     */
    public RTWEBP01RS() {
        super("RTWEBP01");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
