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

import tw.com.ibm.mf.eb.CEW327RSvcRqType;
import tw.com.ibm.mf.eb.CEW327RSvcRsType;

//@formatter:off
/**
* @(#)CEW327RRS.java
* 
* <p>Description:保費權益設定-查詢 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/ACID | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/TxRepeat/*"),
        },
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/CHANGE_DATE")
        }
)
//@formatter:on
public class CEW327RRS extends EAIResponse<CEW327RSvcRqType, CEW327RSvcRsType> {

    /**
     * 建構子
     */
    public CEW327RRS() {
        super("CEW327R");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (StringUtils.equals("V003", errId)) {
            return true;
        }
        return false;
    }
}
