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

// @formatter:off
/**
 * @(#)ValidateDeviceBindingResult.java
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
public class ValidateDeviceBindingResult {

    /** 使用者已綁定目前使用裝置，可繼續執行 */
    private boolean isValid;

    /** 是否執行變更綁定流程 */
    private boolean changeDevice;

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

}
