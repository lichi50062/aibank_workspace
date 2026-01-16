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
package com.tfb.aibank.biz.security.services.mid.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)MidLoginResponse.java
 * 
 * <p>Description:台網MID驗證 - Login - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "台網MID驗證 - Login - Response")
public class MidLoginResponse {

    /** 交易結果代碼 */
    @Schema(description = "交易結果代碼")
    private String returnCode;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String returnCodeDesc;

    /** 通行證 */
    @Schema(description = "通行證")
    private String token;

    /** 驗證編號 */
    @Schema(description = "驗證編號")
    private String verifyNo;

    /** MID 交易序號 */
    @Schema(description = "MID 交易序號")
    private String reqSeq;

    /** MID 交易金鑰 */
    @Schema(description = "MID 交易金鑰")
    private String sessionKey;

    /** MID 交易資訊 */
    @Schema(description = "MID 交易資訊")
    private String profile;

    /** 授權條款版號 */
    @Schema(description = "授權條款版號")
    private String clauseVer;

    /** MOTP裝置綁定鍵值 */
    @Schema(description = "MOTP裝置綁定鍵值")
    private Integer motpMidKey;

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnCodeDesc
     */
    public String getReturnCodeDesc() {
        return returnCodeDesc;
    }

    /**
     * @param returnCodeDesc
     *            the returnCodeDesc to set
     */
    public void setReturnCodeDesc(String returnCodeDesc) {
        this.returnCodeDesc = returnCodeDesc;
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

    /**
     * @return the clauseVer
     */
    public String getClauseVer() {
        return clauseVer;
    }

    /**
     * @param clauseVer
     *            the clauseVer to set
     */
    public void setClauseVer(String clauseVer) {
        this.clauseVer = clauseVer;
    }

    /**
     * @return the motpMidKey
     */
    public Integer getMotpMidKey() {
        return motpMidKey;
    }

    /**
     * @param motpMidKey
     *            the motpMidKey to set
     */
    public void setMotpMidKey(Integer motpMidKey) {
        this.motpMidKey = motpMidKey;
    }

}
