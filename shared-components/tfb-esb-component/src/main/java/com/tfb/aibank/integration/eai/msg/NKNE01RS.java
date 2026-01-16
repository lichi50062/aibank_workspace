package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NKNE01SvcRqType;
import tw.com.ibm.mf.eb.NKNE01SvcRsType;

// @formatter:off
/**
 * @(#)NKNE01RS.java
 * 
 * <p>Description:股票客戶查詢下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
// @formatter:on
public class NKNE01RS extends EAIResponse<NKNE01SvcRqType, NKNE01SvcRsType> {

    public NKNE01RS() {
        super("NKNE01");
    }
}
