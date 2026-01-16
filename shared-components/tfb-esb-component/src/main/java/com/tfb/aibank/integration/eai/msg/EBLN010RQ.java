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

import tw.com.ibm.mf.eb.EBLN010SvcRqType;

//@formatter:off
/**
* @(#)EBLN010RQ.java
* 
* <p>Description:檢核是否符合速貸通資格 上行電文</p>
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
public class EBLN010RQ extends EAIRequest<EBLN010SvcRqType> {

    private static final long serialVersionUID = 3002651133177112438L;

    /**
     * 建構子
     */
    public EBLN010RQ() {
        super("EBLN010");
    }
}
