package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.N8080NSvcRqType;

//@formatter:off
/**
* @(#)N8080NRQ.java
* 
* <p>Description: N8080N 股票委託明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/18, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/AcctId16")
        }
)
//@formatter:on
public class N8080NRQ extends EAIRequest<N8080NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8080NRQ() {
        super("N8080N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
