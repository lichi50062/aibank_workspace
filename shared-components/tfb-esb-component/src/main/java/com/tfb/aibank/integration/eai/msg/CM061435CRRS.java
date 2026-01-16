package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CM061435CRSvcRqType;
import tw.com.ibm.mf.eb.CM061435CRSvcRsType;

// @formatter:off
/**
 * @(#)CM061435CRRS.java
 *
 * <p>Description CM061435CRRS</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/07/10,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
//@formatter:on
public class CM061435CRRS extends EAIResponse<CM061435CRSvcRqType, CM061435CRSvcRsType> {

    /**
     * 建構子
     */
    public CM061435CRRS() {
        super("CM061435CR");
    }

}
