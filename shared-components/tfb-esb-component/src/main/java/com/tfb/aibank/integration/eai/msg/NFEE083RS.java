package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE083SvcRqType;
import tw.com.ibm.mf.eb.NFEE083SvcRsType;

// @formatter:off
/**
 * @(#)NFEE083RS.java
 * 
 * <p>Description:NFEE083 基金已終止憑證查詢 RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        }
)
// @formatter:on
public class NFEE083RS extends EAIResponse<NFEE083SvcRqType, NFEE083SvcRsType> {

    public NFEE083RS() {
        super("NFEE083");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals("V003", errId);
    }
}
