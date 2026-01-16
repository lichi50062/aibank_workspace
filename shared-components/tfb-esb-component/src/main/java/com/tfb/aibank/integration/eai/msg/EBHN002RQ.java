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

import tw.com.ibm.mf.eb.EBHN002SvcRqType;

//@formatter:off
/**
* @(#)EBHN002RQ.java
* 
* <p>Description:房貸可增貸額度 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12  John
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
)
//@formatter:on
public class EBHN002RQ extends EAIRequest<EBHN002SvcRqType> {

    private static final long serialVersionUID = 3002651133177112438L;

    /**
     * 建構子
     */
    public EBHN002RQ() {
        super("EBHN002");
    }
}
