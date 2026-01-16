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

import tw.com.ibm.mf.eb.CEW231RSvcRqType;

//@formatter:off
/**
* @(#)CEW231RRQ.java
* 
* <p>Description:近六個月已繳金額明細上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/10, Rick
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW231RRQ extends EAIRequest<CEW231RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW231RRQ() {
        super("CEW231R");
    }
}
