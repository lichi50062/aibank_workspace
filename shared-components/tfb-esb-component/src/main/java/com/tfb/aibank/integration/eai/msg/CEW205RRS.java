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
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW205RSvcRqType;
import tw.com.ibm.mf.eb.CEW205RSvcRsType;

//@formatter:off
/**
* @(#)CEW205RRS.java
* 
* <p>Description:CEW205R 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/TxRepeat/NcbTwd"), 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/SRCAMT")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/NcbPchd | eai:Tx/TxBody/TxRepeat/NcbNccd | eai:Tx/TxBody/TxRepeat/NCCDAY")
        }
)
//@formatter:on
public class CEW205RRS extends EAIResponse<CEW205RSvcRqType, CEW205RSvcRsType> {
    /**
     * 建構子
     */
    public CEW205RRS() {
        super("CEW205R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId) || "V083".equals(errId);
    }
}
