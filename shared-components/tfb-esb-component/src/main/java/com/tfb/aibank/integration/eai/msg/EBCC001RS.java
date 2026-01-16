package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EBCC001SvcRqType;
import tw.com.ibm.mf.eb.EBCC001SvcRsType;

// @formatter:off
/**
 * @(#)EBCC001RS.java
 * 
 * <p>Description:固定調額啟案檢核 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/26, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT ")
        }
)
// @formatter:on
public class EBCC001RS extends EAIResponse<EBCC001SvcRqType, EBCC001SvcRsType> {

    /**
     * 建構子
     */
    public EBCC001RS() {
        super("EBCC001RS");
    }

}
