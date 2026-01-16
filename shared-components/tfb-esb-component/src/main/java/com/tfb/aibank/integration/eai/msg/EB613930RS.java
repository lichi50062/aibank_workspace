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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB613930SvcRqType;
import tw.com.ibm.mf.eb.EB613930SvcRsType;

//@formatter:off
/**
* @(#)EB613930RS.java
* 
* <p>Description:EB613930預約轉帳</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/29, HankChan    
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
     datetimeConverters = { 
             @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/PAY_DATE"), 
             @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/PREREQ_DATE | eai:Tx/TxBody/TxRepeat/PROC_DATE | eai:Tx/TxBody/TxRepeat/CANCEL_DATE"), 
     }, 
     decimalConverters = { 
             @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "0000", fieldXPath = "eai:Tx/TxRepeat/TRF_FEE"), 
     }
)
//@formatter:on
public class EB613930RS extends EAIResponse<EB613930SvcRqType, EB613930SvcRsType> {

    /**
     * 建構子
     */
    public EB613930RS() {
        super("EB613930");
    }

}
