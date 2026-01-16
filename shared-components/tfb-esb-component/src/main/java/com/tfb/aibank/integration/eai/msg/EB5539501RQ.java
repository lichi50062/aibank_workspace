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
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB5539501SvcRqType;

//@formatter:off
/**
* @(#)EB5539501RQ.java
* 
* <p>Description:即時-繳自行信用卡費上送電文(EB5539501)</p>
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
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 50, fieldXPath = "eai:Tx/TxBody/REMARK | eai:Tx/TxBody/REMARKL"),
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
public class EB5539501RQ extends EAIRequest<EB5539501SvcRqType> {

    private static final long serialVersionUID = -7268790970168144626L;

    /**
     * 建構子
     */
    public EB5539501RQ() {
        super("EB5539501");
    }

}
