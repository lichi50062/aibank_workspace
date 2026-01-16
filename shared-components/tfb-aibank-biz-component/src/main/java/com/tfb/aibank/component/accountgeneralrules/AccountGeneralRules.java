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
package com.tfb.aibank.component.accountgeneralrules;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)AccountGeneralRules.java
 * 
 * <p>Description:帳號通則資料檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "帳號通則資料檔")
public class AccountGeneralRules implements Serializable {

    private static final long serialVersionUID = 723069636919950514L;

    /** 帳號例外規則鍵值 */
    @Schema(description = "帳號例外規則鍵值")
    private Integer accountGeneralRulesKey;

    /** 帳號名稱 */
    @Schema(description = "帳號名稱")
    private String accountName;

    /** 大類正則表示 */
    @Schema(description = "大類正則表示")
    private String accountRuleRegexp;

    /** 子類正則表示 */
    @Schema(description = "子類正則表示")
    private String accountSubRuleRegexp;

    /** 帳號子類 */
    @Schema(description = "帳號子類")
    private String accountSubType;

    /** 帳號大類 */
    @Schema(description = "帳號大類")
    private String accountType;

    /** 建立時間 */
    @Schema(description = "建立時間")
    private Date createTime;

    /** 語系 */
    @Schema(description = "語系")
    private String locale;

    /** 更新時間 */
    @Schema(description = "更新時間")
    private Date updateTime;

    public Integer getAccountGeneralRulesKey() {
        return accountGeneralRulesKey;
    }

    public void setAccountGeneralRulesKey(Integer accountGeneralRulesKey) {
        this.accountGeneralRulesKey = accountGeneralRulesKey;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountRuleRegexp() {
        return accountRuleRegexp;
    }

    public void setAccountRuleRegexp(String accountRuleRegexp) {
        this.accountRuleRegexp = accountRuleRegexp;
    }

    public String getAccountSubRuleRegexp() {
        return accountSubRuleRegexp;
    }

    public void setAccountSubRuleRegexp(String accountSubRuleRegexp) {
        this.accountSubRuleRegexp = accountSubRuleRegexp;
    }

    public String getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(String accountSubType) {
        this.accountSubType = accountSubType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
