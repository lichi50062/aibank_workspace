package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.AJWEE060SvcRqType;

//@formatter:off
/**
* @(#)AJWEE060RQ.java
* 
* <p>AJWEE060RQ 查詢OBU海外債歷史交易清單</p>
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
public class AJWEE060RQ extends EAIRequest<AJWEE060SvcRqType> {

    /**
     * 建構子
     */
    public AJWEE060RQ() {
        super("AJWEE060");
    }
}
