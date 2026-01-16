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
package com.tfb.aibank.chl.general.ot012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NGNOT012010Rq.java
 * 
 * <p>Description:共用跳頁功能 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/11, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNOT012010Rq implements RqData {

    private String param;

    /**
     * @return the param
     */
    public String getParam() {
        return param;
    }

    /**
     * @param param
     *            the param to set
     */
    public void setParam(String param) {
        this.param = param;
    }

}
