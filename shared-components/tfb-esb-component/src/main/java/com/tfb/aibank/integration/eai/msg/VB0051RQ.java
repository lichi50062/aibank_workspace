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

import tw.com.ibm.mf.eb.VB0051SvcRqType;

//@formatter:off
/**
* @(#)VB0051RQ.java
* 
* <p>Description:好多金餘額查詢 VB0051 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class VB0051RQ extends EAIRequest<VB0051SvcRqType> {

    private static final long serialVersionUID = 9023211029795424411L;

    /**
     * 建構子
     */
    public VB0051RQ() {
        super("VB0051");
    }
}
