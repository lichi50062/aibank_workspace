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

import com.tfb.aibank.chl.component.devicebinding.type.UserDeviceBindStatusType;

//@formatter:off
/**
* @(#)CheckUserDeviceStatusResult.java
* 
* <p>Description:檢查使用者與裝置綁定狀態 - Result</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/19, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CheckUserDeviceStatusResult {

    /** 狀態 */
    private UserDeviceBindStatusType status;

    /** 使用者綁定資料 */
    private RetrieveUserBindingResponse userBinding;

    /** 裝置綁定資料 */
    private RetrieveDeviceBindingResponse deviceBinding;

    /**
     * @return the status
     */
    public UserDeviceBindStatusType getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(UserDeviceBindStatusType status) {
        this.status = status;
    }

    /**
     * @return the userBinding
     */
    public RetrieveUserBindingResponse getUserBinding() {
        return userBinding;
    }

    /**
     * @param userBinding
     *            the userBinding to set
     */
    public void setUserBinding(RetrieveUserBindingResponse userBinding) {
        this.userBinding = userBinding;
    }

    /**
     * @return the deviceBinding
     */
    public RetrieveDeviceBindingResponse getDeviceBinding() {
        return deviceBinding;
    }

    /**
     * @param deviceBinding
     *            the deviceBinding to set
     */
    public void setDeviceBinding(RetrieveDeviceBindingResponse deviceBinding) {
        this.deviceBinding = deviceBinding;
    }

}
