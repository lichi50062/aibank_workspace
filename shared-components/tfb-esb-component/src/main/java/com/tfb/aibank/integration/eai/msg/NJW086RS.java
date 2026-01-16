package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJW086SvcRqType;
import tw.com.ibm.mf.eb.NJW086SvcRsType;


//@formatter:off
/**
* @(#)NJW086RS.java
* 
* <p>Description: 債券行銀W-8BEN交易</p>
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
public class NJW086RS extends EAIResponse<NJW086SvcRqType, NJW086SvcRsType> {

    public NJW086RS() {
        super("NJW086");
    }
}
