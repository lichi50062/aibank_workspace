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
package com.tfb.aibank.chl.creditcard.tx001.model;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;

// @formatter:off
/**
 * @(#)NCCTX001Output.java
 * 
 * <p>Description:輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX001Output {

    /** 電文CEW303R 下行 */
    private CEW303RResponse cew303RRes;

    public CEW303RResponse getCew303RRes() {
        return cew303RRes;
    }

    public void setCew303RRes(CEW303RResponse cew303rRes) {
        cew303RRes = cew303rRes;
    }

}
