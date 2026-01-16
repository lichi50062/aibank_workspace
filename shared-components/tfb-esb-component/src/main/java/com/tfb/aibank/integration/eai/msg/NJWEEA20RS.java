package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEEA20SvcRqType;
import tw.com.ibm.mf.eb.NJWEEA20SvcRsType;

// @formatter:off
/**
 * @(#)NJWEEA60RS.java
 * 
 * <p>Description: NJWEEA60RS 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/18, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                               " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },   
        datetimeConverters = {
                // 0cccMMdd => 比照現行
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "0cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/StrDate | eai:Tx/TxBody/TxRepeat/EndDate"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur" ),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/Discount1 | eai:Tx/TxBody/TxRepeat/Discount2")
        }
)
public class NJWEEA20RS extends EAIResponse<NJWEEA20SvcRqType, NJWEEA20SvcRsType> {

    /**
     * 建構子.
     */
    public NJWEEA20RS() {
        super("NJWEEA20");
    }
}
