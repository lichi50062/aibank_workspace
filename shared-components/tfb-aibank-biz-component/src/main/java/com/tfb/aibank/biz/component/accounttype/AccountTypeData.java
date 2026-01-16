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
package com.tfb.aibank.biz.component.accounttype;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 帳號科目別(人工維護)
 * 
 * @author Edward Tien
 */
@Schema(description = "帳號科目別(人工維護)")
public class AccountTypeData implements Serializable {

    private static final long serialVersionUID = 2609483380646374699L;

    /**
     * 帳號科目別
     */
    @Schema(description = "帳號科目別")
    private String accountId;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 帳號類別名稱代碼
     */
    @Schema(description = "帳號類別名稱代碼")
    private String accountTypeCode;

    /**
     * 帳號類別名稱
     */
    @Schema(description = "帳號類別名稱")
    private String accountTypeName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

}
