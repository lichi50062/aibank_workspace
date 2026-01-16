package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB052664SvcRqType;
import tw.com.ibm.mf.eb.EB052664SvcRsType;

//@formatter:off
/**
* @(#)EB052664RS.java
*
* <p>Description:房貸變更扣繳帳號註記查詢 下行</p>
*
* <p>Modify History:</p>
* v1.0, 2024/06/04, Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_LN")
        }
)
//@formatter:on
public class EB052664RS extends EAIResponse<EB052664SvcRqType, EB052664SvcRsType> {
    /**
     * 建構子
     */
    public EB052664RS() {
        super("EB052664");
    }

}
