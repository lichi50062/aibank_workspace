package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.N8067NSvcRqType;

//@formatter:off
/**
* @(#)N8067NRQ.java
* 
* <p>Description: N8067N 股票定期定額訂約</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/18, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/TrustAcct")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern="00000000000", scale = -2, fieldXPath = "eai:Tx/TxBody/EntrustAmt")
        }        
)
//@formatter:on
public class N8067NRQ extends EAIRequest<N8067NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8067NRQ() {
        super("N8067N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
