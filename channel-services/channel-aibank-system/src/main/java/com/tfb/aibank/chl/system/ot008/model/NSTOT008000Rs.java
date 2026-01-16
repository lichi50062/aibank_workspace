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
package com.tfb.aibank.chl.system.ot008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NSTOT008000Rs.java
 * 
 * <p>Description:裝置綁定檢核頁面初始初始</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT008000Rs implements RsData {

    /** 使用者已綁定目前使用裝置，可繼續執行 */
    private boolean isValid;

    /** 是否執行變更綁定流程 */
    private boolean changeDevice;

    /** 綁定狀態 */
    private boolean isAlreadyBind;

    /**
     * 授權狀態
     */
    private boolean isAlreadyAuth;

    /**
     * 授權狀態
     */
    private boolean isNeedAuth;

    /**
     * 需要OTP
     */
    private boolean isNeedOtp;

    /** motp 綁定狀態 */
    private boolean motpFlag;

    /**
     * @return the isValid
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * @param isValid
     *            the isValid to set
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * @return the changeDevice
     */
    public boolean isChangeDevice() {
        return changeDevice;
    }

    /**
     * @param changeDevice
     *            the changeDevice to set
     */
    public void setChangeDevice(boolean changeDevice) {
        this.changeDevice = changeDevice;
    }

    /**
     * @return the isAlreadyAuth
     */
    public boolean isAlreadyAuth() {
        return isAlreadyAuth;
    }

    /**
     * @param isAlreadyAuth
     *            the isAlreadyAuth to set
     */
    public void setAlreadyAuth(boolean isAlreadyAuth) {
        this.isAlreadyAuth = isAlreadyAuth;
    }

    /**
     * @return the isNeedOtp
     */
    public boolean isNeedOtp() {
        return isNeedOtp;
    }

    /**
     * @param isNeedOtp
     *            the isNeedOtp to set
     */
    public void setNeedOtp(boolean isNeedOtp) {
        this.isNeedOtp = isNeedOtp;
    }

    /**
     * @return the isAlreadyBind
     */
    public boolean isAlreadyBind() {
        return isAlreadyBind;
    }

    /**
     * @param isAlreadyBind
     *            the isAlreadyBind to set
     */
    public void setAlreadyBind(boolean isAlreadyBind) {
        this.isAlreadyBind = isAlreadyBind;
    }

    /**
     * @return the isNeedAuth
     */
    public boolean isNeedAuth() {
        return isNeedAuth;
    }

    /**
     * @param isNeedAuth
     *            the isNeedAuth to set
     */
    public void setNeedAuth(boolean isNeedAuth) {
        this.isNeedAuth = isNeedAuth;
    }

    /**
     * @return the motpFlag
     */
    public boolean isMotpFlag() {
        return motpFlag;
    }

    /**
     * @param motpFlag
     *            the motpFlag to set
     */
    public void setMotpFlag(boolean motpFlag) {
        this.motpFlag = motpFlag;
    }

}
