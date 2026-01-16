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

import tw.com.ibm.mf.eb.CEW1410RSvcRqType;

//@formatter:off
/**
* @(#)CEW1410RRQ.java
* 
* <p>Description:CEW1410R(信用卡掛失)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW1410RRQ extends EAIRequest<CEW1410RSvcRqType> {

    private static final long serialVersionUID = -7130505413048278262L;

    /**
     * 建構子
     */
    public CEW1410RRQ() {
        super("CEW1410R");
    }

}
