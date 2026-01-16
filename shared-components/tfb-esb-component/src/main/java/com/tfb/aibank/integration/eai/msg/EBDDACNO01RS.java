/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EBDDACNO01SvcRqType;
import tw.com.ibm.mf.eb.EBDDACNO01SvcRsType;

// @formatter:off
/**
 * @(#)EBDDACNO01RS.java
 * 
 * <p>Description:數位存款帳戶金融卡開卡電文 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/* ")
        }
)
// @formatter:on
public class EBDDACNO01RS extends EAIResponse<EBDDACNO01SvcRqType, EBDDACNO01SvcRsType> {

    /**
     * 建構子
     */
    public EBDDACNO01RS() {
        super("EBDDACNO01");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equalsAny(errId, "ERI4", "ERH2");

    }
}
