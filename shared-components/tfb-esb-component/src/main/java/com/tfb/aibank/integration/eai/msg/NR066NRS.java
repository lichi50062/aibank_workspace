package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR066NSvcRqType;
import tw.com.ibm.mf.eb.NR066NSvcRsType;

// @formatter:off
/**
 * @(#)NR066NRS.java
 * 
 * <p>Description:NR066N 海外股票特別股說明書交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Charlie Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX")
        }
)
// @formatter:on
public class NR066NRS extends EAIResponse<NR066NSvcRqType, NR066NSvcRsType> {

    public NR066NRS() {
        super("NR066N");
    }

}
