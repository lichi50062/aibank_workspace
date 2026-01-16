package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NCWD001SvcRqType;
import tw.com.ibm.mf.eb.NCWD001SvcRsType;

// @formatter:off
/**
 * @(#)NCWD001RS.java
 * 
 * <p>Description:NCWD001 申請無卡提款序號 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(customConverters = { 
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
})
// @formatter:on
public class NCWD001RS extends EAIResponse<NCWD001SvcRqType, NCWD001SvcRsType> {

    /**
     * 建構子
     */
    public NCWD001RS() {
        super("NCWD001");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId);
    }
}