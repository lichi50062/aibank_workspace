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

import java.util.Date;

//@formatter:off
/**
* @(#)DataSyncResponse.java
* 
* <p>Description: DataSyncResponse</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AiDataSyncStatusModel {

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    private Integer securType;

    /**
     * 異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    private Integer insurType;

    /**
     * 證券註記(Y：同意彙整、N：不同意彙整)
     */
    private String securStatus;

    /**
     * 富壽註記(Y：同意彙整、N：不同意彙整)
     */
    private String insurStatus;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

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

    public Integer getSecurType() {
        return securType;
    }

    public void setSecurType(Integer securType) {
        this.securType = securType;
    }

    public Integer getInsurType() {
        return insurType;
    }

    public void setInsurType(Integer insurType) {
        this.insurType = insurType;
    }

    public String getSecurStatus() {
        return securStatus;
    }

    public void setSecurStatus(String securStatus) {
        this.securStatus = securStatus;
    }

    public String getInsurStatus() {
        return insurStatus;
    }

    public void setInsurStatus(String insurStatus) {
        this.insurStatus = insurStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AiDataSyncStatusResponse [companyKey=" + companyKey + ", userKey=" + userKey + ", securType=" + securType + ", insurType=" + insurType + ", securStatus=" + securStatus + ", insurStatus=" + insurStatus + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }
}
