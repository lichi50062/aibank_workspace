/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB12SvcRqType;

//@formatter:off
/**
* @(#)NMWEB12RQ.java
* 
* <p>Description: NMWEB12 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/08, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NMWEB12RQ extends EAIRequest<NMWEB12SvcRqType> {

    /**
     * 建構子
     */
    public NMWEB12RQ() {
        super("NMWEB12");
    }
}
