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
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)Promotion.java
 * 
 * <p>Description:[優惠活動]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PromotionListRequest {

    /** 是否登入 */
    private boolean isLogin;

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private int companyKind;

    /** 誤別碼 */
    private String uidDup;

    /** 語系 */
    private String locale;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
