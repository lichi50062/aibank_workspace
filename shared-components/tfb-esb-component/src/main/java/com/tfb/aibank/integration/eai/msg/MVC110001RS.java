package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.MVC110001SvcRqType;
import tw.com.ibm.mf.eb.MVC110001SvcRsType;

//@formatter:off
/**
 * @(#)MVC110001RS.java
 * 
 * <p>Description:MVC110001 EMAIL驗證平台電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Alex PY Li
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
public class MVC110001RS extends EAIResponse<MVC110001SvcRqType, MVC110001SvcRsType> {
    /**
     * 建構子
     */
    public MVC110001RS() {
        super("MVC110001");
    }

}
