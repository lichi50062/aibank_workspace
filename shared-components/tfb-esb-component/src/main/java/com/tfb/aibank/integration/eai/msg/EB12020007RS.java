package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020007SvcRqType;
import tw.com.ibm.mf.eb.EB12020007SvcRsType;

// @formatter:off
/**
 * @(#)EB12020007RS.java
 * 
 * <p>Description:查詢/變更客戶通訊資料下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, Edward	Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/* ")
        }
)
// @formatter:on
public class EB12020007RS extends EAIResponse<EB12020007SvcRqType, EB12020007SvcRsType> {

    /**
     * 建構子
     */
    public EB12020007RS() {
        super("EB12020007");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "EK63".equals(errId);
    }

}
