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
import com.tfb.aibank.integration.eai.annotation.Converter;

import tw.com.ibm.mf.eb.TMLN003SvcRqType;

//@formatter:off
/**
* @(#)TMLN003RQ.java
* 
* <p>Description:房信貸留資</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/17
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
)
//@formatter:on
public class TMLN003RQ extends EAIRequest<TMLN003SvcRqType> {

    private static final long serialVersionUID = 3002651133177112438L;

    /**
     * 建構子
     */
    public TMLN003RQ() {
        super("TMLN003");
    }
}
