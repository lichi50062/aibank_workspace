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
package com.tfb.aibank.biz.security.services.motp.helper.model;

// @formatter:off
/**
 * @(#)FindMotpTransDataCondition.java
 * 
 * <p>Description:取得交易認證資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FindMotpTransDataCondition {

    /** 交易認證資訊鍵值 */
    private Integer motpTransDataKey;

    /** 交易驗證載具資訊鍵值 */
    private Integer motpVerifyCarrierKey;

    /**
     * @return the motpTransDataKey
     */
    public Integer getMotpTransDataKey() {
        return motpTransDataKey;
    }

    /**
     * @param motpTransDataKey
     *            the motpTransDataKey to set
     */
    public void setMotpTransDataKey(Integer motpTransDataKey) {
        this.motpTransDataKey = motpTransDataKey;
    }

    /**
     * @return the motpVerifyCarrierKey
     */
    public Integer getMotpVerifyCarrierKey() {
        return motpVerifyCarrierKey;
    }

    /**
     * @param motpVerifyCarrierKey
     *            the motpVerifyCarrierKey to set
     */
    public void setMotpVerifyCarrierKey(Integer motpVerifyCarrierKey) {
        this.motpVerifyCarrierKey = motpVerifyCarrierKey;
    }

}
