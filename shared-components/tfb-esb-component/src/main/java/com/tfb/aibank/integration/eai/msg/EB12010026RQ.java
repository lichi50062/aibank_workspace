/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12010026SvcRqType;

//@formatter:off
/**
* @(#)EB12010026RQ.java
* 
* <p>Description: EB12010026 電文上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/03/25, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12010026RQ extends EAIRequest<EB12010026SvcRqType> {

    /**
     * 建構子
     */
    public EB12010026RQ() {
        super("EB12010026");
    }
    
}
