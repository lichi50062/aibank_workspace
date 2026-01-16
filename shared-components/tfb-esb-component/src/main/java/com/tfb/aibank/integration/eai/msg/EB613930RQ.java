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

import tw.com.ibm.mf.eb.EB613930SvcRqType;

//@formatter:off
/**
* @(#)EB613930RS.java
* 
* <p>Description:EB613930預約轉帳</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/29, HankChan    
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
      customConverters = { 
              @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 16, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO_OUT"),
              @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/IDNO"),
              @CustomConverter(converter = EAIPadRightConverter.class, padSize = 10, fieldXPath = "eai:Tx/TxBody/USER_ID")
      },  
      datetimeConverters = {
              @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/PAY_DATE"),
      }, 
      decimalConverters = { 
              @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "000000000000000", scale = -2, fieldXPath = "eai:Tx/TxBody/TRF_OUT_AMT"), 
              @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "000000000000000", scale = -2, fieldXPath = "eai:Tx/TxBody/TRF_IN_AMT"), 
      }
)
//@formatter:on
public class EB613930RQ extends EAIRequest<EB613930SvcRqType> {

    private static final long serialVersionUID = -6939620911566895754L;

    /**
     * 建構子
     */
    public EB613930RQ() {
        super("EB613930");
    }

}