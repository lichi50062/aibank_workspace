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
package com.tfb.aibank.biz.component.datacenter.model;

import com.google.gson.JsonObject;

// @formatter:off
/**
 * @(#)UserTagResult.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UserTagResult {
    /**
     * 需回傳前端之客戶標籤 {" T01-01":"lazy", " T01-02":"cons"}
     */
    private JsonObject userTags;
    /** 接收回覆時間 */
    private Long timestamp;


    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the userTags
     */
    public JsonObject getUserTags() {
        return userTags;
    }

    /**
     * @param userTags
     *            the userTags to set
     */
    public void setUserTags(JsonObject userTags) {
        this.userTags = userTags;
    }


}
