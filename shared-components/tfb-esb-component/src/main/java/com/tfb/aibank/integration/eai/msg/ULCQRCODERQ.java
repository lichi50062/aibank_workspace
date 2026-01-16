/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.ULCQRCODESvcRqType;

// @formatter:off
/**
 * @(#)ULCQRCODERQ.java
 * 
 * <p>Description:金融卡解鎖序號上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ULCQRCODERQ extends EAIRequest<ULCQRCODESvcRqType> {

    private static final long serialVersionUID = 1275732630006554009L;

    /**
     * 建構子
     */
    public ULCQRCODERQ() {
        super("ULCQRCODE");
    }

}
