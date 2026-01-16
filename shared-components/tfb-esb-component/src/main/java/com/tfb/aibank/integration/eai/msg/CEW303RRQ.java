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

import tw.com.ibm.mf.eb.CEW303RSvcRqType;

//@formatter:off
/**
* @(#)CEW303RRQ.java
* 
* <p>Description:信用卡帳務資訊上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW303RRQ extends EAIRequest<CEW303RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW303RRQ() {
        super("CEW303R");
    }

}
