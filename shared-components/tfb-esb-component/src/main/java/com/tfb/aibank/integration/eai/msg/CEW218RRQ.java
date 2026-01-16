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

import tw.com.ibm.mf.eb.CEW218RSvcRqType;

//@formatter:off
/**
* @(#)CEW218RRQ.java
* 
* <p>Description:CEW218R 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW218RRQ extends EAIRequest<CEW218RSvcRqType> {

    private static final long serialVersionUID = 1026174408715833819L;

    /**
     * 建構子
     */
    public CEW218RRQ() {
        super("CEW218R");
    }
}
