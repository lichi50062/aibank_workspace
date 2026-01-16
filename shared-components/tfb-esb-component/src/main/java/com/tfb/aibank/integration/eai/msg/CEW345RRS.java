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

import tw.com.ibm.mf.eb.CEW345RSvcRqType;
import tw.com.ibm.mf.eb.CEW345RSvcRsType;

//@formatter:off
/**
* @(#)CEW345RRS.java
* 
* <p>Description:保費權益設定-查詢 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/10, LEIPING
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/ACID | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/TxRepeat/*"),
        },
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/CHANGE_DATEA | eai:Tx/TxBody/TxRepeat/CHANGE_DATEB")
        }
)
//@formatter:on
public class CEW345RRS extends EAIResponse<CEW345RSvcRqType, CEW345RSvcRsType> {

    /**
     * 建構子
     */
    public CEW345RRS() {
        super("CEW345R");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (StringUtils.equals("V003", errId)) {
            return true;
        }
        return false;
    }
}
