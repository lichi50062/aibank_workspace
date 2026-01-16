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

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.CE5552RSvcRqType;

//@formatter:off
/**
* @(#)CE6220RRQ.java
* 
* <p>Description:信用卡卡片總覽上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern="00000000", scale = 0, fieldXPath = "eai:Tx/TxBody/PILPAM | eai:Tx/TxBody/PIFPAM | eai:Tx/TxBody/PILCAM | eai:Tx/TxBody/PIFCAM | eai:Tx/TxBody/PILTAM | eai:Tx/TxBody/PIFTAM")
        }
)
//@formatter:on
public class CE5552RRQ extends EAIRequest<CE5552RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CE5552RRQ() {
        super("CE5552R");
    }

}