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
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032179SvcRqType;
import tw.com.ibm.mf.eb.EB032179SvcRsType;

// @formatter:off
/**
 * @(#)EB032179RS.java
 * 
 * <p>Description:優惠次數查詢╱修改上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
//@formatter:off
@Converter(
        customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/CUST_NAME | eai:Tx/TxBody/CUST_TYP | eai:Tx/TxBody/CUST_TYP_NAME | eai:Tx/TxBody/NUM_01 | eai:Tx/TxBody/NUM_02 | eai:Tx/TxBody/NUM_03 | eai:Tx/TxBody/NUM_04 | eai:Tx/TxBody/NUM_05 | eai:Tx/TxBody/NUM_06 | eai:Tx/TxBody/TX_AMT | eai:Tx/TxBody/ACT_AMT | eai:Tx/TxBody/FEE_AMT | eai:Tx/TxBody/TX_SRL | eai:Tx/TxBody/IDU_COD | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),     
})
//@formatter:on
public class EB032179RS extends EAIResponse<EB032179SvcRqType, EB032179SvcRsType> {

    /**
     * 建構子
     */
    public EB032179RS() {
        super("EB032179");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "ES63".equals(errId) || "ERH2".equals(errId);
    }
}
