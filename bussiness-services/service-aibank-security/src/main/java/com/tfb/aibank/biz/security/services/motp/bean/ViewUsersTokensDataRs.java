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
 * @(#)ViewUsersTokensRs.java
 * 
 * <p>Description:全景MOTP - API介接服務 - ViewUsersTokens RS data</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ViewUsersTokensDataRs {

    /** 載具資料 */
    private String tokens;

    /**
     * @return the tokens
     */
    public String getTokens() {
        return tokens;
    }

    /**
     * @param tokens
     *            the tokens to set
     */
    public void setTokens(String tokens) {
        this.tokens = tokens;
    }

}
