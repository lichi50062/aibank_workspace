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

import tw.com.ibm.mf.eb.CE4150RSvcRqType;

//@formatter:off
/**
* @(#)CE4150RRQ.java
* 
* <p>Description:綜所稅分期設定交易 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/3/17, Gang Rong
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CE4150RRQ extends EAIRequest<CE4150RSvcRqType> {

    private static final long serialVersionUID = -904256140826245232L;

    /**
     * 建構子.
     */
    public CE4150RRQ() {
        super("CE4150R");
    }

}
