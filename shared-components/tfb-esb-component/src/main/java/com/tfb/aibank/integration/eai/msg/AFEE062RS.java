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

import tw.com.ibm.mf.eb.AFEE062SvcRqType;
import tw.com.ibm.mf.eb.AFEE062SvcRsType;

// @formatter:off
/**
 * @(#)AFEE062RS.java
 * 
 * <p>OBU設定基金滿足停損點</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/04, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/CustNum | eai:Tx/TxBody/CustMail")
        }
)
//@formatter:on
public class AFEE062RS extends EAIResponse<AFEE062SvcRqType, AFEE062SvcRsType> {
    /**
     * 建構子
     */
    public AFEE062RS() {
        super("AFEE062");
    }
}
