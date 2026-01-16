package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.AJWEE040SvcRqType;

// @formatter:off
/**
 * @(#)AJWEE040RQ.java
 * 
 * <p>Description:OBU債券委託交易查詢-已取消</p>
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
public class AJWEE040RQ extends EAIRequest<AJWEE040SvcRqType> {

    /**
     * 建構子.
     */
    public AJWEE040RQ() {
        super("AJWEE040");
    }

}
