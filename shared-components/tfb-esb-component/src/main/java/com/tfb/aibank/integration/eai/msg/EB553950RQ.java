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
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB553950SvcRqType;

//@formatter:off
/**
* @(#)EB553950RQ.java
* 
* <p>Description:即時-繳他行信用卡費上送電文(EB553950)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/OUT_IDNO"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 10, fieldXPath = "eai:Tx/TxBody/USER_ID"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 50, fieldXPath = "eai:Tx/TxBody/REMARK"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 50, fieldXPath = "eai:Tx/TxBody/REMARKL"),
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 16, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO_OUT | eai:Tx/TxBody/ACNO_IN")
        },         
		decimalConverters = {
				@DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = -3, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx/TxBody/TX_AMT")
        },
        datetimeConverters = {
        		@DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE")
        }
)
//@formatter:on
public class EB553950RQ extends EAIRequest<EB553950SvcRqType> {

    private static final long serialVersionUID = 3455222972769830636L;

    /**
     * 建構子
     */
    public EB553950RQ() {
        super("EB553950");
    }

}
