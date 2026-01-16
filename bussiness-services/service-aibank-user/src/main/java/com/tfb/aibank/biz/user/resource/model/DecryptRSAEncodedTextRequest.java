package com.tfb.aibank.biz.user.resource.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)DecryptRSAEncodedTextRequest.java
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
@Schema(description = "DecryptRSAEncodedTextRequest")
public class DecryptRSAEncodedTextRequest {

    @Schema(description = "加密的資料")
    private String encodedText;

    /**
     * @return the encodedText
     */
    public String getEncodedText() {
        return encodedText;
    }

    /**
     * @param encodedText
     *            the encodedText to set
     */
    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

}
