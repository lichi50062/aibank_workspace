/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;

import tw.com.ibm.mf.eb.CEW4661RSvcRqType;

// @formatter:off
/**
 * @(#)CEW4661RRQ.java
 * 
 * <p>Description:[好市多會員自動續約通]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/Renewdate"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, outputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/Renewtime")
        }
)
// @formatter:on
public class CEW4661RRQ extends EAIRequest<CEW4661RSvcRqType> {

    private static final long serialVersionUID = 3767297435413748774L;

    /**
     * 建構子
     */
    public CEW4661RRQ() {
        super("CEW4661R");
    }
}
