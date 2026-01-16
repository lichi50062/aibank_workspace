/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.FEP852835SvcRqType;
import tw.com.ibm.mf.eb.FEP852835SvcRsType;

/**
 * 查詢簽帳金融卡卡號對應卡片性質 下行電文
 * 
 * @author Evan Wanf
 */
// @formatter:off
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        }
)
//@formatter:on
public class FEP852835RS extends EAIResponse<FEP852835SvcRqType, FEP852835SvcRsType> {

    /**
     * 建構子
     */
    public FEP852835RS() {
        super("FEP852835");
    }

}
