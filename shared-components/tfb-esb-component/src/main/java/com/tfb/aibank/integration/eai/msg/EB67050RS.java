/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB67050SvcRqType;
import tw.com.ibm.mf.eb.EB67050SvcRsType;

//@formatter:off
/**
* @(#)EB67050RS.java
* 
* <p>Description:查詢ID項下Email 電文下行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/28, John    
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
     customConverters = { 
             @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/CUSTNO | eai:Tx/TxBody/EMAILADDR | eai:Tx/TxBody/MOBILE | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
     }, 
     datetimeConverters = { 
             @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/BDAY"),
     }
)
//@formatter:on
public class EB67050RS extends EAIResponse<EB67050SvcRqType, EB67050SvcRsType> {

    /**
     * 建構子
     */
    public EB67050RS() {
        super("EB67050");
    }

}
