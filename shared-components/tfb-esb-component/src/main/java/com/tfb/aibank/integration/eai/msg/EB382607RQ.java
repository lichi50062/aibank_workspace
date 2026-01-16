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

import tw.com.ibm.mf.eb.EB382607SvcRqType;

// @formatter:off
/**
 * @(#)EB382607RQ.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=學貸(63)-01就貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/STR_DATE | eai:Tx/TxBody/END_DATE")
        }
)
// @formatter:on
public class EB382607RQ extends EAIRequest<EB382607SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB382607RQ() {
        super("EB382607");
    }

    @Override
    protected boolean allowConversation() {
        return true;
    }
}
