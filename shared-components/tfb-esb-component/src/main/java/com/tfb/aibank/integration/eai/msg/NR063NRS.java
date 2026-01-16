package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR063NSvcRqType;
import tw.com.ibm.mf.eb.NR063NSvcRsType;

//@formatter:off
/**
 * @(#)NR063NRQ.java
 * 
 * <p>Description: NR063N 海外股票、ETF到價通知名單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/CustNum | eai:Tx/TxBody/CustMail"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/CurAmt | eai:Tx/TxBody/TxRepeat/EnterAmt")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Date08 | eai:Tx/TxBody/TxRepeat/EnterSdate | eai:Tx/TxBody/TxRepeat/EnterEdate")
        })
//@formatter:on
public class NR063NRS extends EAIResponse<NR063NSvcRqType, NR063NSvcRsType> {
    /**
     * 建構子
     */
    public NR063NRS() {
        super("NR063N");
    }
}
