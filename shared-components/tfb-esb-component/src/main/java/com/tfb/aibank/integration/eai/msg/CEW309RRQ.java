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

import tw.com.ibm.mf.eb.CEW309RSvcRqType;

//@formatter:off
/**
* @(#)CEW309RRQ.java
* 
* <p>Description:信用卡費自動扣繳設定上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW309RRQ extends EAIRequest<CEW309RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW309RRQ() {
        super("CEW309R");
    }

}
