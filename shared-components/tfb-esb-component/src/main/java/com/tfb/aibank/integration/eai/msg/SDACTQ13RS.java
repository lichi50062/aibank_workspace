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

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.SDACTQ13SvcRqType;
import tw.com.ibm.mf.eb.SDACTQ13SvcRsType;

// @formatter:off
/**
 * @(#)SDACTQ13RS.java
 * 
 * <p>Description:SDACTQ13RS 信託資產_SI產品明細</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/08, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/AcctId14 | eai:Tx/TxBody/SDPRD | eai:Tx/TxBody/PRDNAM | eai:Tx/TxBody/CCY")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/DEPSTR | eai:Tx/TxBody/DEPEND | eai:Tx/TxBody/PRDDTE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/IVAMT2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/SDAMT3"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/PLAMT")
        }
)
// @formatter:on
public class SDACTQ13RS extends EAIResponse<SDACTQ13SvcRqType, SDACTQ13SvcRsType> {

    /**
     * 建構子
     */
    public SDACTQ13RS() {
        super("SDACTQ13");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals(errId, "V003");
    }
}
