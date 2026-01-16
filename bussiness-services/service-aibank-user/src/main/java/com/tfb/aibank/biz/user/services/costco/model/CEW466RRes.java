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
package com.tfb.aibank.biz.user.services.costco.model;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Costco會員自動續約")
public class CEW466RRes {

    @Schema(description = "卡別")
    private String membercardType;

    @Schema(description = "COSTCO會員代碼")
    private String memberNumber;

    @Schema(description = "回傳代碼")
    private String responseCode;

    @Schema(description = "更新代碼")
    private String autoRenew;

    @Schema(description = "處理結果")
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
