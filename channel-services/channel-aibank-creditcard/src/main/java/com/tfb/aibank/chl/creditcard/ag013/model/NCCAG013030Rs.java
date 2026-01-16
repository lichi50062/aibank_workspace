package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013030Rs.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 030 生物辨識設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013030Rs implements RsData {

    private String serverUrl;

    private String systemId;

    private String username;

    private String displayName;

    private Extensions extensions;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    public static class Extensions {

        private String deviceType;

        private String systemId;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }
    }
}
