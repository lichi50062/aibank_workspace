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

import tw.com.ibm.mf.eb.OA000403SvcRqType;
import tw.com.ibm.mf.eb.OA000403SvcRsType;

// @formatter:off
/**
 * @(#)OA000403RS.java
 * 
 * <p>Description:OA000403RS 查詢本行戶名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/24, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/accountName | eai:Tx/TxBody/isNatural | eai:Tx/TxBody/isVirtualAccount | eai:Tx/TxBody/closeDt"),
        }
)
// @formatter:on
public class OA000403RS extends EAIResponse<OA000403SvcRqType, OA000403SvcRsType> {

    /**
     * 建構子
     */
    public OA000403RS() {
        super("OA000403");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equalsAny(errId, "0108", "2045", "2333");
    }
}
