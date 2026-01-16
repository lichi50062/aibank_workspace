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

import tw.com.ibm.mf.eb.CE4153RSvcRqType;

//@formatter:off
/**
* @(#)CE4153RRQ.java
* 
* <p>Description:指定消費分期設定交易 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/03, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CE4153RRQ extends EAIRequest<CE4153RSvcRqType> {

    private static final long serialVersionUID = -904256140826245232L;

    /**
     * 建構子.
     */
    public CE4153RRQ() {
        super("CE4153R");
    }

}
