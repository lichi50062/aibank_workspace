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
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VB0051SvcRqType;
import tw.com.ibm.mf.eb.VB0051SvcRsType;

//@formatter:off
/**
* @(#)VB0051RS.java
* 
* <p>Description:好多金餘額查詢 VB0051 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
 @Converter(
         customConverters = {
				 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
		 },
		 datetimeConverters = {
	              @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/Expdate")
	     }
)
//@formatter:on
public class VB0051RS extends EAIResponse<VB0051SvcRqType, VB0051SvcRsType> {

    /**
     * 建構子
     */
    public VB0051RS() {
        super("VB0051");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || StringUtils.equalsAny(errId, "V003", "V007", "V301", "V818");
    }
}
