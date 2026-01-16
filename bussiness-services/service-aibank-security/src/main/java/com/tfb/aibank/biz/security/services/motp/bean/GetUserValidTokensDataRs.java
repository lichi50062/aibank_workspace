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
package com.tfb.aibank.biz.security.services.motp.bean;

// @formatter:off
/**
 * @(#)GetUserValidTokensRs.java
 * 
 * <p>Description:全景MOTP - API介接服務 - GetUserValidTokens RS data</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GetUserValidTokensDataRs {

    /** 載具序號 */
    private String serials;

    /**
     * @return the serials
     */
    public String getSerials() {
        return serials;
    }

    /**
     * @param serials
     *            the serials to set
     */
    public void setSerials(String serials) {
        this.serials = serials;
    }

}
