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

import tw.com.ibm.mf.eb.CE6220RSvcRqType;

//@formatter:off
/**
* @(#)CE6220RRQ.java
* 
* <p>Description:信用卡卡片總覽上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CE6220RRQ extends EAIRequest<CE6220RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CE6220RRQ() {
        super("CE6220R");
    }

}