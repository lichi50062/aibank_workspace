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
* @(#)MidLoginRs.java
* 
* <p>Description:台網MID驗證 - 共用回傳物件</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public abstract class MidRs {

    @SerializedName("BusinessNo")
    private String businessNo;

    @SerializedName("ApiVersion")
    private String apiVersion;

    @SerializedName("HashKeyNo")
    private String hashKeyNo;

    @SerializedName("VerifyNo")
    private String verifyNo;

    @SerializedName("IdentifyNo")
    private String identifyNo;

    public abstract String getIdentifyData(String hashKey);

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
     * @return the apiVersion
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion
     *            the apiVersion to set
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * @return the hashKeyNo
     */
    public String getHashKeyNo() {
        return hashKeyNo;
    }

    /**
     * @param hashKeyNo
     *            the hashKeyNo to set
     */
    public void setHashKeyNo(String hashKeyNo) {
        this.hashKeyNo = hashKeyNo;
    }

    /**
     * @return the verifyNo
     */
    public String getVerifyNo() {
        return verifyNo;
    }

    /**
     * @param verifyNo
     *            the verifyNo to set
     */
    public void setVerifyNo(String verifyNo) {
        this.verifyNo = verifyNo;
    }

    /**
     * @return the identifyNo
     */
    public String getIdentifyNo() {
        return identifyNo;
    }

    /**
     * @param identifyNo
     *            the identifyNo to set
     */
    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
    }

}
