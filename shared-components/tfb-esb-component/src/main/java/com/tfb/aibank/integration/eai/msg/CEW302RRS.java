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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW302RSvcRqType;
import tw.com.ibm.mf.eb.CEW302RSvcRsType;

//@formatter:off
/**
* @(#)CEW302RRS.java
* 
* <p>Description:信用卡戶況查詢下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/04, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/

@Converter(
        customConverters = { 
                @CustomConverter(
                        converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/CARD_HOLDER_ID | eai:Tx/TxBody/NAME | eai:Tx/TxBody/SEX | eai:Tx/TxBody/EMAIL_ADDRESS | eai:Tx/TxBody/APPLY_EMAIL | eai:Tx/TxBody/APPLY_BILL | eai:Tx/TxBody/BIRTHD | eai:Tx/TxBody/Occur"), 
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(
                        converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/BIRTHD | eai:Tx/TxBody/TxRepeat/ISSUE_DAY")
        },
        decimalConverters = {
                @DecimalConverter(
                        converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/DOMESTIC_CASH_ADVANCE_BALANCE_AVAILABLE")
        }
        
)
//@formatter:on
public class CEW302RRS extends EAIResponse<CEW302RSvcRqType, CEW302RSvcRsType> {

    /**
     * 建構子
     */
    public CEW302RRS() {
        super("CEW302R");
    }

}
