package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEE061SvcRqType;
import tw.com.ibm.mf.eb.NJWEE061SvcRsType;

//@formatter:off
/**
* @(#)NJWEE061RS.java
*
* <p>NJWEE061RS 查詢DBU海外債歷史交易明細</p>
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
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TxDate" +
                        " | eai:Tx/TxBody/TxRepeat/AllocateDate | eai:Tx/TxBody/TxRepeat/InDate | eai:Tx/TxBody/TxRepeat/RefDt | eai:Tx/TxBody/TxRepeat/BaseDate" +
                        " | eai:Tx/TxBody/TxRepeat/EndDate | eai:Tx/TxBody/TxRepeat/BuyDate")
        }
)
//@formatter:on
public class NJWEE061RS extends EAIResponse<NJWEE061SvcRqType, NJWEE061SvcRsType> {
    /**
     * 建構子
     */
    public NJWEE061RS() {
        super("NJWEE061");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

}
