/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class DecryptRSAEncodedTextResponse {

    
    @Schema(description = "原始資料")
    private String rawText;

    /**
     * @return the rawText
     */
    public String getRawText() {
        return rawText;
    }

    /**
     * @param rawText the rawText to set
     */
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

}
