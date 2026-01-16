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

import tw.com.ibm.mf.eb.CEW329RSvcRqType;

//@formatter:off
/**
* @(#)CEW329RRQ.java
* 
* <p>Description:CEW329RRQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW329RRQ extends EAIRequest<CEW329RSvcRqType> {

    private static final long serialVersionUID = -7945757562506386588L;

    /**
     * 建構子
     */
    public CEW329RRQ() {
        super("CEW329R");
    }

}
