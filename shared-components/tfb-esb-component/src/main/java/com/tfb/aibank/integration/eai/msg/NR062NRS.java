package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR062NSvcRqType;
import tw.com.ibm.mf.eb.NR062NSvcRsType;

// @formatter:off
/**
 * @(#)NR062NRS.java
 * 
 * <p>Description:NR062N 海外股票ETF停損停利交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX"),
        }
)
// @formatter:on
public class NR062NRS extends EAIResponse<NR062NSvcRqType, NR062NSvcRsType> {
    /**
     * 建構子
     */
    public NR062NRS() {
        super("NR062N");
    }
}
