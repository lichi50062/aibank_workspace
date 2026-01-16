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

import tw.com.ibm.mf.eb.CEW314RSvcRqType;

//@formatter:off
/**
* @(#)CEW314RRQ.java
* 
* <p>Description:CEW314R 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW314RRQ extends EAIRequest<CEW314RSvcRqType> {

    private static final long serialVersionUID = 1366693244559430056L;

    /**
     * 建構子
     */
    public CEW314RRQ() {
        super("CEW314R");
    }
}
