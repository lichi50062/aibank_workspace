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

import tw.com.ibm.mf.eb.CEW314RSvcRqType;
import tw.com.ibm.mf.eb.CEW314RSvcRsType;

//@formatter:off
/**
* @(#)CEW314RRS.java
* 
* <p>Description:CEW314R 下行電文</p>
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
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/AACRAM | eai:Tx/TxBody/TxRepeat/AANCAM | eai:Tx/TxBody/TxRepeat/AATPAY | eai:Tx/TxBody/TxRepeat/AAOLBL | eai:Tx/TxBody/TxRepeat/AALPAM | eai:Tx/TxBody/TxRepeat/AANEBL | eai:Tx/TxBody/TxRepeat/AACIRE | eai:Tx/TxBody/TxRepeat/AATPAY | eai:Tx/TxBody/TxRepeat/AAMINP | eai:Tx/TxBody/TxRepeat/B1LCAM | eai:Tx/TxBody/TxRepeat/B2LCAM | eai:Tx/TxBody/TxRepeat/B3LCAM | eai:Tx/TxBody/TxRepeat/B5LBAL | eai:Tx/TxBody/TxRepeat/B5TUSE | eai:Tx/TxBody/TxRepeat/B5TADD | eai:Tx/TxBody/TxRepeat/B5TADJ | eai:Tx/TxBody/TxRepeat/B5TBAL | eai:Tx/TxBody/TxRepeat/B5EXBO"), 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/AAINTR | eai:Tx/TxBody/TxRepeat/B3FCAM"),
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/AACLDY | eai:Tx/TxBody/TxRepeat/AALMDY | eai:Tx/TxBody/TxRepeat/B1PHDY | eai:Tx/TxBody/TxRepeat/B1STDY | eai:Tx/TxBody/TxRepeat/B2PHDY | eai:Tx/TxBody/TxRepeat/B2STDY | eai:Tx/TxBody/TxRepeat/B3PHDY | eai:Tx/TxBody/TxRepeat/B3STDY | eai:Tx/TxBody/TxRepeat/B3FCDY | eai:Tx/TxBody/TxRepeat/B5EXDY")
        }
)
//@formatter:on
public class CEW314RRS extends EAIResponse<CEW314RSvcRqType, CEW314RSvcRsType> {
    /**
     * 建構子
     */
    public CEW314RRS() {
        super("CEW314R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId) || "V803".equals(errId);
    }
}
