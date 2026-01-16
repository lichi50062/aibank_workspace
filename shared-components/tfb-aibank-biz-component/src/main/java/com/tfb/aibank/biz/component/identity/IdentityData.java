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
package com.tfb.aibank.biz.component.identity;

import java.io.Serializable;

import com.tfb.aibank.common.type.CompanyStatusType;
import com.tfb.aibank.common.type.UserStatusType;

// @formatter:off
/**
 * @(#)IdentityData.java
 * 
 * <p>Description:識別資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class IdentityData implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 公司鍵值 */
    private Integer companyKey;

    /** 公司狀態，1:正常；0:暫停；-1:註銷；-2:虛擬公司 */
    private Integer companyStatus;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 使用者狀態，1:正常；0:暫停；-1:註銷 */
    private Integer userStatus;

    /**
     * 是否為正常的使用者
     * 
     * @return
     */
    public boolean isAliveUser() {
        return getCompanyStatus() != null && CompanyStatusType.find(getCompanyStatus()).isAlive() && UserStatusType.find(getUserStatus()).isAlive();
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "IdentityData{" +
                "companyKey=" + companyKey +
                ", companyStatus=" + companyStatus +
                ", userKey=" + userKey +
                ", userStatus=" + userStatus +
                '}';
    }
}
