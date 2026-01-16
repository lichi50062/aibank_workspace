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

import tw.com.ibm.mf.eb.CEW321RSvcRqType;

//@formatter:off
/**
* @(#)CEW321RRQ.java
* 
* <p>Description:年度累積消費查詢上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW321RRQ extends EAIRequest<CEW321RSvcRqType> {

    private static final long serialVersionUID = -8136856315622010335L;

    /**
     * 建構子
     */
    public CEW321RRQ() {
        super("CEW321R");
    }
}
