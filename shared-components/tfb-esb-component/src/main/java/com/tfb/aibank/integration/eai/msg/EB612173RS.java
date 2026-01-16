package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB612173SvcRqType;
import tw.com.ibm.mf.eb.EB612173SvcRsType;

// @formatter:off
/**
 * @(#)EB612173RS.java
 * 
 * <p>Description:EB612173網銀國外匯款匯款申請資料建檔及維護下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/07, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/NAME | eai:Tx/TxBody/NAME1 | eai:Tx/TxBody/ADDR | eai:Tx/TxBody/ENG_ADDR1 | eai:Tx/TxBody/ENG_ADDR2")
        }
)
// @formatter:on
public class EB612173RS extends EAIResponse<EB612173SvcRqType, EB612173SvcRsType> {

    /**
     * 建構子
     */
    public EB612173RS() {
        super("EB612173");
    }

    @Override
    protected boolean isNoData(String errId) {
        // 使用者未留英文姓名
        return "EP62".equals(errId);
    }

}
