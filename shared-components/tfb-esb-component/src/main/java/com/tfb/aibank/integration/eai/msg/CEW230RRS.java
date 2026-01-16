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

import tw.com.ibm.mf.eb.CEW230RSvcRqType;
import tw.com.ibm.mf.eb.CEW230RSvcRsType;

//@formatter:off
/**
* @(#)CEW230RRS.java
* 
* <p>Description:累計已繳金額明細下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
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
public class CEW230RRS extends EAIResponse<CEW230RSvcRqType, CEW230RSvcRsType> {
    /**
     * 建構子
     */
    public CEW230RRS() {
        super("CEW230R");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (StringUtils.equals("V003", errId)) {
            return true;
        }
        return false;
    }
}
