/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB555665SvcRqType;

//@formatter:off
/**
* @(#)EB555665RQ.java
* 
* <p>Description:EB555665 預約轉帳結果查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/20, Evan 
* <ol>
*  <li>初版</li>
* </ol>
*/

//@formatter:on
public class EB555665RQ extends EAIRequest<EB555665SvcRqType> {

    /**
     * 建構子
     */
    public EB555665RQ() {
        super("EB555665");
    }

}