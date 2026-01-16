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

import tw.com.ibm.mf.eb.CEW327RSvcRqType;

//@formatter:off
/**
* @(#)CEW327RRQ.java
* 
* <p>Description:保費權益設定-查詢 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW327RRQ extends EAIRequest<CEW327RSvcRqType> {

    private static final long serialVersionUID = -7581350443750586351L;

    /**
     * 建構子
     */
    public CEW327RRQ() {
        super("CEW327R");
    }
}
