package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.NR064NSvcRqType;

//@formatter:off
/**
 * @(#)NR064NRQ.java
 * 
 * <p>Description: NR064N 海外股票ETF到價通知設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
//@formatter:off
@Converter(
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/EnterSdate | eai:Tx/TxBody/EnterEdate")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "0000000000000", scale = -6, fieldXPath = "eai:Tx/TxBody/EnterAmt")
        })
//@formatter:on
public class NR064NRQ extends EAIRequest<NR064NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR064NRQ() {
        super("NR064N");
    }
}
