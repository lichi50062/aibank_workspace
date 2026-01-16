package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.N8068NSvcRqType;

//@formatter:off
/**
* @(#)N8068NRQ.java
* 
* <p>Description: N8068N 股票定期定額贖回</p>
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
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TradeDate | eai:Tx/TxBody/EntrustDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern="00000000000000000", scale = -6, fieldXPath = "eai:Tx/TxBody/EntrustAmt")
        }        
)
//@formatter:on
public class N8068NRQ extends EAIRequest<N8068NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8068NRQ() {
        super("N8068N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
