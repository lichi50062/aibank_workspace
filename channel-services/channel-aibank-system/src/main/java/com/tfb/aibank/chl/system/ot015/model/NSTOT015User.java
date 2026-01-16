/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.model;

import com.tfb.aibank.chl.component.devicebinding.model.QuickSearchResponse;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NSTOT015User.java
* 
* <p>Description: 廣告版位使用者</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/15, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NSTOT015User {

    /**
     * 使用者ID
     */
    private String custId;

    /**
     * 誤別碼
     */
    private String uidDup;

    /**
     * 使用者代碼
     */
    private String userId;

    /**
     * 公司別
     */
    private Integer companyKind;

    /**
     * deviceUuid
     */
    private String deviceUuid;

    public NSTOT015User() {
    }

    public NSTOT015User(AIBankUser user) {
        this.custId = user.getCustId();
        this.uidDup = user.getUidDup();
        this.userId = user.getUserId();
        this.companyKind = user.getCompanyKind();
    }

    public NSTOT015User(QuickSearchResponse res) {
        this.custId = res.getCustId();
        this.uidDup = res.getUidDup();
        this.userId = res.getUserId();
        this.companyKind = res.getCompanyKind();
    }

    public NSTOT015User(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
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

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }
}
