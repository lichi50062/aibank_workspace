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
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)GetSsoSettingRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/29, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GetSsoSettingRequest {

    /** 目標平台鍵值 */
    @Schema(description = "目標平台鍵值")
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
