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

import tw.com.ibm.mf.eb.EBEAGLEEYESvcRqType;

// @formatter:off
/**
 * @(#)EBEAGLEEYERQ.java
 * 
 * <p>Description:分期防詐名單收集及檢核 上行電文(EBEAGLEEYE)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/12, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EBEAGLEEYERQ extends EAIRequest<EBEAGLEEYESvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EBEAGLEEYERQ() {
        super("EBEAGLEEYE");
    }

}
