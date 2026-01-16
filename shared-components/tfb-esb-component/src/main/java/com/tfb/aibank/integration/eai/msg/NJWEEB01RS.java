package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEEB01SvcRqType;
import tw.com.ibm.mf.eb.NJWEEB01SvcRsType;

// @formatter:off
/**
 * @(#)NJWEEB01RS.java
 * 
 * <p>Description:我的觀察債券設定 NJWEEB01</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur")
        }
)
// @formatter:on
public class NJWEEB01RS extends EAIResponse<NJWEEB01SvcRqType, NJWEEB01SvcRsType> {

    public NJWEEB01RS() {
        super("NJWEEB01");
    }

}
