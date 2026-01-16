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

import tw.com.ibm.mf.eb.CEW336RSvcRqType;

//@formatter:off
/**
* @(#)CEW336RRQ.java
* 
* <p>Description:信用卡─虛擬卡片 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW336RRQ extends EAIRequest<CEW336RSvcRqType> {

    private static final long serialVersionUID = -904256140826245232L;

    /**
     * 建構子.
     */
    public CEW336RRQ() {
        super("CEW336R");
    }

}
