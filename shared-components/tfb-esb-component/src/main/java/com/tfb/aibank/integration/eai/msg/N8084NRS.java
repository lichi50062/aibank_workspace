package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.N8084NSvcRqType;
import tw.com.ibm.mf.eb.N8084NSvcRsType;

//@formatter:off
/**
 * @(#)N8084NRS.java
 * 
 * <p>Description: N8084N 信託海外股票－台幣現值總計</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/02, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                 		" | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/AR101")
        }
)
//@formatter:on
public class N8084NRS extends EAIResponse<N8084NSvcRqType, N8084NSvcRsType> {
    /**
     * 建構子
     */
    public N8084NRS() {
        super("N8084N");
    }

}
