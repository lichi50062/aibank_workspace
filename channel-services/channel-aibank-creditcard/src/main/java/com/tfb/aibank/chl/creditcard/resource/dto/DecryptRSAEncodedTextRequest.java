/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

/**
 * @author john
 *
 */
public class DecryptRSAEncodedTextRequest {

    private String encodedText;

    // "是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]")
    private Boolean isPwdWithTime;

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

    /**
     * @return the isPwdWithTime
     */
    public Boolean getIsPwdWithTime() {
        return isPwdWithTime;
    }

    /**
     * @param isPwdWithTime
     *            the isPwdWithTime to set
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
