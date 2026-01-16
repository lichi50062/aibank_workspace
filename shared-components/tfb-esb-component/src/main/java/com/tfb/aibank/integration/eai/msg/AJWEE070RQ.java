package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.AJWEE070SvcRqType;

// @formatter:off
/**
 * @(#)AJWEE070RQ.java
 * 
 * <p>Description:OBU債券預約單長效單委託交易查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/29, Rong Gang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/StartDt | eai:Tx/TxBody/EndDt")
        }
)
// @formatter:on
public class AJWEE070RQ extends EAIRequest<AJWEE070SvcRqType> {

    /**
     * 建構子
     */
    public AJWEE070RQ() {
        super("AJWEE070");
    }

}
