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
* @(#)MidLoginMIDaOutputParams.java
* 
* <p>Description:台網MID驗證 - Login回傳物件 - MIDaOutputParams</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidLoginMIDaOutputParams {

    @SerializedName("ReqSeq")
    private String reqSeq;

    @SerializedName("SessionKey")
    private String sessionKey;

    @SerializedName("Profile")
    private String profile;

    /**
     * @return the reqSeq
     */
    public String getReqSeq() {
        return reqSeq;
    }

    /**
     * @param reqSeq
     *            the reqSeq to set
     */
    public void setReqSeq(String reqSeq) {
        this.reqSeq = reqSeq;
    }

    /**
     * @return the sessionKey
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * @param sessionKey
     *            the sessionKey to set
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * @return the profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile
     *            the profile to set
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

}
