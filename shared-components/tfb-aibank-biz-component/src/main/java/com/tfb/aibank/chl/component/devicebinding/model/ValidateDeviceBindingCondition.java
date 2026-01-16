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

// @formatter:off
/**
 * @(#)ValidateDeviceBindingCondition.java
 * 
 * <p>Description:需裝置綁定交易檢核</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ValidateDeviceBindingCondition {

    /** 使用者 */
    private AIBankUser loginUser;

    /** 檢查使用者與裝置綁定狀態結果 */
    private CheckUserDeviceStatusResult checkUserDeviceStatusResult;

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
     * @return the checkUserDeviceStatusResult
     */
    public CheckUserDeviceStatusResult getCheckUserDeviceStatusResult() {
        return checkUserDeviceStatusResult;
    }

    /**
     * @param checkUserDeviceStatusResult
     *            the checkUserDeviceStatusResult to set
     */
    public void setCheckUserDeviceStatusResult(CheckUserDeviceStatusResult checkUserDeviceStatusResult) {
        this.checkUserDeviceStatusResult = checkUserDeviceStatusResult;
    }

}
