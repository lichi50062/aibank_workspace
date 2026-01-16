/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.security.services.mid.bean;

import com.google.gson.annotations.SerializedName;

//@formatter:off
/**
* @(#)MidQueryVerifyResultMIDParams.java
* 
* <p>Description:台網MID驗證 - QueryVerifyResult回傳物件 - MIDParams</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidQueryVerifyResultMIDParams {

    @SerializedName("MIDResp")
    private String midResp;

    @SerializedName("VerifyCode")
    private String verifyCode;

    @SerializedName("VerifyMsg")
    private String verifyMsg;

    /**
     * @return the midResp
     */
    public String getMidResp() {
        return midResp;
    }

    /**
     * @param midResp
     *            the midResp to set
     */
    public void setMidResp(String midResp) {
        this.midResp = midResp;
    }

    /**
     * @return the verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode
     *            the verifyCode to set
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return the verifyMsg
     */
    public String getVerifyMsg() {
        return verifyMsg;
    }

    /**
     * @param verifyMsg
     *            the verifyMsg to set
     */
    public void setVerifyMsg(String verifyMsg) {
        this.verifyMsg = verifyMsg;
    }

}
