/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.NAWEBP04SvcRqType;

// @formatter:off
/**
 * @(#)NAWEBP04RQ.java
 * 
 * <p>一般型股票查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/StartDate | eai:Tx/TxBody/TxRepeat/EndDate")
        }
)
// @formatter:on
public class NAWEBP04RQ extends EAIRequest<NAWEBP04SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NAWEBP04RQ() {
        super("NAWEBP04");
    }

}
