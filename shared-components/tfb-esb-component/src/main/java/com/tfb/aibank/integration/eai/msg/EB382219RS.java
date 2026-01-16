package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB382219SvcRqType;
import tw.com.ibm.mf.eb.EB382219SvcRsType;

//@formatter:off
/**
* @(#)EB382219RS.java
*
* <p>Description:就貸自動扣繳設定 下行</p>
*
* <p>Modify History:</p>
* v1.0, 2024/06/04, Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
//@formatter:on
public class EB382219RS extends EAIResponse<EB382219SvcRqType, EB382219SvcRsType> {
    /**
     * 建構子
     */
    public EB382219RS() {
        super("EB382219");
    }

}
