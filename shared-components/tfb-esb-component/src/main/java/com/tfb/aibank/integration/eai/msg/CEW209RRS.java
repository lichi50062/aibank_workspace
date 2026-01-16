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

import tw.com.ibm.mf.eb.CEW209RSvcRqType;
import tw.com.ibm.mf.eb.CEW209RSvcRsType;

// @formatter:off
/**
 * @(#)CEW209RRS.java
 * 
 * <p>Description: 信用卡Email設定下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        }
)
//@formatter:on
public class CEW209RRS extends EAIResponse<CEW209RSvcRqType, CEW209RSvcRsType> {

    /**
     * 建構子
     */
    public CEW209RRS() {
        super("CEW209R");
    }

}
