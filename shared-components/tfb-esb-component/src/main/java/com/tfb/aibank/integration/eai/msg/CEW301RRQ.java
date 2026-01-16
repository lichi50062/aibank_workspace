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

import tw.com.ibm.mf.eb.CEW301RSvcRqType;

//@formatter:off
/**
* @(#)CEW301RRQ.java
* 
* <p>Description:信用卡戶況查詢上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW301RRQ extends EAIRequest<CEW301RSvcRqType> {

    private static final long serialVersionUID = 3469898616860329394L;

    /**
     * 建構子
     */
    public CEW301RRQ() {
        super("CEW301R");
    }

}