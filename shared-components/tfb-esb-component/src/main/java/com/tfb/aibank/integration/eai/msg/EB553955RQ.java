package com.tfb.aibank.integration.eai.msg;

import java.util.Date;

import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB553955SvcRqType;

//@formatter:off
/**
 * @(#)EB553955RQ.java
 *
 * <p>Description: 台轉外、外轉台、外轉外即時上行電文(EB553955)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/PREREQ_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = -3, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx/TxBody/TX_AMT | eai:Tx/TxBody/IN_OUT_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000", scale = -6, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx/TxBody/EX_RATE | eai:Tx/TxBody/CS_RATE")
        }
) 
// @formatter:on
public class EB553955RQ extends EAIRequest<EB553955SvcRqType> {

    private static final long serialVersionUID = 2732390152721207925L;

    /**
     * 建構子
     */
    public EB553955RQ() {
        super("EB553955");
    }

    @Override
    protected String getTXMSRN() {
        Date date = new Date();
        date = DateUtils.addMinutes(date, 3);
        return DateUtils.getSimpleTimeStr(date);
    }
}
