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

import tw.com.ibm.mf.eb.EB122351SvcRqType;

//@formatter:off
/**
* @(#)EB122351RQ.java
* 
* <p>Description: EB122351 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/03/24, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB122351RQ extends EAIRequest<EB122351SvcRqType> {

    /**
     * 建構子
     */
    public EB122351RQ() {
        super("EB122351");
    }

}
