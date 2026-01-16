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

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB601191SvcRqType;

// @formatter:off
/**
 * @(#)EB601191RQ.java
 * 
 * <p>Description:悠遊卡綁定與查詢 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/03, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/CUST_ID | eai:Tx/TxBody/ACNO_SA2 | eai:Tx/TxBody/ACNO_SA3"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 19, fieldXPath = "eai:Tx/TxBody/CARD_NAME1 | eai:Tx/TxBody/CARD_NAME2 | eai:Tx/TxBody/CARD_NAME3"),
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14,padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO |eai:Tx/TxBody/ACNO_SA1")
        }
)
// @formatter:on
public class EB601191RQ extends EAIRequest<EB601191SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB601191RQ() {
        super("EB601191");
    }

    @Override
    protected String getTXMSRN() {
        Date date = new Date();
        date = DateUtils.addMinutes(date, 3);
        return new SimpleDateFormat("HHmmss00").format(date);
    }
}
