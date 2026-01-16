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

import tw.com.ibm.mf.eb.CEW424RSvcRqType;
import tw.com.ibm.mf.eb.CEW424RSvcRsType;

//@formatter:off
/**
* @(#)CEW424RRS.java
* 
* <p>Description:卡片暱稱維護查詢交易</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW424RRS extends EAIResponse<CEW424RSvcRqType, CEW424RSvcRsType> {

    /**
     * 建構子
     */
    public CEW424RRS() {
        super("CEW424R");
    }

}
