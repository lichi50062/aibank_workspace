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

import tw.com.ibm.mf.eb.EB572667SvcRqType;

// @formatter:off
/**
 * @(#)EB572667RQ.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=存單質借)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy" , fieldXPath = "eai:Tx/TxBody/IRMT_STR_DATE | eai:Tx/TxBody/IRMT_END_DATE")
        }
)
// @formatter:on
public class EB572667RQ extends EAIRequest<EB572667SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB572667RQ() {
        super("EB572667");
    }
}
