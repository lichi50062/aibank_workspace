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

import tw.com.ibm.mf.eb.CEW1410RSvcRqType;
import tw.com.ibm.mf.eb.CEW1410RSvcRsType;

//@formatter:off
/**
* @(#)CEW1410RRS.java
* 
* <p>Description:CEW1410R(信用卡掛失)下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
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
public class CEW1410RRS extends EAIResponse<CEW1410RSvcRqType, CEW1410RSvcRsType> {

    /**
     * 建構子
     */
    public CEW1410RRS() {
        super("CEW1410R");
    }

}
