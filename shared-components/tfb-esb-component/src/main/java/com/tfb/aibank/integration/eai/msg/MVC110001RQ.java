package com.tfb.aibank.integration.eai.msg;


import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.MVC110001SvcRqType;

//@formatter:off
/**
* @(#)MVC110001RQ.java
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
              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"),
              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
      }
)
//@formatter:on
public class MVC110001RQ extends EAIRequest<MVC110001SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
	public MVC110001RQ() {
		super("MVC110001");
	}

}
