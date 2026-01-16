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
package com.tfb.aibank.chl.component.devicebinding.model;

import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)CheckUserDeviceStatusCondition.java
* 
* <p>Description:檢查使用者與裝置綁定狀態 - Condition</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/19, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CheckUserDeviceStatusCondition {

    /** 使用者 */
    private AIBankUser loginUser;

    /** 裝置ID */
    private String deviceIxd;

    /** 語系 */
    private String locale;

    /**
     * @return the loginUser
     */
    public AIBankUser getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser
     *            the loginUser to set
     */
    public void setLoginUser(AIBankUser loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * @return the deviceIxd
     */
    public String getDeviceIxd() {
        return deviceIxd;
    }

    /**
     * @param deviceIxd
     *            the deviceIxd to set
     */
    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

}
