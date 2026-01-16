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
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB553911SvcRqType;
import tw.com.ibm.mf.eb.EB553911SvcRsType;

//@formatter:off
/**
* @(#)EB553911RS.java
* 
* <p>Description:綜合存款轉存綜合定期性存款(EB553911)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"+
                         " | eai:Tx/TxBody/ACT_BAL_1 | eai:Tx/TxBody/ACT_BAL_2 | eai:Tx/TxBody/ACT_BAL_3"+
                         " | eai:Tx/TxBody/AVAIL_BAL_1 | eai:Tx/TxBody/AVAIL_BAL_2 | eai:Tx/TxBody/AVAIL_BAL_3" +
                         " | eai:Tx/TxBody/OD_AMT_1 | eai:Tx/TxBody/OD_AMT_2 | eai:Tx/TxBody/OD_AMT_3" +
                         " | eai:Tx/TxBody/REMARK_1 | eai:Tx/TxBody/REMARK_2 | eai:Tx/TxBody/REMARK_3" +
                         " | eai:Tx/TxBody/HOST_SRL_1 | eai:Tx/TxBody/HOST_SRL_2 | eai:Tx/TxBody/HOST_SRL_3" +
                         " | eai:Tx/TxBody/INT_RATE_1 | eai:Tx/TxBody/INT_RATE_2 | eai:Tx/TxBody/INT_RATE_3")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/ACT_BAL_1 | eai:Tx/TxBody/ACT_BAL_2 | eai:Tx/TxBody/ACT_BAL_3" +
                                " | eai:Tx/TxBody/AVAIL_BAL_1 | eai:Tx/TxBody/AVAIL_BAL_2 | eai:Tx/TxBody/AVAIL_BAL_3" +
                                " | eai:Tx/TxBody/OD_AMT_1 | eai:Tx/TxBody/OD_AMT_2 | eai:Tx/TxBody/OD_AMT_3"),
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 8, isPostSign = true, fieldXPath = "eai:Tx/TxBody/INT_RATE_1 | eai:Tx/TxBody/INT_RATE_2 | eai:Tx/TxBody/INT_RATE_3")
        }
)
//@formatter:on
public class EB553911RS extends EAIResponse<EB553911SvcRqType, EB553911SvcRsType> {
    /**
     * 建構子
     */
    public EB553911RS() {
        super("EB553911");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E520".equals(errId);
    }

}
