/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;


/**
 * @author john
 *
 */
public class DecryptRSAEncodedTextResponse {

    
    /** 原始資料 */
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
