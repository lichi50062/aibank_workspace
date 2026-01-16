package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.NR066NSvcRqType;

// @formatter:off
/**
 * @(#)NR066NRQ.java
 * 
 * <p>Description:NR066N 海外股票特別股說明書交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/22, Charlie Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/Signdate")
        }
)
        
// @formatter:on
public class NR066NRQ extends EAIRequest<NR066NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR066NRQ() {
        super("NR066N");
    }
}
