package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.NJWEE040SvcRqType;

// @formatter:off
/**
 * @(#)NJWEE040RQ.java
 * 
 * <p>Description:債券委託交易查詢-已取消</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
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
public class NJWEE040RQ extends EAIRequest<NJWEE040SvcRqType> {

    /**
     * 建構子.
     */
    public NJWEE040RQ() {
        super("NJWEE040");
    }

}
