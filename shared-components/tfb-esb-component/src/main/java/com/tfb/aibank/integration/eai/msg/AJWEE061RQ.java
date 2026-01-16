package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.AJWEE061SvcRqType;

//@formatter:off
/**
* @(#)AJWEE061RQ.java
* 
* <p>AJWEE061RQ 查詢OBU海外債歷史交易明細</p>
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
public class AJWEE061RQ extends EAIRequest<AJWEE061SvcRqType> {

    /**
     * 建構子
     */
    public AJWEE061RQ() {
        super("AJWEE061");
    }
}
