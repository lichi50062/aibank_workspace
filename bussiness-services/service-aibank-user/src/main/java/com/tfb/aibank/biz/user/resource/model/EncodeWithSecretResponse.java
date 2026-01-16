package com.tfb.aibank.biz.user.resource.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EncodeWithSecretResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "EncodeWithSecretResponse")
public class EncodeWithSecretResponse {

    @Schema(description = "主機密碼")
    private String hostSecret;

    /**
     * @return the hostSecret
     */
    public String getHostSecret() {
        return hostSecret;
    }

    /**
     * @param hostSecret
     *            the hostSecret to set
     */
    public void setHostSecret(String hostSecret) {
        this.hostSecret = hostSecret;
    }

}
