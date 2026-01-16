package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NAWEBP07SvcRqType;
import tw.com.ibm.mf.eb.NAWEBP07SvcRsType;

// @formatter:off
/**
 * @(#)NAPBW5RS.java
 * 
 * <p>Description:NAWEBP07 持股表尾累計查詢 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/19, John Chang 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
// @formatter:on
public class NAWEBP07RS extends EAIResponse<NAWEBP07SvcRqType, NAWEBP07SvcRsType> {

    public NAWEBP07RS() {
        super("NAWEBP07");
    }
}
