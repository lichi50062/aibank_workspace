package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.NR067NSvcRqType;

//@formatter:off
/**
* @(#)NR067NRQ.java
* 
* <p>Description: NR067N 海外股票、ETF總覽明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/AcctId16")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class,outputPattern="yyyyMMdd", fieldXPath="eai:Tx/TxBody/TradeDate | eai:Tx/TxBody/EntrustDate | eai:Tx/TxBody/TradeDateEnd ")
        })
//@formatter:on
public class NR067NRQ extends EAIRequest<NR067NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR067NRQ() {
        super("NR067N");
    }
}
