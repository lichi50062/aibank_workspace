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

import tw.com.ibm.mf.eb.SDACTQ12SvcRqType;
import tw.com.ibm.mf.eb.SDACTQ12SvcRsType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRqType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRsType;

// @formatter:off
/**
 * @(#)SDACTQ12RS.java
 * 
 * <p>Description:SDACTQ12RS 信託資產_SI產品約當臺幣</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/08, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/DEPSTR | eai:Tx/TxBody/TxRepeat/SDDTE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/IVAMT2 | eai:Tx/TxBody/TxRepeat/Amount"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/SDAMT3")
        }
)
// @formatter:on
public class SDACTQ12RS extends EAIResponse<SDACTQ12SvcRqType, SDACTQ12SvcRsType> {

    /**
     * 建構子
     */
    public SDACTQ12RS() {
        super("SDACTQ12");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals(errId, "V003");
    }
}
