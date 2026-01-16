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

import tw.com.ibm.mf.eb.CEW321RSvcRqType;
import tw.com.ibm.mf.eb.CEW321RSvcRsType;

//@formatter:off
/**
* @(#)CEW321RRS.java
* 
* <p>Description:年度累積消費查詢下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMM", fieldXPath = "eai:Tx/TxBody/STR_DAY | eai:Tx/TxBody/END_DAY")
        }
)
//@formatter:on
public class CEW321RRS extends EAIResponse<CEW321RSvcRqType, CEW321RSvcRsType> {
    /**
     * 建構子
     */
    public CEW321RRS() {
        super("CEW321R");
    }
}
