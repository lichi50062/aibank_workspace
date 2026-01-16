package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NF032333SvcRqType;
import tw.com.ibm.mf.eb.NF032333SvcRsType;

// @formatter:off
/**
 * @(#)NF032333RS.java
 * 
 * <p>Description:取得分行代碼 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
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
public class NF032333RS extends EAIResponse<NF032333SvcRqType, NF032333SvcRsType> {

    /**
     * 建構子
     */
    public NF032333RS() {
        super("NF032333");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (super.isNoData(errId) || "0108".equals(errId)) {
            return true;
        }
        return false;
    }
}
