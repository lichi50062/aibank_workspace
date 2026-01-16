package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.RTWEBP02SvcRqType;
import tw.com.ibm.mf.eb.RTWEBP02SvcRsType;

//@formatter:off
/**
* @(#)RTWEBP02RS.java
* 
* <p>Description: RTWEBP02 員工持股信託公司查詢 下行</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
    }
)
//@formatter:on
public class RTWEBP02RS extends EAIResponse<RTWEBP02SvcRqType, RTWEBP02SvcRsType> {

    /**
     * 建構子
     */
    public RTWEBP02RS() {
        super("RTWEBP02");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
