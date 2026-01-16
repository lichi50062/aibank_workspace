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
package com.tfb.aibank.chl.system.ot010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT010010Rq.java
 * 
 * <p>Description:SSO 登入驗證機制 - 產生 Token</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT010010Rq implements RqData {

    /** 是否執行變更綁定流程 */
    private String channelKey;

    /**
     * @return the channelKey
     */
    public String getChannelKey() {
        return channelKey;
    }

    /**
     * @param channelKey
     *            the channelKey to set
     */
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

}
