package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EBFRE1SvcRqType;
import tw.com.ibm.mf.eb.EBFRE1SvcRsType;

//@formatter:off
/**
* @(#)EBFRE1RS.java
* 
* <p>Description:外幣匯入款申報</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/12, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
   customConverters = { 
           @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/RESCODE | eai:Tx/TxBody/ITEM | eai:Tx/TxBody/ENDMK | eai:Tx/TxRepeat/*[name() != 'data'] | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
   }, 
   datetimeConverters = { 
           @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/VALUE_DATE | eai:Tx/TxBody/TxRepeat/IR05_DATE")
   }, 
   decimalConverters = { 
           @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AMT")
   }
)
//@formatter:on
public class EBFRE1RS extends EAIResponse<EBFRE1SvcRqType, EBFRE1SvcRsType> {
    /**
     * 建構子
     */
    public EBFRE1RS() {
        super("EBFRE1");
    }
}