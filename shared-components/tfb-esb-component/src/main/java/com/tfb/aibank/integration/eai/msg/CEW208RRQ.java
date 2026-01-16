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

import tw.com.ibm.mf.eb.CEW208RSvcRqType;

//@formatter:off
/**
* @(#)CEW208RRQ.java
* 
* <p>Description:CEW208R(信用卡開卡)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW208RRQ extends EAIRequest<CEW208RSvcRqType> {

    private static final long serialVersionUID = 3852976631589476587L;

    /**
     * 建構子
     */
    public CEW208RRQ() {
        super("CEW208R");
    }

}
