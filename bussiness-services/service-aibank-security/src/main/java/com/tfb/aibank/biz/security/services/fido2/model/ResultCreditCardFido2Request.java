package com.tfb.aibank.biz.security.services.fido2.model;

// @formatter:off
/**
 * @(#)ResultCreditCardFidoRequest.java
 * 
 * <p>Description: 信用卡/簽帳金融卡FIDO綁定註記 FIDO2 API 註冊結果 (Veri FIDO 服務的 FIDO2 註冊第二步驟) Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/14, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class ResultCreditCardFido2Request {

    /**
     * 伺服器的網域名稱
     */
    private String rpId;

    /**
     * 由客戶端伺服器產生的交易識別碼（UUID）
     */
    private String rpTxId;

    /**
     * SPC 交易註記
     */
    private Boolean isPaymentFromRp;

    /**
     * 伺服器的使用者(可和 rpUsername 一致) 使用者唯一的識別碼
     */
    private String rpUsername;

    /**
     * DiiA 序號 (選填)
     */
    private String dsn;

    /**
     * UUID
     */
    private String txId;

    /**
     * 如果這筆交易是針對 SPC，則 isPayment 為必要欄位，其值為 [true]。
     */
    private Boolean isPayment;

    /**
     * 伺服器的使用者
     */
    private String username;

    /**
     * 用戶端使用者的 displayName。
     */
    private String displayName;

    private String id;

    /**
     * Rp register system id. Ex : “HiTRUST3DS”
     */
    private String systemId;

    private Response response;

    private String type;

    private Boolean fido2auth;

    private Boolean fido2authPwd;

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

    public Boolean getPaymentFromRp() {
        return isPaymentFromRp;
    }

    public void setPaymentFromRp(Boolean paymentFromRp) {
        isPaymentFromRp = paymentFromRp;
    }

    public String getRpUsername() {
        return rpUsername;
    }

    public void setRpUsername(String rpUsername) {
        this.rpUsername = rpUsername;
    }

    public String getDsn() {
        return dsn;
    }

    public void setDsn(String dsn) {
        this.dsn = dsn;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Boolean getPayment() {
        return isPayment;
    }

    public void setPayment(Boolean payment) {
        isPayment = payment;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getFido2auth() {
        return fido2auth;
    }

    public void setFido2auth(Boolean fido2auth) {
        this.fido2auth = fido2auth;
    }

    public Boolean getFido2authPwd() {
        return fido2authPwd;
    }

    public void setFido2authPwd(Boolean fido2authPwd) {
        this.fido2authPwd = fido2authPwd;
    }

    public static class Response {

        private String clientDataJSON;

        private String attestationObject;

        private String authenticatorData;

        private String signature;

        public String getClientDataJSON() {
            return clientDataJSON;
        }

        public void setClientDataJSON(String clientDataJSON) {
            this.clientDataJSON = clientDataJSON;
        }

        public String getAttestationObject() {
            return attestationObject;
        }

        public void setAttestationObject(String attestationObject) {
            this.attestationObject = attestationObject;
        }

        public String getAuthenticatorData() {
            return authenticatorData;
        }

        public void setAuthenticatorData(String authenticatorData) {
            this.authenticatorData = authenticatorData;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}
