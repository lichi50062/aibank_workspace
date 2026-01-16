package com.tfb.aibank.chl.component.userdatacache.model;

// @formatter:off
/**
 * @(#)RemoveUserFidoRequest.java
 * 
 * <p>Description:${Description}</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/7/4, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RemoveUserFido2Request {

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
     * 伺服器的使用者
     */
    private String username;

    /**
     * Rp register system id. Ex : “HiTRUST3DS”
     */
    private String systemId;

    /**
     * Operator Ex: dukehung
     */
    private String operator;

    /**
     * operatorSource Ex: VeriFIDO API
     */
    private String operatorSource;

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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorSource() {
        return operatorSource;
    }

    public void setOperatorSource(String operatorSource) {
        this.operatorSource = operatorSource;
    }
}
