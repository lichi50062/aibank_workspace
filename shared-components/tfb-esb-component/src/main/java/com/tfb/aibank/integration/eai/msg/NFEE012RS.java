package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE012SvcRqType;
import tw.com.ibm.mf.eb.NFEE012SvcRsType;

/**
 * <p>Title: NFEE012RS</p>
 * <p>Description: 首購狀態查詢 下行電文</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2014. All Rights Reserved.</p>
 *
 * @version 1.0
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
public class NFEE012RS extends EAIResponse<NFEE012SvcRqType, NFEE012SvcRsType> {
    public NFEE012RS() {
        super("NFEE012");
    }
}
