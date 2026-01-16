package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EBCC002SvcRqType;
import tw.com.ibm.mf.eb.EBCC002SvcRsType;

// @formatter:off
/**
 * @(#)EBCC002RS.java
 * 
 * <p>Description:EBCC002 信用卡額度申請(永調)啟案電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/APPLYNO")
        }
)
// @formatter:on
public class EBCC002RS extends EAIResponse<EBCC002SvcRqType, EBCC002SvcRsType> {

    /**
     * 建構子
     */
    public EBCC002RS() {
        super("EBCC002");
    }
}
