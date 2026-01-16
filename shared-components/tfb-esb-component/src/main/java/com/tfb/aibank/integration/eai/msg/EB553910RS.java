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

import tw.com.ibm.mf.eb.EB553910SvcRqType;
import tw.com.ibm.mf.eb.EB553910SvcRsType;

//@formatter:off
/**
* @(#)EB553910RS.java
* 
* <p>Description:預約-繳自行信用卡費下行電文(EB553910)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/FEE")
        },
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE")
        }
)
//@formatter:on
public class EB553910RS extends EAIResponse<EB553910SvcRqType, EB553910SvcRsType> {
    /**
     * 建構子
     */
    public EB553910RS() {
        super("EB553910");
    }

    @Override
    public String getErrorCode() {
        // TxHead.HERRID或TxBody.ERROR
        String errorCode = getHeader().getHERRID();
        if ("0000".equals(errorCode)) {
            if (StringUtils.isNotBlank(getBody().getERROR())) {
                errorCode = getBody().getERROR();
            }
        }
        return errorCode;
    }

    @Override
    public String getInternalErrorCode() {
        EB553910SvcRsType body = getBody();
        if (body != null && StringUtils.isNotBlank(body.getERROR())) {
            return StringUtils.trimToEmptyEx(body.getERROR());
        }
        return super.getInternalErrorCode();
    }

}
