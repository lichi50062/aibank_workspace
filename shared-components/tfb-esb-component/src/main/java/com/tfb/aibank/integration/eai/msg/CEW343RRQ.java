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

import tw.com.ibm.mf.eb.CEW343RSvcRqType;

//@formatter:off
/**
* @(#)CEW343RRQ.java
* 
* <p>Description:分期查詢交易 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/03, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW343RRQ extends EAIRequest<CEW343RSvcRqType> {

    private static final long serialVersionUID = -904256140826245232L;

    /**
     * 建構子.
     */
    public CEW343RRQ() {
        super("CEW343R");
    }

}
