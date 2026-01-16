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
* @(#)MidQueryVerifyResultOutputParams.java
* 
* <p>Description:台網MID驗證 - QueryVerifyResult回傳物件 - OutputParams</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidQueryVerifyResultOutputParams {

    @SerializedName("Action")
    private String action;

    @SerializedName("SelectType")
    private String selectType;

    @SerializedName("VerifyTime")
    private String verifyTime;

    @SerializedName("Plaintext")
    private String plaintext;

    @SerializedName("MIDParams")
    private MidQueryVerifyResultMIDParams midParams;

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the selectType
     */
    public String getSelectType() {
        return selectType;
    }

    /**
     * @param selectType
     *            the selectType to set
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     * @return the verifyTime
     */
    public String getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     *            the verifyTime to set
     */
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    /**
     * @return the plaintext
     */
    public String getPlaintext() {
        return plaintext;
    }

    /**
     * @param plaintext
     *            the plaintext to set
     */
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    /**
     * @return the midParams
     */
    public MidQueryVerifyResultMIDParams getMidParams() {
        return midParams;
    }

    /**
     * @param midParams
     *            the midParams to set
     */
    public void setMidParams(MidQueryVerifyResultMIDParams midParams) {
        this.midParams = midParams;
    }

}
