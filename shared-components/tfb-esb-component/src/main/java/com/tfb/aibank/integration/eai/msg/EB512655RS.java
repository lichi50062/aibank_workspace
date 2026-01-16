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

import tw.com.ibm.mf.eb.EB512655SvcRqType;
import tw.com.ibm.mf.eb.EB512655SvcRsType;

// @formatter:off
/**
 * @(#)EB512655RS.java
 * 
 * <p>Description:EB512655RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/17, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxBody/*[name() != 'TxRepeat'] | eai:Tx/TxBody/TxRepeat/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/WD_ACC_AMT1 | eai:Tx/TxBody/WD_ACC_AMT2 | " +
                        "eai:Tx/TxBody/TRN_ACC_AMT1 | eai:Tx/TxBody/TRN_ACC_AMT2 | eai:Tx/TxBody/TRN_ACC_AMT3 | " +
                        "eai:Tx/TxBody/TRN_ACC_AMT4 | eai:Tx/TxBody/TRN_ACC_AMT5 |  eai:Tx/TxBody/TRN_ACC_AMT6 | " +
                        "eai:Tx/TxBody/TRN_IDACC_AMT1 | eai:Tx/TxBody/TRN_IDACC_AMT2 | eai:Tx/TxBody/TRN_ACC_AMT7 | " +
                        "eai:Tx/TxBody/TRN_ACC_AMT8 | eai:Tx/TxBody/TRN_ACC_AMT9 | eai:Tx/TxBody/TRN_ACC_AMT10 | " +
                        "eai:Tx/TxBody/TRN_ACC_AMT11 | eai:Tx/TxBody/TRN_ACC_AMT12 | eai:Tx/TxBody/TxRepeat/ColDlyTotal")
        }
)
// @formatter:on
public class EB512655RS extends EAIResponse<EB512655SvcRqType, EB512655SvcRsType> {

    /**
     * 建構子
     */
    public EB512655RS() {
        super("EB512655");
    }
}
