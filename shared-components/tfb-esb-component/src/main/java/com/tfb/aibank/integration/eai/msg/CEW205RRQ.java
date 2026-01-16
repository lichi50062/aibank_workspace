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

import tw.com.ibm.mf.eb.CEW205RSvcRqType;

//@formatter:off
/**
* @(#)CEW205RRQ.java
* 
* <p>Description:CEW205R 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW205RRQ extends EAIRequest<CEW205RSvcRqType> {

    private static final long serialVersionUID = 3755433889365784192L;

    /**
     * 建構子
     */
    public CEW205RRQ() {
        super("CEW205R");
    }
}
