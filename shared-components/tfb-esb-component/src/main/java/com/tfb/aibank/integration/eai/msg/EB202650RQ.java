/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import java.util.Date;

import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.EB202650SvcRqType;

//@formatter:off
/**
* @(#)EB202650RQ.java
* 
* <p>Description:台幣活存交易明細上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/STA_DATE | eai:Tx/TxBody/END_DATE")
})
//@formatter:on
public class EB202650RQ extends EAIRequest<EB202650SvcRqType> {

    private static final long serialVersionUID = -3121922436028366994L;

    /**
     * 建構子
     */
    public EB202650RQ() {
        super("EB202650");
    }

    @Override
    protected String getTXMSRN() {
        Date date = new Date();
        date = DateUtils.addMinutes(date, 3);

        return DateUtils.getTxmsrnDateStr(date);
    }

}
