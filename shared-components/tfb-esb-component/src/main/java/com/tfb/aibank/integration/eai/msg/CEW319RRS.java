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

import tw.com.ibm.mf.eb.CEW319RSvcRqType;
import tw.com.ibm.mf.eb.CEW319RSvcRsType;

//@formatter:off
/**
* @(#)CEW319RRS.java
* 
* <p>Description:信用卡客戶身分驗證下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/20 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = { 
       
    }, 
    datetimeConverters = { 
      
    }, 
    decimalConverters = {   
            
    }
)
//@formatter:on
public class CEW319RRS extends EAIResponse<CEW319RSvcRqType, CEW319RSvcRsType> {

    /**
     * 建構子
     */
    public CEW319RRS() {
        super("CEW319R");
    }
}
