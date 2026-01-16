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

import tw.com.ibm.mf.eb.VN081NSvcRqType;

// @formatter:off
/**
 * @(#)VN081NRQ.java
 * 
 * <p>VN081N 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/02, Yoyo Lin	
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
public class VN081NRQ extends EAIRequest<VN081NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public VN081NRQ() {
        super("VN081N");
    }

}
