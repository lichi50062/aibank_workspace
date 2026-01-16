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

import tw.com.ibm.mf.eb.GWAPI051SvcRqType;
import tw.com.ibm.mf.eb.GWAPI051SvcRsType;

// @formatter:off
/**
 * @(#)GWAPI051RS.java
 * 
 * <p>Description:GWAPI051RS 查詢他行戶名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/24, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/code | eai:Tx/TxBody/accountTransferType | eai:Tx/TxBody/bankCode | eai:Tx/TxBody/accountType | eai:Tx/TxBody/accountName"),
        }
)
// @formatter:on
public class GWAPI051RS extends EAIResponse<GWAPI051SvcRqType, GWAPI051SvcRsType> {

    /**
     * 建構子
     */
    public GWAPI051RS() {
        super("GWAPI051");
    }

}
