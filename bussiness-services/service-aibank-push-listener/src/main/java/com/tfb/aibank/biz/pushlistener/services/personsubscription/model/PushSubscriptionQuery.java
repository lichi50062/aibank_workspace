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
package com.tfb.aibank.biz.pushlistener.services.personsubscription.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// @formatter:off
/**
 * @(#)PushSubscriptionQuery.java
 * 
 * <p>Description:推播訂閱狀態查詢條件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/11/28 Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushSubscriptionQuery {
    /**
     * custIxd
     */
    private String custIxd;

    /**
     * push code
     */
    private String pushCode;

    /**
     * 加密的 custIxd
     */
    private String encryptedCustIxd;

    /**
     * 誤別碼
     */
    private String uidDup;

    public PushSubscriptionQuery(String custIxd, String pushCode) {
        this.custIxd = custIxd;
        this.pushCode = pushCode;
    }

    public String getCustIxd() {
        return custIxd;
    }

    public void setCustIxd(String custId) {
        this.custIxd = custId;
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    public String getEncryptedCustIxd() {
        return encryptedCustIxd;
    }

    public void setEncryptedCustIxd(String encryptedCustIxd) {
        this.encryptedCustIxd = encryptedCustIxd;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

}
