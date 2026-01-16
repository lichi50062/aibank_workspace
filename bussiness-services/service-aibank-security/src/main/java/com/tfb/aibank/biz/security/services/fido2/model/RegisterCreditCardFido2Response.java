package com.tfb.aibank.biz.security.services.fido2.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// @formatter:off
/**
 * @(#)RegisterCreditCardFidoResponse.java
 * 
 * <p>Description: 信用卡/簽帳金融卡FIDO綁定註記 FIDO2 API 註冊選項 (Veri FIDO 服務的 FIDO2 註冊第一步驟) Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/14, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RegisterCreditCardFido2Response {

    /**
     * 狀態碼
     */
    private String status;

    /**
     * 錯誤訊息
     */
    private String errorMessage;

    /**
     * 時間逾時
     */
    private int timeout;

    /**
     * 回傳碼
     */
    private String returnCode;

    /**
     * Default value : "direct"
     */
    private String attestation;

    /**
     * Challenge value
     */
    private String challenge;

    /**
     * 使用者資訊
     */
    private User user;

    /**
     * 擴充資訊
     */
    private Extensions extensions;

    /**
     * aututhenticatorSelection object
     */
    private AuthenticatorSelection authenticatorSelection;

    /**
     * Rp object
     */
    private Rp rp;

    /**
     * excludeCredentials object array
     */
    private List<ExcludeCredential> excludeCredentials;

    /**
     * pubKeyCredParams object array
     */
    private List<PubKeyCredParam> pubKeyCredParams;

    /**
     * Headers object array
     */
    private List<HeaderEntry> headers;

    /**
     * 由客戶端伺服器產生的交易識別碼（UUID）
     */
    private String rpTxId;

    /**
     * Uuid.Veri FIDO transaction id.
     */
    private String txId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    public AuthenticatorSelection getAuthenticatorSelection() {
        return authenticatorSelection;
    }

    public void setAuthenticatorSelection(AuthenticatorSelection authenticatorSelection) {
        this.authenticatorSelection = authenticatorSelection;
    }

    public Rp getRp() {
        return rp;
    }

    public void setRp(Rp rp) {
        this.rp = rp;
    }

    public List<ExcludeCredential> getExcludeCredentials() {
        return excludeCredentials;
    }

    public void setExcludeCredentials(List<ExcludeCredential> excludeCredentials) {
        this.excludeCredentials = excludeCredentials;
    }

    public List<PubKeyCredParam> getPubKeyCredParams() {
        return pubKeyCredParams;
    }

    public void setPubKeyCredParams(List<PubKeyCredParam> pubKeyCredParams) {
        this.pubKeyCredParams = pubKeyCredParams;
    }

    public List<HeaderEntry> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderEntry> headers) {
        this.headers = headers;
    }

    public String getRpTxId() {
        return rpTxId;
    }

    public void setRpTxId(String rpTxId) {
        this.rpTxId = rpTxId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public static class User {
        private String displayName;
        private String name;
        private String id;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Extensions {
        private String systemId;

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }
    }

    public static class AuthenticatorSelection {
        private String userVerification;
        private boolean requireResidentKey;

        public String getUserVerification() {
            return userVerification;
        }

        public void setUserVerification(String userVerification) {
            this.userVerification = userVerification;
        }

        public boolean isRequireResidentKey() {
            return requireResidentKey;
        }

        public void setRequireResidentKey(boolean requireResidentKey) {
            this.requireResidentKey = requireResidentKey;
        }
    }

    public static class Rp {
        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public class ExcludeCredential {
        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class PubKeyCredParam {
        private String type;
        private int alg;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getAlg() {
            return alg;
        }

        public void setAlg(int alg) {
            this.alg = alg;
        }
    }

    public static class HeaderEntry {
        @JsonAnySetter
        private Map<String, String> headerMap = new HashMap<>();

        public Map<String, String> getHeaderMap() {
            return headerMap;
        }

        public void setHeaderMap(Map<String, String> headerMap) {
            this.headerMap = headerMap;
        }
    }
}
