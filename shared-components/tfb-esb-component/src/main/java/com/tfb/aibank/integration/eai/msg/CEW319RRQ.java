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

import tw.com.ibm.mf.eb.CEW319RSvcRqType;

//@formatter:off
/**
* @(#)CEW319RRQ.java
* 
* <p>Description:信用卡客戶身分驗證上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/20 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW319RRQ extends EAIRequest<CEW319RSvcRqType> {

    private static final long serialVersionUID = 214893669947849098L;

    /**
     * 建構子
     */
    public CEW319RRQ() {
        super("CEW319R");
    }
}
