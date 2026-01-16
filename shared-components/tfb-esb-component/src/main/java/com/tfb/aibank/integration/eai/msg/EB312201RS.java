package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB312201SvcRqType;
import tw.com.ibm.mf.eb.EB312201SvcRsType;

//@formatter:off
/**
* @(#)EB312201RS.java
*
* <p>Description:房貸、信貸、留貸變更扣繳帳號(授信帳號基本資料維護) 下行</p>
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
public class EB312201RS extends EAIResponse<EB312201SvcRqType, EB312201SvcRsType> {
    /**
     * 建構子
     */
    public EB312201RS() {
        super("EB312201");
    }

}
