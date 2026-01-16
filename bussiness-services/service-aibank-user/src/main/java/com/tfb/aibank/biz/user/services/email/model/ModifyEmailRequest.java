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
package com.tfb.aibank.biz.user.services.email.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ModifyEmailRequest.java
 * 
 * <p>Description:Email變更</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "Email變更")
public class ModifyEmailRequest {
    /** 公司鍵值 */
    @Schema(description = "公司鍵值")
    private Integer companyKey;
    /** 用戶代碼 */
    @Schema(description = "用戶代碼")
    private String nameCode;
    /** 使用者鍵值 */
    @Schema(description = "使用者鍵值")
    private Integer userKey;
    /** 變更原因代碼 */
    @Schema(description = "變更原因代碼")
    private String reasonCode;
    /** 變更原因 */
    @Schema(description = "變更原因")
    private String reason;

    // EB12020006
    /** 功能代碼 若為查詢:上送06 若為變更:上送52 */
    @Schema(description = "功能代碼 若為查詢:上送06 若為變更:上送52")
    private String funcCod;
    /** 功能 固定01 */
    @Schema(description = "功能 固定01")
    private String func = "01";
    /** 手機號碼 */
    @Schema(description = "手機號碼")
    private String mobile;
    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }
    
    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }
    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }
    
    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }
    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }
    
    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the reasonCode
     */
    public String getReasonCode() {
        return reasonCode;
    }
    
    /**
     * @param reasonCode
     *            the reasonCode to set
     */
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }
    
    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * @return the funcCod
     */
    public String getFuncCod() {
        return funcCod;
    }
    
    /**
     * @param funcCod
     *            the funcCod to set
     */
    public void setFuncCod(String funcCod) {
        this.funcCod = funcCod;
    }
    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }
    
    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
