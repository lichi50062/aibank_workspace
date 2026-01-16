/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author john
 *
 */
public class FidoLoginOutputParams {

    @SerializedName("MemberNo")
    private String businessNo;

    @SerializedName("Token")
    private String token;

    @SerializedName("TimeStamp")
    private String timeStamp;

    @SerializedName("FidoOutputParams")
    private String fidoOutputParams;

    /**
     * @return the businessNo
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * @param businessNo
     *            the businessNo to set
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
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
    public String getFidoOutputParams() {
        return fidoOutputParams;
    }

    /**
     * @param fidoOutputParams
     *            the fidoOutputParams to set
     */
    public void setFidoOutputParams(String fidoOutputParams) {
        this.fidoOutputParams = fidoOutputParams;
    }

}
