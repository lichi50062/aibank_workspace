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

import tw.com.ibm.mf.eb.CEW302RSvcRqType;

//@formatter:off
/**
* @(#)CEW302RRQ.java
* 
* <p>Description:信用卡戶況查詢上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/04, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW302RRQ extends EAIRequest<CEW302RSvcRqType> {

    private static final long serialVersionUID = 3469898616860329394L;

    /**
     * 建構子
     */
    public CEW302RRQ() {
        super("CEW302R");
    }

}