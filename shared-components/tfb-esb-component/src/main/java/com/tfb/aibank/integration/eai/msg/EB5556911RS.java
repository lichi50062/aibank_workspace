package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB5556911SvcRqType;
import tw.com.ibm.mf.eb.EB5556911SvcRsType;

// @formatter:off
/**
 * @(#)EB5556911RS.java
 * 
 * <p>Description:EB5556911 約定轉入帳號 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/USER_ID_LEVEL | eai:Tx/TxBody/EB_APPLY_FLG | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
                @CustomConverter(converter = EAIReplaceConverter.class, replaceRegex = "", replacement = "○", fieldXPath = "eai:Tx/TxBody/TxRepeat/CUST_NAME")
        }
)
// @formatter:on
public class EB5556911RS extends EAIResponse<EB5556911SvcRqType, EB5556911SvcRsType> {

    /**
     * 建構子
     */
    public EB5556911RS() {
        super("EB5556911");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equalsAny(errId, "E005", "EBB2") || isCBSNoData(errId);
    }
}
