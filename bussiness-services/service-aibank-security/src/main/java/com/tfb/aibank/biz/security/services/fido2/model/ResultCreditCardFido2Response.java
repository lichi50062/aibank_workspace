package com.tfb.aibank.biz.security.services.fido2.model;

// @formatter:off
/**
 * @(#)ResultCreditCardFidoResponse.java
 * 
 * <p>Description: 信用卡/簽帳金融卡FIDO綁定註記 FIDO2 API 註冊結果 (Veri FIDO 服務的 FIDO2 註冊第二步驟) Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/14, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class ResultCreditCardFido2Response {

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
     * 使用者名稱
     */
    private String userName;

    /**
     * 用戶端使用者的 displayName。
     */
    private String displayName;

    /**
     * Rp register system id. Ex : “HiTRUST3DS”
     */
    private String systemId;

    /**
     * Device type. Ex: ASUS or Iphone XR 填寫 DEVICE_MODEL 對應的 MARKETING_NAME
     */
    private String deviceType;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
