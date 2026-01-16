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
package com.tfb.aibank.biz.security.services.motp.helper.model;

import com.tfb.aibank.biz.security.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpDeviceInfoEntity;
import com.tfb.aibank.common.type.MotpAuthVerifyType;

// @formatter:off
/**
 * @(#)PreAuthCheckResult.java
 * 
 * <p>Description:MOTP安控前置檢查</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PreAuthCheckResult {

    /** MOTP裝置綁定資訊 */
    private MotpDeviceInfoEntity bindingDeviceInfo;

    /** 裝置設定檔 */
    private MbDeviceInfoEntity bindingMbDeviceInfo;

    /** 使用MOTP驗證前檢查狀態類型 */
    private MotpAuthVerifyType motpAuthVerifyType = MotpAuthVerifyType.UNKNOWN;

    /**
     * @return the bindingDeviceInfo
     */
    public MotpDeviceInfoEntity getBindingDeviceInfo() {
        return bindingDeviceInfo;
    }

    /**
     * @param bindingDeviceInfo
     *            the bindingDeviceInfo to set
     */
    public void setBindingDeviceInfo(MotpDeviceInfoEntity bindingDeviceInfo) {
        this.bindingDeviceInfo = bindingDeviceInfo;
    }

    /**
     * @return the bindingMbDeviceInfo
     */
    public MbDeviceInfoEntity getBindingMbDeviceInfo() {
        return bindingMbDeviceInfo;
    }

    /**
     * @param bindingMbDeviceInfo
     *            the bindingMbDeviceInfo to set
     */
    public void setBindingMbDeviceInfo(MbDeviceInfoEntity bindingMbDeviceInfo) {
        this.bindingMbDeviceInfo = bindingMbDeviceInfo;
    }

    /**
     * @return the motpAuthVerifyType
     */
    public MotpAuthVerifyType getMotpAuthVerifyType() {
        return motpAuthVerifyType;
    }

    /**
     * @param motpAuthVerifyType
     *            the motpAuthVerifyType to set
     */
    public void setMotpAuthVerifyType(MotpAuthVerifyType motpAuthVerifyType) {
        this.motpAuthVerifyType = motpAuthVerifyType;
    }

}
