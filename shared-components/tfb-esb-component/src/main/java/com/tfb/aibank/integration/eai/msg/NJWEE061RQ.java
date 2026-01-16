package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.NJWEE061SvcRqType;

//@formatter:off
/**
* @(#)NJWEE061RQ.java
* 
* <p>NJWEE061RQ 查詢DBU海外債歷史交易明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/11, Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/Date")
        }
)
//@formatter:on
public class NJWEE061RQ extends EAIRequest<NJWEE061SvcRqType> {

    /**
     * 建構子
     */
    public NJWEE061RQ() {
        super("NJWEE061");
    }
}
