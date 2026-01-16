package com.tfb.aibank.chl.creditcard.ag003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG003012Rs.java
 * 
 * <p>Description:信用卡 設定 012 action</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG003012Rs implements RsData {
    private String email;

    private String txCode;

    private String userEncTxCode;

    private String userTxHash;

    private int countdownSeconds;

    private boolean canResend;

    private int resendCountdownSeconds;

    private String expiryTime;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
