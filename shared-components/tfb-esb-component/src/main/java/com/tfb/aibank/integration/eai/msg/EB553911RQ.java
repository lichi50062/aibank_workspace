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
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB553911SvcRqType;

// @formatter:off
/**
* @(#)EB553911RQ.java
* 
* <p>Description綜合存款轉存綜合定期性存款上送電文(EB553911)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        }, 
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, 
                                outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=-3,isPostSign = true,showPlusSign=true, pattern="00000000000000000", fieldXPath = "eai:Tx/TxBody/TX_AMT")
        }
)
//@formatter:on
public class EB553911RQ extends EAIRequest<EB553911SvcRqType> {

    private static final long serialVersionUID = -3307638022055576425L;

    /**
     * 建構子
     */
    public EB553911RQ() {
        super("EB553911");
    }

}
