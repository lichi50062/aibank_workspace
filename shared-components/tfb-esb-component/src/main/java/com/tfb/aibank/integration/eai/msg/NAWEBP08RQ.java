/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NAWEBP08SvcRqType;

//@formatter:off
/**
* @(#)NAWEBP08RQ.java
* 
* <p>Description: 持股現金領回明細查詢 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/27, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NAWEBP08RQ extends EAIRequest<NAWEBP08SvcRqType> {

	/**
     * 建構子
     */
    public NAWEBP08RQ() {
        super("NAWEBP08");
    }
    
}
