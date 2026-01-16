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

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CE5552RSvcRqType;
import tw.com.ibm.mf.eb.CE5552RSvcRsType;

//@formatter:off
/**
* @(#)CE6220RRS.java
* 
* <p>Description:信用卡卡片總覽下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/PIFUNC | eai:Tx/TxBody/PICDNO | eai:Tx/TxBody/PILPHY | eai:Tx/TxBody/PIFPHY | eai:Tx/TxBody/PILCNP"+" | eai:Tx/TxBody/PIFCNP | eai:Tx/TxBody/PILTKN | eai:Tx/TxBody/PIFTKN" 
                                + " | eai:Tx/TxBody/PILPAM | eai:Tx/TxBody/PIFPAM | eai:Tx/TxBody/PILCAM | eai:Tx/TxBody/PIFCAM | eai:Tx/TxBody/PILTAM | eai:Tx/TxBody/PIFTAM | eai:Tx/TxBody/PICHDY | eai:Tx/TxBody/PICHTM"
                 )
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/PICHDY")
        }
)
//@formatter:on
public class CE5552RRS extends EAIResponse<CE5552RSvcRqType, CE5552RSvcRsType> {

    /**
     * 建構子
     */
    public CE5552RRS() {
        super("CE5552R");
    }

    @Override
    protected boolean isNoData(String errId) {

        return StringUtils.equals("V003", errId);
    }
}
