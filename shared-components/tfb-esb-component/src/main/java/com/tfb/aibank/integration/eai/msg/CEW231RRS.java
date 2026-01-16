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

import tw.com.ibm.mf.eb.CEW231RSvcRqType;
import tw.com.ibm.mf.eb.CEW231RSvcRsType;

//@formatter:off
/**
* @(#)CEW231RRS.java
* 
* <p>Description:近六個月已繳金額明細下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/10, Rick
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, 
                        fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/RwdAmt")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, 
                        inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/RwdPday | eai:Tx/TxBody/TxRepeat/RwdNcday")
        }
)
//@formatter:on
public class CEW231RRS extends EAIResponse<CEW231RSvcRqType, CEW231RSvcRsType> {
    /**
     * 建構子
     */
    public CEW231RRS() {
        super("CEW231R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals("V003", errId);
    }
}
