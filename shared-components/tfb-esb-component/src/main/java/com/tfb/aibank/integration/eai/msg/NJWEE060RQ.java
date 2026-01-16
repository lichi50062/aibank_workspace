package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.NJWEE060SvcRqType;

//@formatter:off
/**
* @(#)NJWEE060RQ.java
* 
* <p>NJWEE060RQ 查詢DBU海外債歷史交易清單</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/11, Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/StartDt | eai:Tx/TxBody/EndDt")
        }
)
//@formatter:on
public class NJWEE060RQ extends EAIRequest<NJWEE060SvcRqType> {

    /**
     * 建構子
     */
    public NJWEE060RQ() {
        super("NJWEE060");
    }
}
