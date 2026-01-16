package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.EB6705021SvcRqType;

//@formatter:off
/**
* @(#)EB6705021RQ.java
* 
* <p>Description:財管會員等級</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/07, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
      customConverters = {
              @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO_SA" )
      }
)
//@formatter:on
public class EB6705021RQ extends EAIRequest<EB6705021SvcRqType> {
    private static final long serialVersionUID = 3002651133177112438L;

    /**
     * 建構子
     */
    public EB6705021RQ() {
        super("EB6705021");
    }
}