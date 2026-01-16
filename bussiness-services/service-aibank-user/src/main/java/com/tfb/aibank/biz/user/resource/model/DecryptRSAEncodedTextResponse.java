package com.tfb.aibank.biz.user.resource.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)DecryptRSAEncodedTextResponse.java
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
     * @param rawText
     *            the rawText to set
     */
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

}
