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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)UpdateUserDeviceBindingRequest.java
* 
* <p>Description:更新使用者裝置綁定資料 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateUserDeviceBindingRequest {

    /** 行動裝置UUID */
    private String deviceUuid;

    /** 是否更新免登速查註記 */
    private boolean isUpdateQsearchFlag;

    /** 免登速查註記 */
    private int qsearchFlag;

    /** 是否更新快速登入註記 */
    private boolean isUpdateLoginFlag;

    /** 是否授權簡易登入 */
    private int loginFlag;

    /** 快速登入密碼類型 */
    private int loginPasswdType;

    /** 簡易登入授權日期 */
    private Date loginAuthDate;

    /** 是否更新訊息通知註記 */
    private boolean isUpdateNotifyFlag;

    /** 是否授權訊息通知 */
    private int notifyFlag;

    /** 訊息通知授權日期 */
    private Date notifyAuthDate;

    /** 是否更新錢包註記 */
    private boolean isUpdateWalletFlag;

    /** 錢包註記 */
    private int walletFlag;

    /**
     * @return the deviceUuid
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     *            the deviceUuid to set
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * @return the isUpdateQsearchFlag
     */
    public boolean isUpdateQsearchFlag() {
        return isUpdateQsearchFlag;
    }

    /**
     * @param isUpdateQsearchFlag
     *            the isUpdateQsearchFlag to set
     */
    public void setUpdateQsearchFlag(boolean isUpdateQsearchFlag) {
        this.isUpdateQsearchFlag = isUpdateQsearchFlag;
    }

    /**
     * @return the qsearchFlag
     */
    public int getQsearchFlag() {
        return qsearchFlag;
    }

    /**
     * @param qsearchFlag
     *            the qsearchFlag to set
     */
    public void setQsearchFlag(int qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }

    /**
     * @return the isUpdateLoginFlag
     */
    public boolean isUpdateLoginFlag() {
        return isUpdateLoginFlag;
    }

    /**
     * @param isUpdateLoginFlag
     *            the isUpdateLoginFlag to set
     */
    public void setUpdateLoginFlag(boolean isUpdateLoginFlag) {
        this.isUpdateLoginFlag = isUpdateLoginFlag;
    }

    /**
     * @return the loginFlag
     */
    public int getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     *            the loginFlag to set
     */
    public void setLoginFlag(int loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return the loginPasswdType
     */
    public int getLoginPasswdType() {
        return loginPasswdType;
    }

    /**
     * @param loginPasswdType
     *            the loginPasswdType to set
     */
    public void setLoginPasswdType(int loginPasswdType) {
        this.loginPasswdType = loginPasswdType;
    }

    /**
     * @return the loginAuthDate
     */
    public Date getLoginAuthDate() {
        return loginAuthDate;
    }

    /**
     * @param loginAuthDate
     *            the loginAuthDate to set
     */
    public void setLoginAuthDate(Date loginAuthDate) {
        this.loginAuthDate = loginAuthDate;
    }

    /**
     * @return the isUpdateNotifyFlag
     */
    public boolean isUpdateNotifyFlag() {
        return isUpdateNotifyFlag;
    }

    /**
     * @param isUpdateNotifyFlag
     *            the isUpdateNotifyFlag to set
     */
    public void setUpdateNotifyFlag(boolean isUpdateNotifyFlag) {
        this.isUpdateNotifyFlag = isUpdateNotifyFlag;
    }

    /**
     * @return the notifyFlag
     */
    public int getNotifyFlag() {
        return notifyFlag;
    }

    /**
     * @param notifyFlag
     *            the notifyFlag to set
     */
    public void setNotifyFlag(int notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    /**
     * @return the notifyAuthDate
     */
    public Date getNotifyAuthDate() {
        return notifyAuthDate;
    }

    /**
     * @param notifyAuthDate
     *            the notifyAuthDate to set
     */
    public void setNotifyAuthDate(Date notifyAuthDate) {
        this.notifyAuthDate = notifyAuthDate;
    }

    public boolean isUpdateWalletFlag() {
        return isUpdateWalletFlag;
    }

    public void setUpdateWalletFlag(boolean updateWalletFlag) {
        isUpdateWalletFlag = updateWalletFlag;
    }

    public int getWalletFlag() {
        return walletFlag;
    }

    public void setWalletFlag(int walletFlag) {
        this.walletFlag = walletFlag;
    }
}
