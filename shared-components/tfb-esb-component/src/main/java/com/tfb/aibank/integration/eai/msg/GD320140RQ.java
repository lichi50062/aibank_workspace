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

import tw.com.ibm.mf.eb.GD320140SvcRqType;

// @formatter:off
/**
 * @(#)GD320140RQ.java
 * 
 * <p>Description:GD320140 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GD320140RQ extends EAIRequest<GD320140SvcRqType> {

    private static final long serialVersionUID = 8647551546002210735L;

    /**
     * 建構子
     */
    public GD320140RQ() {
        super("GD320140");
        this.getHeader().setHDRVQ1("GDWEBHQ");
    }
}
