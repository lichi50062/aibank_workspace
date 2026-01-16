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
* @(#)NSTOT003021Rs.java
* 
* <p>Description:安控驗證共用 - 產生MOTP - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/16, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003021Rs implements RsData {

    /** 裝置暱稱 */
    private String deviceAlias;

    /** 交易代碼 */
    private String txCode;

    /** 彈跳視窗內容 */
    private String custInfo;

    /** 與MOTP綁定為相同裝置 */
    private boolean sameDevice;

    /** 手機端用戶ID 對應全景addProfile後取得之ClientID */
    private String clientId;

    /** 挑戰碼 */
    private String challenge;

    /** 有效時間(秒) */
    private int expireTime;

    /**
     * 已經驗證過
     */
    private boolean skipAuth;

    /**
     * @return the deviceAlias
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * @param deviceAlias
     *            the deviceAlias to set
     */
    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
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
     * @return the custInfo
     */
    public String getCustInfo() {
        return custInfo;
    }

    /**
     * @param custInfo
     *            the custInfo to set
     */
    public void setCustInfo(String custInfo) {
        this.custInfo = custInfo;
    }

    /**
     * @return the sameDevice
     */
    public boolean isSameDevice() {
        return sameDevice;
    }

    /**
     * @param sameDevice
     *            the sameDevice to set
     */
    public void setSameDevice(boolean sameDevice) {
        this.sameDevice = sameDevice;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * @param challenge
     *            the challenge to set
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
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
    /**
     * 
     * @return
     */
    public boolean isSkipAuth() {
        return skipAuth;
    }
    /**
     * 
     * @param skipAuth
     */
    public void setSkipAuth(boolean skipAuth) {
        this.skipAuth = skipAuth;
    }

}
