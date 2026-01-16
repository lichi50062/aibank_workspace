/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CEW466RRes.java
 * 
 * <p>Description:[Costco會員自動續約]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW466RRes {
    private String memberNumber;

    private String membercardType;

    private String responseCode;

    private String autoRenew;

    private String abn;

    /**
     * @return the memberNumber
     */
    public String getMemberNumber() {
        return memberNumber;
    }

    /**
     * @param memberNumber
     *            the memberNumber to set
     */
    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    /**
     * @return the membercardType
     */
    public String getMembercardType() {
        return membercardType;
    }

    /**
     * @param membercardType
     *            the membercardType to set
     */
    public void setMembercardType(String membercardType) {
        this.membercardType = membercardType;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode
     *            the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the autoRenew
     */
    public String getAutoRenew() {
        return autoRenew;
    }

    /**
     * @param autoRenew
     *            the autoRenew to set
     */
    public void setAutoRenew(String autoRenew) {
        this.autoRenew = autoRenew;
    }

    /**
     * @return the abn
     */
    public String getAbn() {
        return abn;
    }

    /**
     * @param abn
     *            the abn to set
     */
    public void setAbn(String abn) {
        this.abn = abn;
    }

}
