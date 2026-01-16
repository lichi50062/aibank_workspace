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
 * @(#)SendOfflineOtpResult.java
 * 
 * <p>Description:發送離線載具OTP認證</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SendOfflineOtpResult {

    /** 是否成功 */
    private boolean isSuccess;

    /** 有效時間(秒) */
    private int expireTime;

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
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
