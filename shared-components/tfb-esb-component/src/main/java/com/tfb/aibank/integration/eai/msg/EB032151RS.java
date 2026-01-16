package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032151SvcRqType;
import tw.com.ibm.mf.eb.EB032151SvcRsType;

//@formatter:off
/**
* @(#)EB032151RQ.java
* 
* <p>Description: EB032151客戶基本資料維護(非臨櫃)下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/

@Converter(
        customConverters = {
             @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
    }
)
//@formatter:on
public class EB032151RS extends EAIResponse<EB032151SvcRqType, EB032151SvcRsType> {

    /**
     * 建構子
     */
    public EB032151RS() {
        super("EB032151");
    }

}
