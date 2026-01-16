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

import tw.com.ibm.mf.eb.EB141153SvcRqType;

// @formatter:off
/**
* @(#)EB141153RQ.java
* 
* <p>Description: 定存到期預約設定╱變更上送電文(EB141153)</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, Leiley
* <ol>
*  <li>初版</li>
* </ol>
*/
public class EB141153RQ extends EAIRequest<EB141153SvcRqType> {

	private static final long serialVersionUID = -3974269437255202229L;

    /**
     * 建構子
     */
    public EB141153RQ() {
        super("EB141153");
    }
}
