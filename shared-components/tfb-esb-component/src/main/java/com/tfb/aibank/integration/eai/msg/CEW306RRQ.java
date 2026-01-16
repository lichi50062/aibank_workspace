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

import tw.com.ibm.mf.eb.CEW306RSvcRqType;

//@formatter:off
/**
* @(#)CEW306RRQ.java
* 
* <p>Description:CEW211RRQ(紅利績點點數查詢)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW306RRQ extends EAIRequest<CEW306RSvcRqType> {

    private static final long serialVersionUID = 7187699741438983472L;

    /**
     * 建構子
     */
    public CEW306RRQ() {
        super("CEW306R");
    }

}
