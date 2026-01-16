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
import tw.com.ibm.mf.eb.NMI005SvcRqType;
import tw.com.ibm.mf.eb.NMI005SvcRsType;

// @formatter:off
/**
 * @param 
 * @(#)NMI005.java
 * 
 * <p>Description:奈米投契約明細查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        }

)
public class NMI005RS extends EAIResponse<NMI005SvcRqType, NMI005SvcRsType> {

    /**
     * 建構子
     */
    public NMI005RS() {
        super("NMI005");
    }

}
