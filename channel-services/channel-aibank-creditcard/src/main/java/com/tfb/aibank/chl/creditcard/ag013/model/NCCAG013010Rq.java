package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013010Rq.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013010Rq implements RqData {

    /**
     * 系統裝置名稱
     */
    private String device;

    /**
     * 系統裝置版本
     */
    private Integer deviceVersion;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Integer getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(Integer deviceVersion) {
        this.deviceVersion = deviceVersion;
    }
}
