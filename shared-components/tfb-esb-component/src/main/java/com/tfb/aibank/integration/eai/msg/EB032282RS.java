package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032282SvcRqType;
import tw.com.ibm.mf.eb.EB032282SvcRsType;

/**
 * <p>
 * Title: com.fubon.tw.pib.tr.message.EB032282RS
 * </p>
 * <p>
 * Description: EB032282 下行電文
 * </p>
 * <p>
 * Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 *
 * @author Edward Tien
 * @version 1.0
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
                        " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT ")
        }
)
public class EB032282RS extends EAIResponse<EB032282SvcRqType, EB032282SvcRsType> {
    /**
     * 建構子
     */
    public EB032282RS() {
        super("EB032282");
    }
}
