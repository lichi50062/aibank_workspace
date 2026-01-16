package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032280SvcRqType;
import tw.com.ibm.mf.eb.EB032280SvcRsType;

// @formatter:off
/**
 * @(#)EB032280RS.java
 * 
 * <p>Description:EB032280 客戶辦理投資有價證券資訊提供聲明書維護</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ETF_FLAG")
        }
)
// @formatter:on
public class EB032280RS extends EAIResponse<EB032280SvcRqType, EB032280SvcRsType> {

    /**
     * 建構子
     */
    public EB032280RS() {
        super("EB032280");
    }

}
