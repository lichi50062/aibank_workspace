package com.tfb.aibank.biz.user.services.user.model;

import java.io.Serializable;

public class UserDataModel implements Serializable {

    private String custId;

    private String userId;

    private String uidDup;

    private String nameCode;

    private Integer companyKind;

    private String mobile;

    private String email;

    private Integer userKey;

    public UserDataModel() {
    }

    public UserDataModel(String custId, String userId, String uidDup, String nameCode, Integer companyKind, String mobile, String email, Integer userKey) {
        this.custId = custId;
        this.userId = userId;
        this.uidDup = uidDup;
        this.nameCode = nameCode;
        this.companyKind = companyKind;
        this.mobile = mobile;
        this.email = email;
        this.userKey = userKey;
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

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }
}
