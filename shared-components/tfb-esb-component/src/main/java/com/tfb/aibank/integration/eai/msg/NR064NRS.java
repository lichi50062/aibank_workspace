package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR064NSvcRqType;
import tw.com.ibm.mf.eb.NR064NSvcRsType;

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

@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/CustNum | eai:Tx/TxBody/CustMail"),
        }
)
public class NR064NRS extends EAIResponse<NR064NSvcRqType, NR064NSvcRsType> {
    /**
     * 建構子
     */
    public NR064NRS() {
        super("NR064N");
    }
}
