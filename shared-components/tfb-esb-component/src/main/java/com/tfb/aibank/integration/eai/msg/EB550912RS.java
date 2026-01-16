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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB550912SvcRqType;
import tw.com.ibm.mf.eb.EB550912SvcRsType;

//@formatter:off
/**
* @(#)EB550912RS.java
* 
* <p>Description綜合存款轉存綜合定期性存款上送電文(EB550912)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/OD_AMT"),
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, pattern = "00000000000000000", isPostSign = true, fieldXPath = "eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAIL_BAL | eai:Tx/TxBody/INT_PAY")
        }
)
//@formatter:on
public class EB550912RS extends EAIResponse<EB550912SvcRqType, EB550912SvcRsType> {
    /**
     * 建構子
     */
    public EB550912RS() {
        super("EB550912");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E520".equals(errId);
    }

}
