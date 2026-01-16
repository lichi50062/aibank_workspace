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

import tw.com.ibm.mf.eb.EB300712SvcRqType;
import tw.com.ibm.mf.eb.EB300712SvcRsType;

// @formatter:off
/**
 * @(#)EB362611RS.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=房貸(61)-02公教/03國宅)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ACNO_LN | eai:Tx/TxBody/DOC_NO | eai:Tx/TxBody/DOC_SEQ ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,isPostSign = true,scale = 3,
                        fieldXPath = " eai:Tx/TxBody/INS_BAL | eai:Tx/TxBody/LTG_BAL | eai:Tx/TxBody/TOT_BAL"),
        }
)
//@formatter:on
public class EB300712RS extends EAIResponse<EB300712SvcRqType, EB300712SvcRsType> {

    /**
     * 建構子
     */
    public EB300712RS() {
        super("EB300712");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "4998".equals(errId) || "E005".equals(errId)|| "EBB2".equals(errId);
    }
}
