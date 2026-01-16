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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)AccountAliasModel.java
 * 
 * <p>Description:帳號暱稱</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/06, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "帳號暱稱")
public class AccountAlias {

    /**
     * 帳號暱稱
     */
    @Schema(description = "帳號暱稱")
    private String accountAlias;

    /**
     * 主檔鍵值
     */
    @Schema(description = "主檔鍵值")
    private Integer accountAliasKey;

    /**
     * 帳號
     */
    @Schema(description = "帳號")
    private String accountNo;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 幣別英文名稱
     */
    @Schema(description = "幣別英文名稱")
    private String currencyEname;

    /**
     * 用戶代碼
     */
    @Schema(description = "用戶代碼")
    private String nameCode;

    /**
     * 存單號碼
     */
    @Schema(description = "存單號碼")
    private String slipNo;

    /**
     * 類別
     */
    @Schema(description = "類別")
    private String type;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    /**
     * 使用者代碼
     */
    @Schema(description = "使用者代碼")
    private String userId;

    /**
     * 公司鍵值
     */
    @Schema(description = "公司鍵值")
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Schema(description = "使用者鍵值")
    private Integer userKey;

    /**
     * @return the accountAlias
     */
    public String getAccountAlias() {
        return accountAlias;
    }

    /**
     * @param accountAlias
     *            the accountAlias to set
     */
    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    /**
     * @return the accountAliasKey
     */
    public Integer getAccountAliasKey() {
        return accountAliasKey;
    }

    /**
     * @param accountAliasKey
     *            the accountAliasKey to set
     */
    public void setAccountAliasKey(Integer accountAliasKey) {
        this.accountAliasKey = accountAliasKey;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the currencyEname
     */
    public String getCurrencyEname() {
        return currencyEname;
    }

    /**
     * @param currencyEname
     *            the currencyEname to set
     */
    public void setCurrencyEname(String currencyEname) {
        this.currencyEname = currencyEname;
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
     * @return the slipNo
     */
    public String getSlipNo() {
        return slipNo;
    }

    /**
     * @param slipNo
     *            the slipNo to set
     */
    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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

}
