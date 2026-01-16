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
* @(#)MidLoginOutputParams.java
* 
* <p>Description:台網MID驗證 - Login回傳物件 - OutputParams</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidLoginOutputParams {

    @SerializedName("MemberNo")
    private String memberNo;

    @SerializedName("Token")
    private String token;

    @SerializedName("TimeStamp")
    private String timeStamp;

    @SerializedName("MIDOutputParams")
    private MidLoginMIDOutputParams midOutputParams;

    /**
     * @return the memberNo
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo
     *            the memberNo to set
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp
     *            the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the midOutputParams
     */
    public MidLoginMIDOutputParams getMidOutputParams() {
        return midOutputParams;
    }

    /**
     * @param midOutputParams
     *            the midOutputParams to set
     */
    public void setMidOutputParams(MidLoginMIDOutputParams midOutputParams) {
        this.midOutputParams = midOutputParams;
    }

}
