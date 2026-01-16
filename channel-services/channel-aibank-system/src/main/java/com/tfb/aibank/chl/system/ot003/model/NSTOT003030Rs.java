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
package com.tfb.aibank.chl.system.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NSTOT003030Rs.java
* 
* <p>Description:安控驗證共用 - 重送OTP - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003030Rs implements RsData {

    private String phone;

    private String txCode;

    private String userEncTxCode;

    private String userTxHash;

    private int countdownSeconds;

    private boolean canResend;

    private int resendCountdownSeconds;

    private String expiryTime;

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the txCode
     */
    public String getTxCode() {
        return txCode;
    }

    /**
     * @param txCode
     *            the txCode to set
     */
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    /**
     * @return the userEncTxCode
     */
    public String getUserEncTxCode() {
        return userEncTxCode;
    }

    /**
     * @param userEncTxCode
     *            the userEncTxCode to set
     */
    public void setUserEncTxCode(String userEncTxCode) {
        this.userEncTxCode = userEncTxCode;
    }

    /**
     * @return the userTxHash
     */
    public String getUserTxHash() {
        return userTxHash;
    }

    /**
     * @param userTxHash
     *            the userTxHash to set
     */
    public void setUserTxHash(String userTxHash) {
        this.userTxHash = userTxHash;
    }

    /**
     * @return the countdownSeconds
     */
    public int getCountdownSeconds() {
        return countdownSeconds;
    }

    /**
     * @param countdownSeconds
     *            the countdownSeconds to set
     */
    public void setCountdownSeconds(int countdownSeconds) {
        this.countdownSeconds = countdownSeconds;
    }

    /**
     * @return the canResend
     */
    public boolean isCanResend() {
        return canResend;
    }

    /**
     * @param canResend
     *            the canResend to set
     */
    public void setCanResend(boolean canResend) {
        this.canResend = canResend;
    }

    /**
     * @return the resendCountdownSeconds
     */
    public int getResendCountdownSeconds() {
        return resendCountdownSeconds;
    }

    /**
     * @param resendCountdownSeconds
     *            the resendCountdownSeconds to set
     */
    public void setResendCountdownSeconds(int resendCountdownSeconds) {
        this.resendCountdownSeconds = resendCountdownSeconds;
    }

    /**
     * @return the expiryTime
     */
    public String getExpiryTime() {
        return expiryTime;
    }

    /**
     * @param expiryTime
     *            the expiryTime to set
     */
    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

}
