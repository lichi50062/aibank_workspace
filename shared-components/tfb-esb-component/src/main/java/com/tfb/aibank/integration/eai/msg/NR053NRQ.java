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

import tw.com.ibm.mf.eb.NR053NSvcRqType;

// @formatter:off
/**
 * @(#)NR053NRQ.java
 * 
 * <p>Description:海外股票ETF新增股務回覆結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/18, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/DataDay | eai:Tx/TxBody/NoteDay")
        })
// @formatter:on
public class NR053NRQ extends EAIRequest<NR053NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR053NRQ() {
        super("NR053N");
        this.getHeader().setHDRVQ1("NRWEBHQ");
    }
}
