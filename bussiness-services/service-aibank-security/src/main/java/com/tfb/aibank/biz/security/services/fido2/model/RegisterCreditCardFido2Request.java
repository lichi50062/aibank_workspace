package com.tfb.aibank.biz.security.services.fido2.model;

// @formatter:off
/**
 * @(#)RegisterCreditCardFidoRequest.java
 * 
 * <p>Description: 信用卡/簽帳金融卡FIDO綁定註記 FIDO2 API 註冊選項 (Veri FIDO 服務的 FIDO2 註冊第一步驟) Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/14, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RegisterCreditCardFido2Request {

    /**
     * 伺服器的網域名稱
     */
    private String rpId;

    /**
     * 由客戶端伺服器產生的交易識別碼（UUID）
     */
    private String rpTxId;

    /**
     * 伺服器的使用者(可和 rpUsername 一致) 使用者唯一的識別碼
     */
    private String rpUsername;

    /**
     * 伺服器的使用者(可和 rpUsername 一致) 使用者唯一的識別碼
     */
    private String username;

    /**
     * SPC 交易註記
     */
    private boolean isPaymentFromRp;

    /**
     * DiiA 序號 (選填)
     */
    private String dsn;

    /**
     * 用戶端使用者的 displayName。
     */
    private String displayName;

    /**
     * 註冊型態;  預設數值 : 「direct」
     */
    private String attestation;

    /**
     * 延伸物件
     */
    private Extensions extensions;

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    public String getRpTxId() {
        return rpTxId;
    }

    public void setRpTxId(String rpTxId) {
        this.rpTxId = rpTxId;
    }

    public String getRpUsername() {
        return rpUsername;
    }

    public void setRpUsername(String rpUsername) {
        this.rpUsername = rpUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isPaymentFromRp() {
        return isPaymentFromRp;
    }

    public void setPaymentFromRp(boolean paymentFromRp) {
        isPaymentFromRp = paymentFromRp;
    }

    public String getDsn() {
        return dsn;
    }

    public void setDsn(String dsn) {
        this.dsn = dsn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    public static class Extensions {

        /**
         * Device type. Ex: ASUS or Iphone XR 填寫 DEVICE_MODEL 對應的 MARKETING_NAME
         */
        private String deviceType;

        /**
         * Rp register system id. Ex : “HiTRUST3DS”
         */
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
