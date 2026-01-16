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
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW332RSvcRqType;
import tw.com.ibm.mf.eb.CEW332RSvcRsType;

//@formatter:off
/**
* @(#)CEW332RRS.java
* 
* <p>Description: 歸戶下附卡查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/25, Aaron
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*"
                        + " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/VNACID"
                        + " | eai:Tx/TxBody/VNCPNM | eai:Tx/TxBody/VNTITL | eai:Tx/TxBody/VNTLO1 | eai:Tx/TxBody/VNTLO2"
                        + " | eai:Tx/TxBody/VNTEL2 | eai:Tx/TxBody/VNMAIL | eai:Tx/TxBody/Occur"),
                @CustomConverter(converter = EAIReplaceConverter.class, replaceRegex = "", replacement = "○", fieldXPath = "eai:Tx/TxBody/TxRepeat/VNCNAM")
        }
)
//@formatter:on
public class CEW332RRS extends EAIResponse<CEW332RSvcRqType, CEW332RSvcRsType> {

    /**
     * 建構子
     */
    public CEW332RRS() {
        super("CEW332R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V803".equals(errId);
    }

}
