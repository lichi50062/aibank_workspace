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
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12010029SvcRqType;
import tw.com.ibm.mf.eb.EB12010029SvcRsType;

// @formatter:off
/**
 * @(#)EB12010029RS.java
 * 
 * <p>Description:EB12010029 約定自動扣繳設定(國民年金)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/12, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/RES_COD | eai:Tx/TxBody/RES_COD_CH | eai:Tx/TxBody/SEQNO | eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/TX_TIME"),
        }
    )
// @formatter:on
public class EB12010029RS extends EAIResponse<EB12010029SvcRqType, EB12010029SvcRsType> {

    /**
     * 建構子
     */
    public EB12010029RS() {
        super("EB12010029");
    }

}
