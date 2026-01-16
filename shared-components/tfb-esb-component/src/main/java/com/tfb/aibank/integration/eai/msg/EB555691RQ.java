/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB555691SvcRqType;

//@formatter:off
/**
* @(#)EB555691RQ.java
* 
* <p>Description:EB555691RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/15, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB555691RQ extends EAIRequest<EB555691SvcRqType> {

    private static final long serialVersionUID = 5490118681685124407L;

    /**
     * 建構子
     */
    public EB555691RQ() {
        super("EB555691");
    }

}
