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
package com.tfb.aibank.chl.general.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)WS00002RQ.java
 * 
 * <p>Description:保單資料 RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/06, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class WS00002Request implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 要保人身分證字號 */
    private String ownerId;

    /** 要保人出生年月日 */
    private String ownerBirth;

    private String sessionId;
    private String custId;
    private String realIp;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerBirth() {
        return ownerBirth;
    }

    public void setOwnerBirth(String ownerBirth) {
        this.ownerBirth = ownerBirth;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRealIp() {
        return realIp;
    }

    public void setRealIp(String realIp) {
        this.realIp = realIp;
    }
}
