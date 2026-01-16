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

import tw.com.ibm.mf.eb.CEW424RSvcRqType;

//@formatter:off
/**
* @(#)CEW424RRQ.java
* 
* <p>Description:卡片暱稱維護查詢交易</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW424RRQ extends EAIRequest<CEW424RSvcRqType> {

    private static final long serialVersionUID = 6506703952138286392L;

    /**
     * 建構子
     */
    public CEW424RRQ() {
        super("CEW424R");
    }

}
