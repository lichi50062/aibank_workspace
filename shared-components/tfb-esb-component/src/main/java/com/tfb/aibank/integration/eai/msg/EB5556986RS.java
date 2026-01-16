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
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.EB5556986SvcRqType;
import tw.com.ibm.mf.eb.EB5556986SvcRsType;

// @formatter:off
/**
 * @(#)EB5556986RS.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/10/09, Lichi.Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxBody/*[name() != 'MASS_CHK' and name() != 'TxRepeat']")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/PW_CHG_DATE | eai:Tx/TxBody/USERID_CHG_DATE"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/EBILL_STR_DATE | eai:Tx/TxBody/EBILL_END_DATE | eai:Tx/TxBody/BILL_END_DATE | eai:Tx/TxBody/BIRTHDAY ")
        }
)
// @formatter:on
public class EB5556986RS extends EAIResponse<EB5556986SvcRqType, EB5556986SvcRsType> {

    /**
     * 建構子
     */
    public EB5556986RS() {
        super("EB5556986");
    }
}
