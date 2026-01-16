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

import com.tfb.aibank.biz.security.services.motp.type.MotpSendResult;

// @formatter:off
/**
 * @(#)SendPushOtpResult.java
 * 
 * <p>Description:發送推播OTP認證</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SendPushOtpResult {

    /** MOTP發送結果 */
    private MotpSendResult sendResult;

    /** 有效時間(秒) */
    private int expireTime;

    /**
     * @return the sendResult
     */
    public MotpSendResult getSendResult() {
        return sendResult;
    }

    /**
     * @param sendResult
     *            the sendResult to set
     */
    public void setSendResult(MotpSendResult sendResult) {
        this.sendResult = sendResult;
    }

    /**
     * @return the expireTime
     */
    public int getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

}
