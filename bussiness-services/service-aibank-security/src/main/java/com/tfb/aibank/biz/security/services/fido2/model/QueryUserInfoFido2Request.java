package com.tfb.aibank.biz.security.services.fido2.model;

// @formatter:off
/**
 * @(#)QueryUserInfoFidoRequest.java
 * 
 * <p>Description: FIDO2 API 查詢會員狀態 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/28, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class QueryUserInfoFido2Request {

    /**
     * 伺服器的網域名稱
     */
    private String rpId;

    /**
     * 伺服器的使用者(可和 rpUsername 一致) 使用者唯一的識別碼
     */
    private String username;

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "QueryUserInfoFidoRequest{" +
                "rpId='" + rpId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
