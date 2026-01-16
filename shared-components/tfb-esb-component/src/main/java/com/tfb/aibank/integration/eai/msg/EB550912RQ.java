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

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.EB550912SvcRqType;

// @formatter:off
/**
* @(#)EB550912RQ.java
* 
* <p>Description綜合存款轉存綜合定期性存款上送電文(EB550912)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,scale = -3, pattern = "00000000000000000", isPostSign  = true, fieldXPath="eai:Tx/TxBody/TX_AMT")
        }
)
//@formatter:on
public class EB550912RQ extends EAIRequest<EB550912SvcRqType> {

    private static final long serialVersionUID = -3307638022055576425L;

    /**
     * 建構子
     */
    public EB550912RQ() {
        super("EB550912");
    }

}
