package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020003SvcRqType;
import tw.com.ibm.mf.eb.EB12020003SvcRsType;

//@formatter:off
/**
* @(#)EB12020003RS.java
* 
* <p>Description:外幣定存續約變更下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/31, Leiley
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
public class EB12020003RS extends EAIResponse<EB12020003SvcRqType, EB12020003SvcRsType> {
    /**
     * 建構子
     */
    public EB12020003RS() {
        super("EB12020003");
    }
}
