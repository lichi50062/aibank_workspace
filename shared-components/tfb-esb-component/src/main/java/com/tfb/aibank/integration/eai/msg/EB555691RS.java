/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.ArrayUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB555691SvcRqType;
import tw.com.ibm.mf.eb.EB555691SvcRsType;

//@formatter:off
/**
* @(#)EB555691RS.java
* 
* <p>Description:EB555691RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/15, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/USER_ID_LEVEL | eai:Tx/TxBody/EB_APPLY_FLG | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
//@formatter:on
public class EB555691RS extends EAIResponse<EB555691SvcRqType, EB555691SvcRsType> {

    /**
     * 建構子
     */
    public EB555691RS() {
        super("EB555691");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E005".equals(errId) || "EBB2".equals(errId) || ArrayUtils.contains(CBS_NO_DATA_ERRID, errId);
    }

}
