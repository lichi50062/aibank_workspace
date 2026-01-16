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

import com.tfb.aibank.biz.user.repository.entities.AiDataSyncStatusEntity;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)DataSyncResponse.java
* 
* <p>Description: AiDataSyncStatusModel 資料彙整狀態註記檔</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "AiDataSyncStatusModel 資料彙整狀態註記檔")
public class AiDataSyncStatusModel {

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
     * 異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    @Schema(description = "異動情境(證券)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)")
    private Integer securType;

    /**
     * 異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    @Schema(description = "異動情境(富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)")
    private Integer insurType;

    /**
     * 證券註記(Y：同意彙整、N：不同意彙整)
     */
    @Schema(description = "證券註記(Y：同意彙整、N：不同意彙整)")
    private String securStatus;

    /**
     * 富壽註記(Y：同意彙整、N：不同意彙整)
     */
    @Schema(description = "富壽註記(Y：同意彙整、N：不同意彙整)")
    private String insurStatus;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    public AiDataSyncStatusModel() {
    }

    public AiDataSyncStatusModel(Integer companyKey, Integer userKey) {
        this.companyKey = companyKey;
        this.userKey = userKey;
    }

    public AiDataSyncStatusModel(AiDataSyncStatusEntity entity) {
        this.companyKey = entity.getCompanyKey();
        this.userKey = entity.getUserKey();
        this.securType = entity.getSecurType();
        this.securStatus = entity.getSecurStatus();
        this.insurType = entity.getInsurType();
        this.insurStatus = entity.getInsurStatus();
        this.createTime = entity.getCreateTime();
        this.updateTime = entity.getUpdateTime();
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
}
