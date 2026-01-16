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

import tw.com.ibm.mf.eb.NMWEB06SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB06RQ.java
 * 
 * <p>金錢信託-特金股票明細查詢(NMWEB06)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB06RQ extends EAIRequest<NMWEB06SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB06RQ() {
        super("NMWEB06");
    }
}
