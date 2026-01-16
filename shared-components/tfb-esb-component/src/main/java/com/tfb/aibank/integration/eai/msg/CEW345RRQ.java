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

import tw.com.ibm.mf.eb.CEW345RSvcRqType;

//@formatter:off
/**
* @(#)CEW345RRQ.java
* 
* <p>Description:保費權益設定-查詢 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/10, LEIPING
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW345RRQ extends EAIRequest<CEW345RSvcRqType> {

    private static final long serialVersionUID = -7581350443750586351L;

    /**
     * 建構子
     */
    public CEW345RRQ() {
        super("CEW345R");
    }
}
