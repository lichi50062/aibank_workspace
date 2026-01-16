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
package com.tfb.aibank.biz.user.services.datasync.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)ApiRequestUser.java
* 
* <p>Description: ApiRequestUser 更新資料彙整custom使用者</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "ApiRequestUser 更新資料彙整custom使用者")
public class ApiRequestUser {

    /**
     * 使用者身分證號
     */
    @Schema(description = "使用者身分證號")
    private String custId;

    /**
     * 識別碼
     */
    @Schema(description = "識別碼")
    private String dup;

    /**
     * 使用者代碼
     */
    @Schema(description = "使用者代碼")
    private String userId;

    /**
     * 公司類型
     */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private Date birthDay;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDup() {
        return dup;
    }

    public void setDup(String dup) {
        this.dup = dup;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
