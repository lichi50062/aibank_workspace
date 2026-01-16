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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)EBCC002Response.java
 * 
 * <p>Description:信用卡額度申請(永調)啟案下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EBCC002Response implements Serializable {

    private static final long serialVersionUID = -3430025676669237034L;

    /** 線上申請編號 */
    private String applyno;

    public String getApplyno() {
        return applyno;
    }

    public void setApplyno(String applyno) {
        this.applyno = applyno;
    }

}
