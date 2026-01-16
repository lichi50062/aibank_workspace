package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.AJWEE060SvcRqType;
import tw.com.ibm.mf.eb.AJWEE060SvcRsType;

//@formatter:off
/**
* @(#)AJWEE060RS.java
*
* <p>AJWEE060RS 查詢OBU海外債歷史交易清單</p>
*
* <p>Modify History:</p>
* v1.0, 2024/05/11, Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/Amt")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Date")
        }
)
//@formatter:on
public class AJWEE060RS extends EAIResponse<AJWEE060SvcRqType, AJWEE060SvcRsType> {
    /**
     * 建構子
     */
    public AJWEE060RS() {
        super("AJWEE060");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

}
