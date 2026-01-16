/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
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
     * @param hostSecret the hostSecret to set
     */
    public void setHostSecret(String hostSecret) {
        this.hostSecret = hostSecret;
    }
    
    
}
