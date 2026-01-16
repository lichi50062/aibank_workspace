package com.tfb.aibank.chl.component.userdatacache.model;

// @formatter:off
/**
 * @(#)QueryUserInfoFidoRequest.java
 * 
 * <p>Description:${Description}</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/7/24, billchang
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
}
