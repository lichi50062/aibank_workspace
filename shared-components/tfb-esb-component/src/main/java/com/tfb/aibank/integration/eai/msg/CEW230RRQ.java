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

import tw.com.ibm.mf.eb.CEW230RSvcRqType;

//@formatter:off
/**
* @(#)CEW230RRQ.java
* 
* <p>Description:累計已繳金額明細上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW230RRQ extends EAIRequest<CEW230RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW230RRQ() {
        super("CEW230R");
    }
}
