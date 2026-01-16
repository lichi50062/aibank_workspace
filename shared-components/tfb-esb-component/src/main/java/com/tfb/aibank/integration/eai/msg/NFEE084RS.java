package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE084SvcRqType;
import tw.com.ibm.mf.eb.NFEE084SvcRsType;

// @formatter:off
/**
 * @(#)NFEE084RS.java
 * 
 * <p>Description:NFEE084 基金觀察到價設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        }
)
// @formatter:on
public class NFEE084RS extends EAIResponse<NFEE084SvcRqType, NFEE084SvcRsType> {

    /**
     * 建構子
     */
    public NFEE084RS() {
        super("NFEE084");
    }
}
