/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author john
 *
 */
public class FidoLoginResponseContentBean {

    @SerializedName("MemberNo")
    private String memberNo;

    @SerializedName("Token")
    private String token;

    @SerializedName("TimeStamp")
    private String timeStamp;

    @SerializedName("FidoOutputParams")
    private FidoLoginResponseContentFidoParamsBean fidoOutputParams;

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
     * @return the fidoOutputParams
     */
    public FidoLoginResponseContentFidoParamsBean getFidoOutputParams() {
        return fidoOutputParams;
    }

    /**
     * @param fidoOutputParams
     *            the fidoOutputParams to set
     */
    public void setFidoOutputParams(FidoLoginResponseContentFidoParamsBean fidoOutputParams) {
        this.fidoOutputParams = fidoOutputParams;
    }

}
