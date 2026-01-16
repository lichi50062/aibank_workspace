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

import tw.com.ibm.mf.eb.NAWEBP03SvcRqType;
import tw.com.ibm.mf.eb.NAWEBP03SvcRsType;

// @formatter:off
/**
 * @(#)NAWEBP03RS.java
 * 
 * <p>查詢參加契約</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/15, Yoyo Lin	
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
public class NAWEBP03RS extends EAIResponse<NAWEBP03SvcRqType, NAWEBP03SvcRsType> {

    /**
     * 建構子
     */
    public NAWEBP03RS() {
        super("NAWEBP03");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equalsAny(errId, "EA09", "EA07");
    }

}
