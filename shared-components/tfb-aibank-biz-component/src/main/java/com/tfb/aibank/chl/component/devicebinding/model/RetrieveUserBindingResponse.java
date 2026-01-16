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

//@formatter:off
/**
* @(#)RetrieveUserBindingResponse.java
* 
* <p>Description:取得使用者綁定資料 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RetrieveUserBindingResponse {

    /** 裝置綁定狀態 */
    private boolean isBind;

    /** 行動裝置設定 */
    private MbDeviceInfo model;

    /** 裝置型號 */
    private String deviceModel;

    /**
     * @return the isBind
     */
    public boolean isBind() {
        return isBind;
    }

    /**
     * @param isBind
     *            the isBind to set
     */
    public void setBind(boolean isBind) {
        this.isBind = isBind;
    }

    /**
     * @return the model
     */
    public MbDeviceInfo getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(MbDeviceInfo model) {
        this.model = model;
    }

    /**
     * @return the deviceModel
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     *            the deviceModel to set
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

}
