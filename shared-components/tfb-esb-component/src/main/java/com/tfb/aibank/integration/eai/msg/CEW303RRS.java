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

import tw.com.ibm.mf.eb.CEW303RSvcRqType;
import tw.com.ibm.mf.eb.CEW303RSvcRsType;

//@formatter:off
/**
* @(#)CEW303RRS.java
* 
* <p>Description:信用卡帳務資訊下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/AcctIdCram | eai:Tx/TxBody/AcctIdPcbl | eai:Tx/TxBody/AcctIdTmmh | eai:Tx/TxBody/AcctIdCcbl | eai:Tx/TxBody/AcctIdSbal | eai:Tx/TxBody/AcctIdMinp | eai:Tx/TxBody/AcctIdPayd | eai:Tx/TxBody/AcctIdTmmhF | eai:Tx/TxBody/AcctIdCcblF | eai:Tx/TxBody/AcctIdDpayd | eai:Tx/TxBody/AcctIdub |eai:Tx/TxBody/AcctIdoreS"),
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/AcctIdPcblS | eai:Tx/TxBody/AcctIdubS")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/AcctIdCldy | eai:Tx/TxBody/AcctIdDudy | eai:Tx/TxBody/AcctIdLpdy | eai:Tx/TxBody/NextCycleDate")
        }
)
//@formatter:on
public class CEW303RRS extends EAIResponse<CEW303RSvcRqType, CEW303RSvcRsType> {
    /**
     * 建構子
     */
    public CEW303RRS() {
        super("CEW303R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V803".equals(errId);
    }
}
