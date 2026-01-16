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

import tw.com.ibm.mf.eb.CEW315RSvcRqType;

//@formatter:off
/**
* @(#)CEW315RRQ.java
* 
* <p>Description:信用卡帳單分期查詢上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW315RRQ extends EAIRequest<CEW315RSvcRqType> {

    private static final long serialVersionUID = 214893669947849098L;

    /**
     * 建構子
     */
    public CEW315RRQ() {
        super("CEW315R");
    }
}
