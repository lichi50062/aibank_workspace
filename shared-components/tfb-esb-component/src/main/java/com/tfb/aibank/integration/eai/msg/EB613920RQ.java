package com.tfb.aibank.integration.eai.msg;

import java.util.Date;

import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB613920SvcRqType;

//@formatter:off
/**
 * @(#)EB613920RQ.java
 *
 * <p>Description:台轉外、外轉台、外轉外預約轉帳上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
        @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/PAY_DATE | eai:Tx/TxBody/ACT_DATE")
    },
        decimalConverters = {
        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/RCV_AMT_NT", scale = -2, pattern="000000000000000")
    }
) 
// @formatter:on
public class EB613920RQ extends EAIRequest<EB613920SvcRqType> {

    private static final long serialVersionUID = -6115875792912198928L;

    /**
     * 建構子
     */
    public EB613920RQ() {
        super("EB613920");
    }

    @Override
    protected String getTXMSRN() {
        Date date = new Date();
        date = DateUtils.addMinutes(date, 3);
        return DateUtils.getSimpleTimeStr(date);
    }
}
