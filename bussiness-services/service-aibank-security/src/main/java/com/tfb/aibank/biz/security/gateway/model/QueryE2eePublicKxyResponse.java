/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
@Schema(description = "QueryE2eePublicKxyResponse")
public class QueryE2eePublicKxyResponse {

    @Schema(description = "uid使用的PublicKxy")
    private String publicKeyforUid;
        
    @Schema(description = "secret使用的PublicKxy")
    private String publicKeyforSecret;

    /**
     * @return the publicKeyforUid
     */
    public String getPublicKeyforUid() {
        return publicKeyforUid;
    }

    /**
     * @param publicKeyforUid the publicKeyforUid to set
     */
    public void setPublicKeyforUid(String publicKeyforUid) {
        this.publicKeyforUid = publicKeyforUid;
    }

    /**
     * @return the publicKeyforSecret
     */
    public String getPublicKeyforSecret() {
        return publicKeyforSecret;
    }

    /**
     * @param publicKeyforSecret the publicKeyforSecret to set
     */
    public void setPublicKeyforSecret(String publicKeyforSecret) {
        this.publicKeyforSecret = publicKeyforSecret;
    }
    
    
}
