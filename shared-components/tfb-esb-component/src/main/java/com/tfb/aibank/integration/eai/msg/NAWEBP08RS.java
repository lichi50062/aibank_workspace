/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
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

import tw.com.ibm.mf.eb.NAWEBP08SvcRqType;
import tw.com.ibm.mf.eb.NAWEBP08SvcRsType;

//@formatter:off
/**
* @(#)NAWEBP08.java
* 
* <p>Description: 持股現金領回明細查詢 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/27, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/RECV_SUM_CASE | eai:Tx/TxBody/TxRepeat/RECV_AMT | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        },
        decimalConverters = {
            @DecimalConverter(converter = EAIDecimalConverter.class, scale=0, fieldXPath = "eai:Tx/TxBody/RECV_SEQ | eai:Tx/TxBody/RECV_CNT"),
        },
        datetimeConverters = {
            @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/RECV_DATE")
        }
)
//@formatter:on
public class NAWEBP08RS extends EAIResponse<NAWEBP08SvcRqType, NAWEBP08SvcRsType> {

	/**
     * 建構子
     */
    public NAWEBP08RS() {
        super("NAWEBP08");
    }
    
    @Override
    protected boolean isNoData(String errId) {
        return "EA07".equals(errId);
    }
}
